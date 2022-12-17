package dev.krysztal.raid.foundation.utils

import dev.krysztal.raid.RAIDMain
import dev.krysztal.raid.foundation.extensions.getConfig
import dev.krysztal.raid.party.PartiesImpls
import java.io.File

object ConfigUtils {
    var PARTY_IMPL_TYPE: PartiesImpls

    init {
        val configFile = File(RAIDMain.dataFolder.path, "config.yml")
        if (!configFile.exists()) {
            configFile.parentFile.mkdirs()
            RAIDMain.saveResource("config.yml", false)
        }
        RAIDMain.config.load(configFile);

        PARTY_IMPL_TYPE = when (PartiesImpls.valueOf("party.impl".getConfig().toString())) {
            else -> PartiesImpls.Local
        }
    }
}