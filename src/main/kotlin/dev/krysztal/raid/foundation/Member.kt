package dev.krysztal.raid.foundation

import org.bukkit.entity.Player
import java.util.*

data class Member(
    val uuid: UUID, var memberPermission: MemberPermission = MemberPermission.Member
) {
    companion object {
        fun fromPlayer(player: Player, memberPermission: MemberPermission): Member {
            return Member(player.uniqueId, memberPermission)
        }

        fun fromPlayer(player: Player): Member {
            return Member(player.uniqueId)
        }
    }
}

enum class MemberPermission { Member, Captain }