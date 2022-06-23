package org.d3if2024.assesment2.db

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SuhuDao {
    //
    @Insert
    fun insert(suhuDao: SuhuEntity)

    @Query("SELECT * FROM suhu ORDER BY id ASC")
    fun getKonversiSuhu(): LiveData<List<SuhuEntity?>>

    @Query("DELETE FROM suhu")
    fun clearData()

    @Update
    fun updateData(suhu: SuhuEntity)

}