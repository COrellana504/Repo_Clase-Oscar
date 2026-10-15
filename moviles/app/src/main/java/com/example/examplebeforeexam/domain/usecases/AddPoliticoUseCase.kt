package com.example.examplebeforeexam.domain.usecases

import com.example.examplebeforeexam.data.Politicos
import com.example.examplebeforeexam.domain.model.Politico

class AddPoliticoUseCase {

    fun addPolitico(p: Politico) = Politicos.addpolitico(p)

}