package com.example.practicandoxml28_10.di

import com.example.practicandoxml28_10.domain.useCases.AddPoliticoUseCase
import com.example.practicandoxml28_10.domain.useCases.CargarPoliticoUseCase
import com.example.practicandoxml28_10.domain.useCases.DamePresidenteUseCase
import com.example.practicandoxml28_10.domain.useCases.DelPoliticosUseCase

object AppModule {
    val damePresidenteUseCase: DamePresidenteUseCase = DamePresidenteUseCase()
    val addPoliticoUseCase: AddPoliticoUseCase = AddPoliticoUseCase()
    val delPoliticoUseCase: DelPoliticosUseCase = DelPoliticosUseCase()
    val cargarPoliticiosUseCase: CargarPoliticoUseCase = CargarPoliticoUseCase()
//
}