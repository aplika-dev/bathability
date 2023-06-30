package com.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "collect_point")
data class CollectPointEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "should_hide")
    val shouldHide: Boolean,
    @ColumnInfo(name = "latitude")
    val latitude: Double,
    @ColumnInfo(name = "longitude")
    val longitude: Double,
    @ColumnInfo(name = "beachId")
    val beachId: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "is_bathable")
    val isBathable: Boolean?
)
