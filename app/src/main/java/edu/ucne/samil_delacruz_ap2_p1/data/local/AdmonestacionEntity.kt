package edu.ucne.samil_delacruz_ap2_p1.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "admonestacion")
data class AdmonestacionEntity(
    @PrimaryKey (autoGenerate = true)
    val admonestacionId : Int,
    val nombres: String,
    val razon: String,
    val monto: Double
)
