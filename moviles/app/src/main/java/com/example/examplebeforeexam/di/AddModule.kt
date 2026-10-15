package com.example.examplebeforeexam.di

import com.example.examplebeforeexam.domain.usecases.AddPoliticoUseCase
import com.example.examplebeforeexam.domain.usecases.CargarListaUseCase
import com.example.examplebeforeexam.domain.usecases.DelPoliticosUseCase
import com.example.examplebeforeexam.domain.usecases.PrimeroDeLaListaUseCase

object AddModule {

    val añadirpoliticoDI: AddPoliticoUseCase = AddPoliticoUseCase()
    val eliminarpoliticoDI: DelPoliticosUseCase = DelPoliticosUseCase()
    val mostrarlista: CargarListaUseCase = CargarListaUseCase()
    val mostrarprimero: PrimeroDeLaListaUseCase = PrimeroDeLaListaUseCase()


}