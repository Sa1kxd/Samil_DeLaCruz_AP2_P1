package edu.ucne.samil_delacruz_ap2_p1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [AdmonestacionEntity::class],
    version = 1,
    exportSchema = false
)


abstract class AdmonestacionDatabase : RoomDatabase(){
    abstract fun admonestacionDao() : AdmonestacionDao
}