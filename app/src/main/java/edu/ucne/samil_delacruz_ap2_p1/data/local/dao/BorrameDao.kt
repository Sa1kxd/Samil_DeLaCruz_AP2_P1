package edu.ucne.samil_delacruz_ap2_p1.data.local.dao

import androidx.room.Dao
import androidx.room.Update
import androidx.room.Upsert
import edu.ucne.samil_delacruz_ap2_p1.data.local.entity.BorrameEntity

@Dao
interface BorrameDao {

    @Upsert
    suspend fun upsert(entity: BorrameEntity)
}