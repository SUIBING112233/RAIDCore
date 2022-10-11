package dev.krysztal.raid.utils

import dev.krysztal.raid.RAIDMain
import dev.krysztal.raid.party.PartiesImplTypes
import java.io.File

object ConfigUtils {
    lateinit var PARTY_IMPL_TYPE: PartiesImplTypes

    init {
        val configFile = File(RAIDMain.dataFolder.path, "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            RAIDMain.saveResource("config.yml", false)
        }
        RAIDMain.config.load(configFile);

        this.PARTY_IMPL_TYPE = when (RAIDMain.config.get("party.impl")) {
            else -> PartiesImplTypes.Local
        }


    }
}