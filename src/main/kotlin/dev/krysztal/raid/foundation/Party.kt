package dev.krysztal.raid.foundation

import java.util.*

interface PartyManager {
    fun createParty(vararg member: Member): UUID
    fun removeParty(uuid: UUID)
    fun clearParty(uuid: UUID)
    fun getParty(uuid: UUID): Party?
    fun setParty(uuid: UUID, party: Party)
    fun getParties(): List<Pair<UUID, Party>>
    fun setParties(list: List<Pair<UUID, Party>>)
    fun addMember(uuid: UUID, member: Member)
    fun removeMember(partyUuid: UUID, memberUuid: UUID)

    fun saveParties()
}

interface Party {
    fun addMember(vararg member: Member)
    fun removeMember(uuid: UUID)
    fun getMembers(): List<Member>
    fun getMember(uuid: UUID): Member?
}