package com.goodaysolutions.waltmartchallenge.core.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getAll(): List<Item>

    @Insert
    fun insertAll(vararg items: Item)

    @Delete
    fun deleteAll(vararg items: Item)

}