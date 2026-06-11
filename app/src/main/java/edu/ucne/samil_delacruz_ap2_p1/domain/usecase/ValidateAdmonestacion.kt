package edu.ucne.samil_delacruz_ap2_p1.domain.usecase

data class resultValidate(
    val isValid : Boolean,
    val error: String? = null
)

fun ValidateNombres(nombres: String): resultValidate {
        return when{
            nombres.isBlank() -> resultValidate(false, "Campo nombre no puede estar vacio")
            nombres.length < 0 -> resultValidate(false, "Campo nombre no puede contener menos de 3 caracteres")
            else -> resultValidate(true)
        }
}

fun ValidateRazon(razon: String): resultValidate {
    return when{
        razon.isBlank() -> resultValidate(false, "Campo razon no puede estar vacio")
        razon.length < 0 -> resultValidate(false, "Campo nombre no puede contener menos de 3 caracteres")
        else -> resultValidate(true)
    }
}

fun ValidateMonto(monto: String): resultValidate {
    return when{
        monto.isBlank() -> resultValidate(false, "Campo sueldo no puede estar vacio")
        monto.toDoubleOrNull()  == null-> resultValidate(false, "Campo sueldo solo puede contener numeros")
        monto.toDouble() <= 0 -> resultValidate(false, "Campo sueldo debe ser mayor a 0")
        else -> resultValidate(true)
    }
}