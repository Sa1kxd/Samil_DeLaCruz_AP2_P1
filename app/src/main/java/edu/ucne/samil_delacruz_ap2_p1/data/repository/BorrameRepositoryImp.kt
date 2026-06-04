package edu.ucne.samil_delacruz_ap2_p1.data.repository

import edu.ucne.samil_delacruz_ap2_p1.data.local.dao.BorrameDao
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Borrame
import javax.inject.Inject
import edu.ucne.samil_delacruz_ap2_p1.domain.repository.BorrameRepository
import kotlinx.coroutines.flow.Flow

/*class BorrameRepositoryImpl @Inject constructor(private val localDataSource: BorrameDao): BorrameRepository {
    override fun observe(): Flow<List<Borrame>>
    {
    }

    override suspend fun  get(id: Int): Borrame?
    {
    }

    override suspend fun upsert(borrame: Borrame): Int
    {
    }

    override suspend fun delete(id: Int)
    {
    }

    override suspend fun  exists(id: Int): Boolean
    {
    }
}*/