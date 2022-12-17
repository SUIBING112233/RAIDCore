package dev.krysztal.raid

import dev.krysztal.raid.party.PartiesImpls
import dev.krysztal.raid.party.PartyImplLocal
import dev.krysztal.raid.foundation.utils.ConfigUtils
import org.bukkit.plugin.java.JavaPlugin

object RAIDMain : JavaPlugin() {
    override fun onEnable() {
        when (ConfigUtils.PARTY_IMPL_TYPE) {
            PartiesImpls.Local -> RAID.party = PartyImplLocal()
        }
    }

    override fun onDisable() {
        RAID.party.saveParties()
    }
}