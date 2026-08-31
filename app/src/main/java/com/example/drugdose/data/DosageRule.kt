package com.example.drugdose.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.math.sqrt

@Serializable
sealed class DosageRule{
    abstract val minAge : Int
    abstract val maxAge : Int
    abstract fun calculateDose(profile: Profile) : Double
   @Serializable
  @SerialName("WeightBased")
    data class WeightBased(
        override val minAge: Int,
        override val maxAge: Int,
        val maxDose: Double,
        val doseAmount: Double,
    ) : DosageRule() {
    override fun calculateDose(profile: Profile): Double {
        return this.doseAmount * profile.weight

    }
    }
    @Serializable
    @SerialName("FixedDose")
    data class FixedDose(
        override val minAge: Int,
        override val maxAge: Int,
        val quantity: Double,
    ) : DosageRule(){
        override fun calculateDose(profile: Profile): Double {
           return quantity
        }
    }
    @Serializable
    @SerialName("BodySurfaceAreaBased")
    data class BodySurfaceAreaBased(
        override val minAge: Int,
        override val maxAge: Int,
        val maxDose: Double,
        val doseAmount: Double,
    ) : DosageRule(){
        override fun calculateDose(profile: Profile): Double {
            val bsa = sqrt(profile.height * profile.weight / 3600)
           return this.doseAmount * bsa
        }
    }
    @Serializable
    @SerialName("WeightBracketBased")
    data class WeightBracketBased(
        override val minAge: Int,
        override val maxAge: Int,
       val maxDose: Double,
        val brackets: List<WeightBracket>,
        val maxWeightKg: Double = brackets.last().maxWeightKg,
        val minWeightKg: Double = brackets[0].minWeightKg
    ) : DosageRule(){
        override fun calculateDose(profile: Profile): Double {
            val weight = profile.weight
            if(weight < minWeightKg ) return 0.0
            for(bracket in brackets){
                if(weight<bracket.maxWeightKg)
                    return bracket.doseAmount
            }
            return -1.0
        }
    }
}
@Serializable
data class WeightBracket(
    val minWeightKg: Double,
    val maxWeightKg: Double,
    val doseAmount: Double
)
