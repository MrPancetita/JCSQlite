package net.iessochoa.sergiocontreras.jcsqlite.sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import net.iessochoa.sergiocontreras.jcsqlite.Park
import kotlin.apply

/**
 * Project: XML SQLite
 * From: net.iessochoa.sergiocontreras.xmlsqlite.sqlite
 * Created by: Contr
 * On: 08/11/2025 at 00:49
 * Creado en Settings -> Editor -> File and Code Templates
 */
class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    Constants.DATABASE_NAME,
    null,
    Constants.DATABASE_VERSION
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ${Constants.ENTITY_PARK} (" +
            "${Constants.P_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
            "${Constants.P_NAME} TEXT," +
            "${Constants.P_IS_FAVORITE} BOOLEAN )"
        db?.execSQL(createTable) //Android se da cuenta con esto que lo de arriba es una instruccion SQL
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }

    fun insertPark(park: Park): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.P_NAME, park.name)
            put(Constants.P_IS_FAVORITE, park.isFavorite)
        }

        val resultId = database.insert(
            Constants.ENTITY_PARK,
            null,
            contentValues
        )
        return resultId

    }

    fun getAllParks(): MutableList<Park> {
        val parks: MutableList<Park> = mutableListOf()
        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.ENTITY_PARK}"

        val result = database.rawQuery(query, null)
        if (result.moveToFirst()) { //Equivalente al isNotEmpty para queries
            do {
                val id = result.getColumnIndex(Constants.P_ID)
                val name = result.getColumnIndex(Constants.P_NAME)
                val isFavorite = result.getColumnIndex(Constants.P_IS_FAVORITE)

                if (id != -1 && name != -1 && isFavorite != -1){
                    val park = Park(
                        id = result.getLong(id),
                        name = result.getString(name),
                        isFavorite = result.getInt(isFavorite) == 1 // 1 == true, 0 == false
                    )
                    parks.add(park)
                }
            } while (result.moveToNext())  // Mientras haya resultados
        }

        result.close()
        return parks

    }

    fun updatePark(park: Park): Boolean {
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.P_NAME, park.name)
            put(Constants.P_IS_FAVORITE, park.isFavorite)
        }
        val result = database.update(
            Constants.ENTITY_PARK,
            contentValues,
            "${Constants.P_ID} = ${park.id}",
            null
        )
        return result == 1
    }

    fun deletePark(park: Park): Boolean {
        val database = this.writableDatabase
        val result = database.delete(
            Constants.ENTITY_PARK,
            "${Constants.P_ID} = ${park.id}",
            null
        )
        return result == 1
    }

}