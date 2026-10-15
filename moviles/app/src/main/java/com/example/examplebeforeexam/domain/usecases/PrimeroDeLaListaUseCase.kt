package com.example.examplebeforeexam.domain.usecases

import com.example.examplebeforeexam.data.Politicos

class PrimeroDeLaListaUseCase {

    fun cargarLista() = Politicos.damelistaentera()

}