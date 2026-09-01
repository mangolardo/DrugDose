package com.example.drugdose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.sqrt

//classe per la gestione del calcolo della dose sulla base delle diverse indicazioni mediche
@Serializable
sealed class DosageRule {
    abstract val minAge: Int
    abstract val maxAge: Int
    abstract val maxDose: Double
    abstract fun calculateDose(profile: Profile): Pair<Double, Double>

    @Serializable
    @SerialName("WeightBased")
    data class WeightBased(
        override val minAge: Int,
        override val maxAge: Int,
        override val maxDose: Double,
        val doseAmount: Double,
    ) : DosageRule() {
        override fun calculateDose(profile: Profile): Pair<Double, Double> {
            val dose = this.doseAmount * profile.weight
            return Pair(dose, profile.weight * maxDose)
        }

    }

    @Serializable
    @SerialName("FixedDose")
    data class FixedDose(
        override val minAge: Int,
        override val maxAge: Int,
        override val maxDose: Double,
        val quantity: Double,
    ) : DosageRule() {
        override fun calculateDose(profile: Profile): Pair<Double, Double> {
            return Pair(quantity, maxDose)
        }

    }

    @Serializable
    @SerialName("BodySurfaceAreaBased")
    data class BodySurfaceAreaBased(
        override val minAge: Int,
        override val maxAge: Int,
        override val maxDose: Double,
        val doseAmount: Double,
    ) : DosageRule() {
        override fun calculateDose(profile: Profile): Pair<Double, Double> {
            val bsa = sqrt(profile.height * profile.weight / 3600)
            val dose = this.doseAmount * bsa
            return Pair(dose, dose * maxDose)
        }
    }

    @Serializable
    @SerialName("WeightBracketBased")
    data class WeightBracketBased(
        override val minAge: Int,
        override val maxAge: Int,
        override val maxDose: Double,
        val brackets: List<WeightBracket>,
        val maxWeightKg: Double = brackets.last().maxWeightKg,
        val minWeightKg: Double = brackets[0].minWeightKg
    ) : DosageRule() {
        override fun calculateDose(profile: Profile): Pair<Double, Double> {
            val weight = profile.weight
            if (weight < minWeightKg) return Pair(0.0, 0.0)
            for (bracket in brackets) {
                if (weight <= bracket.maxWeightKg)
                    return Pair(bracket.doseAmount, bracket.maxDose)
            }
            return Pair(-1.0, -1.0)
        }
    }
}

@Serializable
data class WeightBracket(
    val minWeightKg: Double,
    val maxWeightKg: Double,
    val doseAmount: Double,
    val maxDose: Double
)
