package edu.ucne.samil_delacruz_ap2_p1.domain.usecase
import edu.ucne.samil_delacruz_ap2_p1.data.repository.AdmonestacionRepositoryImpl
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import javax.inject.Inject

class UpsertAdmonestacionUseCase @Inject constructor(
    private val repositoryImpl: AdmonestacionRepositoryImpl
){
    suspend operator fun invoke(admonestacion: Admonestacion): Result<Int>{
        val nombresValidate = ValidateNombres(admonestacion.nombres)
        if(!nombresValidate.isValid){
            return Result.failure(IllegalArgumentException(nombresValidate.error))
        }
        val razonValidate = ValidateRazon(admonestacion.razon)
        if(!razonValidate.isValid){
            return Result.failure(IllegalArgumentException(razonValidate.error))
        }
        val montoValidate = ValidateMonto(admonestacion.monto.toString())
        if(!montoValidate.isValid){
            return Result.failure(IllegalArgumentException(montoValidate.error))
        }
        return  runCatching { repositoryImpl.Upsert(admonestacion) }
    }
}