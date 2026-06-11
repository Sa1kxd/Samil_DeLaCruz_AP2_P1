package edu.ucne.samil_delacruz_ap2_p1.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.samil_delacruz_ap2_p1.data.local.AdmonestacionDao
import edu.ucne.samil_delacruz_ap2_p1.data.local.AdmonestacionDatabase
import android.content.Context
import androidx.room.Room
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule{

    @Provides
    @Singleton
    fun provideEmpleadoDatabase(
        @ApplicationContext context: Context
    ): AdmonestacionDatabase{
        return Room.databaseBuilder(
            context,
            AdmonestacionDatabase::class.java,
            "empleado_database"
        ).build()
    }
    @Provides
    @Singleton
    fun provideEmpleado(database: AdmonestacionDatabase): AdmonestacionDao{
        return database.admonestacionDao()
    }
}