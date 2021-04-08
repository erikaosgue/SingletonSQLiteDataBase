package com.erikaosgue.singletonsqlitedatabase

import android.app.Application

// Initialize the Database
class MyApplication: Application() {
  override fun onCreate() {
    super.onCreate()
    DataBaseHelper.initDatabase(this)
  }
}