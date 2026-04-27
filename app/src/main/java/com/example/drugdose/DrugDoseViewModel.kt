package com.example.drugdose

//maybe reuse for form viewmodel instead of list???
//class DrugDoseViewModel(meds : List<Medicine>) : ViewModel(){
////    val uistate = MutableStateFlow(UiState())
////    init {
////        initList(meds)
////    }
//    var _meds : List<Medicine> = emptyList()
//    init{
//        _meds = meds
//    }

//    private fun initList(meds : List<Medicine>) {
//        uistate.value = UiState(
//            meds = meds
//        )
//    }
//}
//data class UiState(
//    val meds : List<Medicine> = emptyList()
//)

//ui state for navigation, form values to remember
//