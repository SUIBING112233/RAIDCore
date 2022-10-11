package dev.krysztal.raid

import dev.krysztal.raid.party.PartiesImplTypes
import dev.krysztal.raid.party.impl.PartyImplLocal
import dev.krysztal.raid.utils.ConfigUtils
import org.bukkit.plugin.java.JavaPlugin

object RAIDMain : JavaPlugin() {
    override fun onEnable() {
        when (ConfigUtils.PARTY_IMPL_TYPE) {
            PartiesImplTypes.Local -> RAID.party = PartyImplLocal()
        }
    }

    override fun onDisable() {
        RAID.party.saveParties()
    }
}