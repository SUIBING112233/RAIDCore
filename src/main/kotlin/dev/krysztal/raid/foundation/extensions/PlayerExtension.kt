package dev.krysztal.raid.foundation.extensions

import dev.krysztal.raid.RAID
import dev.krysztal.raid.foundation.PartyMember
import dev.krysztal.raid.foundation.PartyMemberPermission
import org.bukkit.entity.Player
import java.util.*


fun Player.addToParty(uuid: UUID) {
    RAID.party.addMember(uuid, this.toPartyMember())
}

fun Player.addToParty(uuid: UUID, partyMemberPermission: PartyMemberPermission) {
    RAID.party.addMember(uuid, this.toPartyMember(partyMemberPermission))
}

fun Player.removeFromParty(uuid: UUID) {
    RAID.party.removeMember(uuid, this.uniqueId)
}

fun Player.toPartyMember(): PartyMember {
    return PartyMember(this.uniqueId)
}

fun Player.toPartyMember(partyMemberPermission: PartyMemberPermission): PartyMember {
    return PartyMember(this.uniqueId, partyMemberPermission)
}

fun Player.isPartyMemberOf(partyUUID: UUID): Boolean {
    return RAID.party.getParty(partyUUID)?.getMember(this.uniqueId) == null
}

fun Player.isTroopMemberOf(partyUUID: UUID): Boolean {
    return RAID.party.getParty(partyUUID)?.getMember(this.uniqueId) == null
}