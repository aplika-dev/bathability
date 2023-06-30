package com.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "beach")
data class BeachEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo("city_ibge_id")
    val cityIbgeId: String,
    @ColumnInfo("city")
    val city: String,
    @ColumnInfo("collect_point")
    val collectPoint: String,
    @ColumnInfo("beach")
    val beach: String,
    @ColumnInfo("location")
    val location: String,
    @ColumnInfo("latitude")
    val latitude: Double,
    @ColumnInfo("longitude")
    val longitude: Double
)
