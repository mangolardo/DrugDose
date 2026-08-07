package com.example.drugdose.data

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json

class AppRepo (private val dao: AppDao, meds : List<Medicine> ){
    val medicines : List<Medicine> = meds
  //  val medicines : Flow<List<Medicine>> = dao.getAllMeds()
    val profiles : Flow<List<Profile>> = dao.getAllProfiles()

//    fun getMed(id : Int) {
//    dao.getMed(id)
//    }
    suspend fun insertProfile(profile: Profile){
        dao.insertProfile(profile)
    }
    suspend fun updateProfile(profile:Profile){
        dao.updateProfile(profile)
    }
    suspend fun deleteProfile(profile:Profile){
        dao.deleteProfile(profile)
    }
}