package net.iessochoa.sergiocontreras.jcsqlite

import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * Project: JC SQlite
 * From: net.iessochoa.sergiocontreras.jcsqlite
 * Created by: Contr
 * On: 09/11/2025 at 09:41
 * Creado en Settings -> Editor -> File and Code Templates
 */

val parkPreview = Park(1, "Parque preview", true)
val parksPreview = List(4) { parkPreview } //True Kotlin mejor

suspend fun simulateDelay() {
    delay(Random.nextLong(300, 1500))
}

suspend fun simulateDelayLong() {
    delay(Random.nextLong(3000, 5000))
}
