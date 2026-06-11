package edu.ucne.samil_delacruz_ap2_p1.domain.usecase

import edu.ucne.samil_delacruz_ap2_p1.data.repository.AdmonestacionRepositoryImpl
import javax.inject.Inject

class DeleteAdmonestacionUseCase @Inject constructor(
    private val repositoryImpl: AdmonestacionRepositoryImpl
) {
    suspend operator fun invoke(id: Int) = repositoryImpl.DeleteById(id)
}