package net.iessochoa.sergiocontreras.jcsqlite

import kotlin.jvm.javaClass

/**
 * Project: XML SQLite
 * From: net.iessochoa.sergiocontreras.xmlsqlite
 * Created by: Contr
 * On: 07/11/2025 at 22:20
 * Creado en Settings -> Editor -> File and Code Templates
 */
class Park(var id: Long = 0,
           val name: String,
           var isFavorite: Boolean ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Park

        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}