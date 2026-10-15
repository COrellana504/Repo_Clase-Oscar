package com.example.examplebeforeexam.domain.usecases

import com.example.examplebeforeexam.data.Politicos
import com.example.examplebeforeexam.domain.model.Politico

class DelPoliticosUseCase {

    fun delPolitico(p: Politico) = Politicos.delpolitico(p)

}