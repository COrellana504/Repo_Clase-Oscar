package com.example.examplebeforeexam.data

import com.example.examplebeforeexam.domain.model.Politico

object Politicos {

    private val politicos =mutableListOf(Politico("Nathalie", "Orellana", 17),
                                        Politico("Yessica", "Rodriguez", 40))


    fun dameelprimerdelalista() = politicos[1]
    fun damelistaentera() =politicos.toList()

    fun addpolitico(p: Politico){
        politicos.add(p)
    }

    fun delpolitico(p: Politico){
        politicos.remove(p)
    }

}