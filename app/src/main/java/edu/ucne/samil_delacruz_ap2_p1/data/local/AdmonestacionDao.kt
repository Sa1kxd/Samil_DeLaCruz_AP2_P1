package edu.ucne.samil_delacruz_ap2_p1.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface AdmonestacionDao {

    @Upsert
    suspend fun upsert(entity: AdmonestacionEntity)

    @Query("SELECT * FROM admonestacion ORDER BY admonestacionid DESC")
    fun ObserveAll() : Flow<List<AdmonestacionEntity>>

    @Query("SELECT * FROM admonestacion WHERE admonestacionid = :id")
    suspend fun GetById(id: Int): AdmonestacionEntity?

    @Query("DELETE FROM admonestacion WHERE admonestacionid = :id")
    suspend fun DeleteById(id: Int)


}