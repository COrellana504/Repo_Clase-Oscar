package com.example.practicandoxml28_10.domain.useCases

import com.example.practicandoxml28_10.data.Politicos
import com.example.practicandoxml28_10.domain.model.Politico

class AddPoliticoUseCase {



    fun addPolitico(p : Politico) =Politicos.add(p)
}