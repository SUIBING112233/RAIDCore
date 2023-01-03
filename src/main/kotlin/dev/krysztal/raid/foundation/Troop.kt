package dev.krysztal.raid.foundation

import java.util.*

interface TroopManager {
    fun newTroop(uuid: UUID): Boolean
    fun deleteTroop(uuid: UUID): Boolean
    fun getTroop(troopUUID: UUID): Troop
    fun getTroops(): List<Troop>
}

interface Troop {
    fun members(): List<TroopMember>
    fun uuid(): UUID
    fun name(): String
    fun add(player: UUID, operator: UUID)
    fun kick(player: UUID, operator: UUID)
}
