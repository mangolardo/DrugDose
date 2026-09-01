package com.example.drugdose.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drugdose.data.AppRepo

//factory per permettere l'uso di un argomento nella creazione del viewmodel
class SharedViewModelFactory(private val repo: AppRepo) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SharedViewModel(repo) as T
    }
}