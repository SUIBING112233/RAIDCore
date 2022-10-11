package dev.krysztal.raid.utils

import dev.krysztal.raid.RAIDMain

fun String.getConfig(): Any? {
    return RAIDMain.config.get(this)
}


