package edu.ucne.samil_delacruz_ap2_p1.domain.usecase

import edu.ucne.samil_delacruz_ap2_p1.data.repository.AdmonestacionRepositoryImpl
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import javax.inject.Inject

class GetAdmonestacionUseCase @Inject constructor(
    private val repositoryImpl: AdmonestacionRepositoryImpl
){
    suspend operator fun invoke(id: Int) : Admonestacion? = repositoryImpl.GetById(id)
}