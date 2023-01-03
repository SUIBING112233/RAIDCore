package dev.krysztal.raid.foundation

import org.bukkit.entity.Player
import java.util.*

interface TroopManager {
    fun newTroop(uuid: UUID): Boolean
    fun deleteTroop(uuid: UUID): Boolean
    fun getTroop(player: Player): Troop
    fun getTroops(): List<Troop>
}

interface Troop {
    fun members(): List<TroopMember>
    fun uuid(): UUID
    fun name(): String
    fun add(player: Player, operator: Player)
    fun kick(player: Player, operator: Player)
}