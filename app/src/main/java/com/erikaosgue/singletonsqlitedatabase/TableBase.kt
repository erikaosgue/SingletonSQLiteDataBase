package com.erikaosgue.singletonsqlitedatabase

import android.database.sqlite.SQLiteDatabase

abstract class TableBase {

    abstract fun createTable(db: SQLiteDatabase)

    fun dropTable(db: SQLiteDatabase) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
    }
}
