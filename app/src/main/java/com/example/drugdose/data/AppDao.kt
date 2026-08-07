package com.example.drugdose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
//    @Query("SELECT * FROM Medicines ORDER BY name ASC ")
//    fun getAllMeds() : Flow<List<Medicine>>
//    @Query("SELECT * FROM Medicines WHERE id = :id")
//    fun getMed(id:Int) : Medicine
    @Query("SELECT * FROM Profiles ORDER BY name ASC")
    fun getAllProfiles() : Flow<List<Profile>>
    @Insert
    suspend fun insertProfile(profile:Profile)
    @Delete
    suspend fun deleteProfile(profile: Profile)
    @Update
    suspend fun updateProfile(profile:Profile)
}