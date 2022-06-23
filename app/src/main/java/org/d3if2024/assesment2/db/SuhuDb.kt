package org.d3if2024.assesment2.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SuhuEntity::class], version = 1, exportSchema = false)
abstract class SuhuDb : RoomDatabase() {

//    abstract fun itemDao(): SuhuDao
    abstract val itemDao: SuhuDao

    companion object {
        @Volatile
        private var INSTANCE: SuhuDb? = null

        fun getInstance(context: Context): SuhuDb {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SuhuDb::class.java,
                        "suhu.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}