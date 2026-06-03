package edu.ucne.samil_delacruz_ap2_p1.domain.repository

import edu.ucne.samil_delacruz_ap2_p1.domain.model.Borrame
import kotlinx.coroutines.flow.Flow

interface BorrameRepository {
    fun observe(): Flow<List<Borrame>>
    suspend fun  get(id: Int): Borrame?
    suspend fun upsert(empleado: Borrame): Int
    suspend fun delete(id: Int)
    suspend fun  exists(id: Int): Boolean
}