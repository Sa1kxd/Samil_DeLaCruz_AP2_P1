package edu.ucne.samil_delacruz_ap2_p1.domain.usecase

import edu.ucne.samil_delacruz_ap2_p1.data.repository.AdmonestacionRepositoryImpl
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveAdmonestacionUseCase @Inject constructor(
    private val repositoryImpl: AdmonestacionRepositoryImpl
) {
    operator fun invoke() : Flow<List<Admonestacion>> = repositoryImpl.observeAll()
}