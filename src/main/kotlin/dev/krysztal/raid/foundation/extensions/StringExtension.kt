package dev.krysztal.raid.foundation.extensions

import dev.krysztal.raid.RAIDMain

fun String.getConfig(): Any? {
    return RAIDMain.config.get(this)
}


