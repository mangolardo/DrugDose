package com.example.drugdose.data

import kotlinx.coroutines.flow.Flow

class AppRepo (private val dao: AppDao){
    val medicines : Flow<List<Medicine>> = dao.getAllMeds()
    val profiles : Flow<List<Profile>> = dao.getAllProfiles()

    fun getMed(id : Int) {
    dao.getMed(id)
    }
    suspend fun insertProfile(name:String,age:Int, weight:Short,height:Short,pregnant:Boolean){
        dao.insertProfile(Profile(
            name = name,
            age = age,
            weight=weight,
            height=height,
            pregnant=pregnant
        )
        )
    }
    suspend fun updateProfile(profile:Profile){
        dao.updateProfile(profile)
    }
    suspend fun deleteProfile(profile:Profile){
        dao.deleteProfile(profile)
    }
}