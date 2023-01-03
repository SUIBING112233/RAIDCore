package dev.krysztal.raid.foundation

import org.bukkit.entity.Player
import java.util.*

data class PartyMember(
    val uuid: UUID, var partyMemberPermission: PartyMemberPermission = PartyMemberPermission.Member
) {
    companion object {
        fun fromPlayer(player: Player, partyMemberPermission: PartyMemberPermission): PartyMember {
            return PartyMember(player.uniqueId, partyMemberPermission)
        }

        fun fromPlayer(player: Player): PartyMember {
            return PartyMember(player.uniqueId)
        }
    }
}

enum class PartyMemberPermission { Member, Captain }

data class TroopMember(
    val uuid: UUID,
    var troopMemberPermission: Int
)
