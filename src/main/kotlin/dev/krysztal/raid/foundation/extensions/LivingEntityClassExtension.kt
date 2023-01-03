package dev.krysztal.raid.foundation.extensions

import dev.krysztal.raid.RAID
import dev.krysztal.raid.RAIDMain
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue

fun LivingEntity.isEnemy(): Boolean {
    return this.getMetadata(RAID.Constants.IS_ENEMY).first().asBoolean();
}

fun LivingEntity.setEnemy(isEnemy: Boolean) {
    this.setMetadata(RAID.Constants.IS_ENEMY, FixedMetadataValue(RAIDMain, isEnemy))
}

fun LivingEntity.toEnemy() {
    this.setEnemy(true)
}
