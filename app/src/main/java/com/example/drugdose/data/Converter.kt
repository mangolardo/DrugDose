package com.example.drugdose.data

import androidx.room3.ColumnTypeConverter
import kotlinx.serialization.json.Json

//classe converter per convertire tipo complessi in stringhe json e viceversa
class Converter {
    private val jsonFormatter = Json { ignoreUnknownKeys = true }

    @ColumnTypeConverter
    fun fromRulesList(rules: List<DosageRule>?): String {
        return rules?.let { jsonFormatter.encodeToString(it) } ?: "[]"
    }

    @ColumnTypeConverter
    fun toRulesList(data: String?): List<DosageRule> {
        return if (data.isNullOrEmpty()) emptyList() else jsonFormatter.decodeFromString(data)
    }
    @ColumnTypeConverter
    fun fromDosageList(dosages: List<Dosage>?): String {
        return dosages?.let { jsonFormatter.encodeToString(it) } ?: "[]"
    }

    @ColumnTypeConverter
    fun toDosageList(data: String?): List<Dosage> {
        return if (data.isNullOrEmpty()) emptyList() else jsonFormatter.decodeFromString(data)
    }
}