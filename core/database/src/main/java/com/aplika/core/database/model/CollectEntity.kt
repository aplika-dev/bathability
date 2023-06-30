package com.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "collect")
data class CollectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    @ColumnInfo("beach_id")
    val beachId: String,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("bathability_situation")
    val bathabilitySituation: String?,
    @ColumnInfo("rain_situation")
    val rainSituation: String?,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int
)
