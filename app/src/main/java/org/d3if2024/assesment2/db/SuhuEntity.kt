package org.d3if2024.assesment2.db

import android.content.ClipData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.RowId
import java.text.NumberFormat

@Entity(tableName = "suhu")
data class SuhuEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var suhuCelcius: Float,
    var hasilConvertCelcius: String
        )