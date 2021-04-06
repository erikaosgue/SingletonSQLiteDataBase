package com.erikaosgue.singletonsqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DataBaseHelper(private val context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {


    companion object {

        var database: SQLiteDatabase? = null
        fun initDatabase(context: Context) {
            database = DataBaseHelper(context).writableDatabase
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null) {
            val tableAppData = TableAppData(context)
            tableAppData.createTable(db)
        }
        Log.e("DB", "database created")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (db != null) {
            val tableAppData = TableAppData(context)
            tableAppData.dropTable(db)
        }
        onCreate(db)
        Log.e("DB", "database upgrade")
    }
}
