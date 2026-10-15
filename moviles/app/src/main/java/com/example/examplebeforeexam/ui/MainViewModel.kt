package com.example.examplebeforeexam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import com.example.examplebeforeexam.domain.usecases.AddPoliticoUseCase
import com.example.examplebeforeexam.domain.usecases.CargarListaUseCase
import com.example.examplebeforeexam.domain.usecases.DelPoliticosUseCase
import com.example.examplebeforeexam.domain.usecases.PrimeroDeLaListaUseCase

data class MainViewModel(
    private val damePresidenteMODEL: PrimeroDeLaListaUseCase,
    private val dameListaPoliticosMODEL: CargarListaUseCase,
    private val anadirPoliticoMODEL: AddPoliticoUseCase,
    private val delPoliticoMODEL: DelPoliticosUseCase
) : ViewModel() {


    private val _state = MutableLiveData<MainState>(MainState("Inicio", listOf(), "error"))


    val state: LiveData<MainState> = _state


    init {
        handleCargarPrimeroLista()
    }

    fun handleCargarPrimeroLista() {

        var presi = dameListaPoliticosMODEL.cargarPrimeroDeLaLista()

        _state.value = _state.value?.copy(
            mensajeinicial = presi.toString()
        )
    }



}