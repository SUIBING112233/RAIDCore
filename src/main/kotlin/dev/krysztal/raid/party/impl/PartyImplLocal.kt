package dev.krysztal.raid.party.impl

import com.google.gson.Gson
import dev.krysztal.raid.RAIDMain
import dev.krysztal.raid.party.IParties
import dev.krysztal.raid.party.IParty
import dev.krysztal.raid.party.Member
import dev.krysztal.raid.party.MemberPermission
import java.io.File
import java.util.*


class PartyImplLocal : IParties {
    private var partiesList: MutableList<Pair<UUID, IParty>> = mutableListOf()
    override fun createParty(vararg member: Member): UUID {
        val uuid = UUID.randomUUID()

        val party = PartyLocal()
        party.addMember(*member)

        this.partiesList.add(Pair(uuid, party))
        return uuid
    }

    private fun createParty(uuid: UUID) {
        this.removeParty(uuid)
        this.partiesList.add(Pair(uuid, PartyLocal()))

    }

    override fun removeParty(uuid: UUID) {
        this.partiesList.removeIf { it.first == uuid }
    }

    override fun clearParty(uuid: UUID) {
        val members = this.getParty(uuid)?.getMembers()?.filter { it.memberPermission == MemberPermission.Captain }
        val partyLocal = PartyLocal()
        partyLocal.memberList = members?.toMutableList()!!

        this.removeParty(uuid)
        this.setParty(uuid, partyLocal)
    }

    override fun getParty(uuid: UUID): IParty? {
        return this.partiesList.find { it.first == uuid }?.second
    }

    override fun setParty(uuid: UUID, iParty: IParty) {
        this.removeParty(uuid)
        this.partiesList.add(Pair(uuid, iParty))
    }

    override fun getParties(): List<Pair<UUID, IParty>> {
        return this.partiesList
    }

    override fun setParties(list: List<Pair<UUID, IParty>>) {
        this.partiesList = list.toMutableList()
    }

    override fun addMember(uuid: UUID, member: Member) {
        val party = this.partiesList.find { it.first == uuid }?.second

        party?.addMember(member)
        party?.let { this.setParty(uuid, it) }
    }

    override fun removeMember(partyUuid: UUID, memberUuid: UUID) {
        val party = this.partiesList.find { it.first == partyUuid }?.second
        party?.removeMember(memberUuid)
        party?.let { this.setParty(partyUuid, it) }

        if (party?.getMembers()?.isEmpty() == true) this.removeParty(partyUuid)
    }

    override fun saveParties() {
        val partiesFile = File(RAIDMain.dataFolder, "parties.json")

        if (!partiesFile.exists()) partiesFile.createNewFile()
        partiesFile.writeText(Gson().toJson(this.partiesList))
    }
}


private class PartyLocal : IParty {
    var memberList = mutableListOf<Member>()

    override fun addMember(vararg member: Member) {
        member.forEach {
            this.removeMember(it.uuid)
            this.memberList.add(it)
        }
    }

    override fun removeMember(uuid: UUID) {
        this.memberList.removeIf { it.uuid == uuid }
    }

    override fun getMembers(): List<Member> {
        return this.memberList
    }

    override fun getMember(uuid: UUID): Member? {
        return this.memberList.find { it.uuid == uuid }
    }
}