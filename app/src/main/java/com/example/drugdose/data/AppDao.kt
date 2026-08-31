package com.example.drugdose.data

import androidx.room3.Dao
import androidx.room3.Delete
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
//    @Query("SELECT * FROM Medicines ORDER BY name ASC ")
//    fun getAllMeds() : Flow<List<Medicine>>
//    @Query("SELECT * FROM Medicines WHERE id = :id")
//    fun getMed(id:Int) : Medicine
    @Query("SELECT * FROM Profiles ORDER BY name ASC")
    fun getAllProfiles() : Flow<List<Profile>>
    @Query("SELECT * FROM Medicines ORDER BY name ASC")
    fun getAllMeds() : Flow<List<Medicine>>

    @Query("SELECT COUNT(*) FROM Medicines")
    suspend fun getMedsCount(): Int

    @Insert
    suspend fun insertProfile(profile:Profile)

    @Insert
    suspend fun insertAll(medicines : List<Medicine>)
    @Delete
    suspend fun deleteProfile(profile: Profile)
    @Update
    suspend fun updateProfile(profile:Profile)


}