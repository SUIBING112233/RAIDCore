package dev.krysztal.raid.foundation

import java.util.*

interface PartyManager {
    fun createParty(vararg partyMember: PartyMember): UUID
    fun removeParty(uuid: UUID)
    fun clearParty(uuid: UUID)
    fun getParty(uuid: UUID): Party?
    fun setParty(uuid: UUID, party: Party)
    fun getParties(): List<Party>
    fun setParties(list: List<Party>)
    fun addMember(uuid: UUID, partyMember: PartyMember)
    fun removeMember(partyUuid: UUID, memberUuid: UUID)

    fun saveParties()
}

interface Party {
    fun addMember(vararg partyMember: PartyMember)
    fun removeMember(uuid: UUID)
    fun getMembers(): List<PartyMember>
    fun getMember(uuid: UUID): PartyMember?
    fun uuid(): UUID
}