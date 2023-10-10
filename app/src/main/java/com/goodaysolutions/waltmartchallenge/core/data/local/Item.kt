package com.goodaysolutions.waltmartchallenge.core.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "idItem") val idItem: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "double") val price: Double,
)

