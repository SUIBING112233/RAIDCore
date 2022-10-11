package dev.krysztal.raid.utils

import dev.krysztal.raid.RAIDMain
import org.bukkit.entity.LivingEntity
import org.bukkit.metadata.FixedMetadataValue

fun LivingEntity.isEnemy(): Boolean {
    return this.getMetadata(Constants.IS_ENEMY).first().asBoolean();
}

fun LivingEntity.setEnemy(isEnemy: Boolean) {
    this.setMetadata(Constants.IS_ENEMY, FixedMetadataValue(RAIDMain, isEnemy))
}

fun LivingEntity.toEnemy() {
    this.setEnemy(true)
}
