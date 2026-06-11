package edu.ucne.samil_delacruz_ap2_p1.data.mapper

import edu.ucne.samil_delacruz_ap2_p1.data.local.AdmonestacionEntity
import edu.ucne.samil_delacruz_ap2_p1.domain.model.Admonestacion

fun Admonestacion.toEntity() : AdmonestacionEntity = AdmonestacionEntity(
    admonestacionId = admonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto
)

fun AdmonestacionEntity.toDomain() : Admonestacion = Admonestacion(
    admonestacionId = admonestacionId,
    nombres = nombres,
    razon = razon,
    monto = monto
)



