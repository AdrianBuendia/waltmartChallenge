package com.goodaysolutions.waltmartchallenge.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun item(): ItemDao
}
