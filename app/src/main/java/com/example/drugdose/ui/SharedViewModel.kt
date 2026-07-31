package com.example.drugdose.ui

import android.os.Bundle
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drugdose.data.AppRepo
import com.example.drugdose.data.Medicine
import com.example.drugdose.data.Profile
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SharedViewModel(val repo : AppRepo) : ViewModel() {

    val uiState = MutableStateFlow(UiState(profiles =  repo.profiles,
        meds = repo.medicines))
    val _uiState: StateFlow<UiState> = uiState
    fun selectProfile(profile: Profile){
        uiState.value = uiState.value.copy(selectedProfile = profile,selectedMed = null)
    }

    //room queries


    fun selectMed(med: Medicine){
        uiState.value = uiState.value.copy(selectedMed = med)

    }
    fun resetMed(){
        uiState.value = uiState.value.copy(selectedMed = null)

    }
    fun resetUi(){
        uiState.value = UiState(
            profiles =  repo.profiles,
            meds = repo.medicines
        )

    }
    fun addProfile(profile: Profile){
        //fields checks
        viewModelScope.launch {
            repo.insertProfile(profile)
        }
    }

    fun setDose(dose: Double){
        uiState.value = uiState.value.copy(calculatedDose = dose)
    }
}
data class UiState(
    val profiles : Flow<List<Profile>> ,
    val meds : Flow<List<Medicine>> ,
    val selectedProfile : Profile? = null,
    val selectedMed : Medicine? = null,
    val calculatedDose : Double? = null,
    val isAlert : Boolean = false
)