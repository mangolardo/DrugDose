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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SharedViewModel(val repo : AppRepo) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState
    //inizializzazione dei profili e medicine quando il viewmodel viene creato
    //collect permette al viewmodel di ricevere i flow proveniente dalla repository ad ogni modifica dei dati sul database
    init {
        viewModelScope.launch {
            repo.profiles.collect { profiles ->
                _uiState.update { it.copy(profiles = profiles) } //aggiornamento thread-safe della ui, gestione delle concorrenza delle coroutine
            }
        }
        viewModelScope.launch { //segue il lifecycle del viewmodel
            repo.medicines.collect { meds ->
                _uiState.update { it.copy(meds = meds) }
            }
        }
    }

    fun selectProfile(profile: Profile){
        _uiState.update { it.copy(selectedProfile = profile, selectedMed = null) }
    }

    fun selectMed(med: Medicine){
        _uiState.update { it.copy(selectedMed = med) }
    }

    fun resetMed(){
        _uiState.update { it.copy(selectedMed = null) }
    }

    fun resetUi(){
        _uiState.update { 
            UiState(
                profiles = it.profiles,
                meds = it.meds,
                isAlert = false
            )
        }
    }

    fun addProfile(profile: Profile){
        viewModelScope.launch {
            repo.insertProfile(profile)
        }
    }


    fun updateProfile(profile: Profile){
        viewModelScope.launch {
            repo.updateProfile(profile)
        }
    }

}

data class UiState(
    val profiles : List<Profile> = emptyList(),
    val meds : List<Medicine> = emptyList(),
    val selectedProfile : Profile? = null,
    val selectedMed : Medicine? = null,
    val calculatedDose : Double? = null,
    val isAlert : Boolean = false
)