package dev.krysztal.raid.party

import com.google.gson.Gson
import dev.krysztal.raid.RAIDMain
import dev.krysztal.raid.foundation.Party
import dev.krysztal.raid.foundation.PartyManager
import dev.krysztal.raid.foundation.PartyMember
import dev.krysztal.raid.foundation.PartyMemberPermission
import java.io.File
import java.util.*


class PartyImplLocal : PartyManager {
    private var partiesList: MutableList<Party> = mutableListOf()
    override fun createParty(vararg partyMember: PartyMember): UUID {
        val uuid = UUID.randomUUID()

        val party = PartyLocal()
        party.addMember(*partyMember)

        this.partiesList.add(party)
        return uuid
    }

    private fun createParty(uuid: UUID) {
        this.removeParty(uuid)
        this.partiesList.add(PartyLocal())

    }

    override fun removeParty(uuid: UUID) {
        this.partiesList.removeIf { it.uuid() == uuid }
    }

    override fun clearParty(uuid: UUID) {
        val members =
            this.getParty(uuid)?.getMembers()?.filter { it.partyMemberPermission == PartyMemberPermission.Captain }
        val partyLocal = PartyLocal(
            partyMemberList = members!!.toMutableList(),
            uuid = uuid,
        )

        this.removeParty(uuid)
        this.setParty(uuid, partyLocal)
    }

    override fun getParty(uuid: UUID): Party? {
        return this.partiesList.find { it.uuid() == uuid }
    }

    override fun setParty(uuid: UUID, party: Party) {
        this.removeParty(uuid)
        this.partiesList.add(party)
    }

    override fun getParties(): List<Party> {
        return this.partiesList
    }

    override fun setParties(list: List<Party>) {
        this.partiesList = list.toMutableList()
    }

    override fun addMember(uuid: UUID, partyMember: PartyMember) {
        val party = this.partiesList.find { it.uuid() == uuid }

        party?.addMember(partyMember)
        party?.let { this.setParty(uuid, it) }
    }

    override fun removeMember(partyUuid: UUID, memberUuid: UUID) {
        val party = this.partiesList.find { it.uuid() == partyUuid }
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


class PartyLocal(
    private var partyMemberList: MutableList<PartyMember> = mutableListOf<PartyMember>(),
    private val uuid: UUID = UUID.randomUUID()
) : Party {

    override fun addMember(vararg partyMember: PartyMember) {
        partyMember.forEach {
            this.removeMember(it.uuid)
            this.partyMemberList.add(it)
        }
    }

    override fun removeMember(uuid: UUID) {
        this.partyMemberList.removeIf { it.uuid == uuid }
    }

    override fun getMembers(): List<PartyMember> {
        return this.partyMemberList
    }

    override fun getMember(uuid: UUID): PartyMember? {
        return this.partyMemberList.find { it.uuid == uuid }
    }

    override fun uuid(): UUID {
        return this.uuid
    }
}