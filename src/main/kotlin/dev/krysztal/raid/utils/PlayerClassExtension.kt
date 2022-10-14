package dev.krysztal.raid.utils

import dev.krysztal.raid.RAID
import dev.krysztal.raid.party.Member
import dev.krysztal.raid.party.MemberPermission
import org.bukkit.entity.Player
import java.util.*


fun Player.addToParty(uuid: UUID) {
    RAID.party.addMember(uuid, this.toMember())
}

fun Player.addToParty(uuid: UUID, memberPermission: MemberPermission) {
    RAID.party.addMember(uuid, this.toMember(memberPermission))
}

fun Player.removeFromParty(uuid: UUID) {
    RAID.party.removeMember(uuid, this.uniqueId)
}

fun Player.toMember(): Member {
    return Member(this.uniqueId)
}

fun Player.toMember(memberPermission: MemberPermission): Member {
    return Member(this.uniqueId, memberPermission)
}

fun Player.isMemberOf(uuid: UUID): Boolean {
    return RAID.party.getParty(uuid)?.getMember(this.uniqueId) == null
}
