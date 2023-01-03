package dev.krysztal.raid

import dev.krysztal.raid.foundation.PartyManager

object RAID {
    lateinit var party: PartyManager

    object Constants {
        const val IS_ENEMY = "raid_core:is_enemy"
    }
}

