package com.example.drugdose.ui

import com.example.drugdose.data.Medicine
import com.example.drugdose.data.Profile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel() {

    val uiState = MutableStateFlow(UiState())
    val _uiState: StateFlow<UiState> = uiState
    fun selectProfile(profile: Profile){
        uiState.value = UiState(selectedProfile = profile)
    }
    init {
        getMeds()
        getProfiles()
    }
    //room queries
    fun getProfiles(){}
    fun getMeds(){}

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
    val profiles : List<Profile> = emptyList(),
    val meds : List<Medicine> = emptyList(),
    val selectedProfile : Profile? = null,
    val selectedMed : Medicine? = null
)