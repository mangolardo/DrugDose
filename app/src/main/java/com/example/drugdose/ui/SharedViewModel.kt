package com.example.drugdose.ui

import android.os.Bundle
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

    val uiState = MutableStateFlow(UiState())
    val _uiState: StateFlow<UiState> = uiState
    fun selectProfile(profile: Profile){
        uiState.value = UiState(selectedProfile = profile)
    }
    init {
        initialize()
    }
    //room queries
    fun initialize(){
        viewModelScope.launch {
            uiState.value = UiState(
            profiles =  repo.profiles,
            meds = repo.medicines
            )
        }
    }

    fun selectMed(med: Medicine){
        uiState.value = uiState.value.copy(selectedMed = med)

    }
    fun resetMed(){
        uiState.value = uiState.value.copy(selectedMed = null)

    }
    fun resetUi(){
        uiState.value = UiState()

    }
}
data class UiState(
    val profiles : Flow<List<Profile>> ? = null,
    val meds : Flow<List<Medicine> > ? = null,
    val selectedProfile : Profile? = null,
    val selectedMed : Medicine? = null,
    val calculatedDose : Bundle? = null
)