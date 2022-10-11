package dev.krysztal.raid.party

import java.util.*

interface IParties {
    fun createParty(vararg member: Member): UUID
    fun removeParty(uuid: UUID)
    fun clearParty(uuid: UUID)
    fun getParty(uuid: UUID): IParty?
    fun setParty(uuid: UUID, iParty: IParty)
    fun getParties(): List<Pair<UUID, IParty>>
    fun setParties(list: List<Pair<UUID, IParty>>)
    fun addMember(uuid: UUID, member: Member)
    fun removeMember(partyUuid: UUID, memberUuid: UUID)

    fun saveParties()
}

interface IParty {
    fun addMember(vararg member: Member)
    fun removeMember(uuid: UUID)
    fun getMembers(): List<Member>
    fun getMember(uuid: UUID): Member?
}