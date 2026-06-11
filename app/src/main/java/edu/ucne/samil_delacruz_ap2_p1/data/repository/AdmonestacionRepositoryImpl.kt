package edu.ucne.samil_delacruz_ap2_p1.data.repository

import edu.ucne.samil_delacruz_ap2_p1.data.local.AdmonestacionDao
import edu.ucne.samil_delacruz_ap2_p1.data.mapper.toDomain
import edu.ucne.samil_delacruz_ap2_p1.data.mapper.toEntity
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import edu.ucne.samil_delacruz_ap2_p1.domain.repository.AdmonestacionesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AdmonestacionRepositoryImpl @Inject constructor(
    private val localDao : AdmonestacionDao
) : AdmonestacionesRepository{

    override fun observeAll(): Flow<List<Admonestacion>> {
        return localDao.ObserveAll().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun GetById(id: Int): Admonestacion? {
        return localDao.GetById(id)?.toDomain()
    }

    override suspend fun Upsert(admonestacion: Admonestacion): Int {
       localDao.upsert(admonestacion.toEntity())
        return admonestacion.admonestacionId?:0
    }

    override suspend fun DeleteById(id: Int) {
      return localDao.DeleteById(id)
    }

}