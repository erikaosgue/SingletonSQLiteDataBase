package com.erikaosgue.singletonsqlitedatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.widget.Toast

class TableAppData(private val context: Context): TableBase() {

    override fun createTable(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COLUMN_NAME VARCHAR(256),$COLUMN_AGE INTEGER)"
        db.execSQL(createTable)
    }

    fun insertData(user: User) {

//        val database = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(COLUMN_NAME, user.name)
        contentValues.put(COLUMN_AGE, user.age)

        val result = DataBaseHelper.database?.insert(TABLE_NAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
    fun readData(): MutableList<User> {

        val list: MutableList<User> = ArrayList()


        val query = "Select * from $TABLE_NAME"

        val cursor = DataBaseHelper.database!!.rawQuery(query, null)
        try {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    val user = User()
                    user.id = cursor.getString(cursor.getColumnIndex(COLUMN_ID)).toInt()
                    user.name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                    user.age = cursor.getString(cursor.getColumnIndex(COLUMN_AGE)).toInt()
                    list.add(user)
                } while (cursor.moveToNext())
            }

        } catch (e: Exception) {
            e.printStackTrace()

        } finally {
            cursor.close()
        }
        return list
    }
}