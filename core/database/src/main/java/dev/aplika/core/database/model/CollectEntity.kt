package dev.aplika.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "collect", primaryKeys = ["beach_id", "date"])
data class CollectEntity(
    @ColumnInfo("beach_id")
    val beachId: String,
    @ColumnInfo("date")
    val date: Long,
    @ColumnInfo("bathability_situation")
    val bathabilitySituation: String?,
    @ColumnInfo("rain_situation")
    val rainSituation: String?,
    @ColumnInfo("escherichia_coli_factor")
    val escherichiaColiFactor: Int
)
