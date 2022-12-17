package dev.krysztal.raid.foundation.extensions

import dev.krysztal.raid.RAIDMain
import dev.krysztal.raid.foundation.utils.Constants
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
