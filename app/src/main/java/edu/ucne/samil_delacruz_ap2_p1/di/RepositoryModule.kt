package edu.ucne.samil_delacruz_ap2_p1.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.samil_delacruz_ap2_p1.data.repository.AdmonestacionRepositoryImpl
import edu.ucne.samil_delacruz_ap2_p1.domain.repository.AdmonestacionesRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule{

    @Binds
    @Singleton
    abstract fun binEmpleadoRepository(
        impl: AdmonestacionRepositoryImpl
    ): AdmonestacionesRepository
}