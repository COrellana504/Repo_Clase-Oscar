package com.example.practicandoxml28_10.domain.useCases

import com.example.practicandoxml28_10.data.Politicos
import com.example.practicandoxml28_10.domain.model.Politico

class DelPoliticosUseCase {

    fun delPoliticos(p : Politico) = Politicos.del(p)
}