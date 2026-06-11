package edu.ucne.samil_delacruz_ap2_p1.domain.repository

import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import kotlinx.coroutines.flow.Flow

interface AdmonestacionesRepository {
    fun observeAll(): Flow<List<Admonestacion>>
    suspend fun  GetById(id: Int): Admonestacion?
    suspend fun Upsert(admonestacion: Admonestacion): Int
    suspend fun DeleteById(id: Int)
}