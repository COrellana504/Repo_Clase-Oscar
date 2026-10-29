package com.example.practicandoxml28_10.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practicandoxml28_10.domain.model.Politico
import com.example.practicandoxml28_10.domain.useCases.AddPoliticoUseCase
import com.example.practicandoxml28_10.domain.useCases.CargarPoliticoUseCase
import com.example.practicandoxml28_10.domain.useCases.DamePresidenteUseCase
import com.example.practicandoxml28_10.domain.useCases.DelPoliticosUseCase

class MainViewModel (
private val damePresidenteUseCase: DamePresidenteUseCase,
    private val cargarPoliticoUseCase: CargarPoliticoUseCase,
    val addPoliticoUseCase: AddPoliticoUseCase,
    val delPoliticosUseCase: DelPoliticosUseCase
) : ViewModel() {

        private val _state = MutableLiveData<MainState>(MainState("inicio", listOf()))

        val state: LiveData<MainState> = _state

        init {
            handleCargarLista()
        }



    //eventos no devuelven nada

    fun handleCargarLista() {
        _state.value =_state.value?.copy(listapoliticos = cargarPoliticoUseCase.cargarLista())
    }



fun handledamepresidente() {

    val presi = damePresidenteUseCase.damePresidente()

_state.value = _state.value?.copy(presidente = presi.toString())
}



    fun handleAdd(p : Politico){

        try {
            addPoliticoUseCase.addPolitico(p)
_state.value = _state.value?.copy(listapoliticos = cargarPoliticoUseCase.cargarLista())
        }catch(e : Exception){
_state.value =_state.value?.copy(error = e.message) }

    }



    fun handleDelete(p: Politico){
        try {
            delPoliticosUseCase.delPoliticos(p)
            _state.value = _state.value?.copy(listapoliticos = cargarPoliticoUseCase.cargarLista())
        }catch(e: Exception){
            _state.value = _state.value?.copy(error = e.message)
        }
    }



    fun handlelimpiarerror(){
        _state.value = _state.value?.copy(error = null)
    }

}

class MainViewModelFactory(
private val damePresidenteUseCase: DamePresidenteUseCase,
val cargarPoliticoUseCase: CargarPoliticoUseCase,
    val addPoliticoUseCase: AddPoliticoUseCase,
    val delPoliticosUseCase: DelPoliticosUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST") return MainViewModel(
                damePresidenteUseCase,
                cargarPoliticoUseCase,
                addPoliticoUseCase,
                delPoliticosUseCase,
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}