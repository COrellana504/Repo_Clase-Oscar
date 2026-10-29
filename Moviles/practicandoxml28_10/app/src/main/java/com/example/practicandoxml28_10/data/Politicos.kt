package com.example.practicandoxml28_10.data

import com.example.practicandoxml28_10.domain.model.Politico
import kotlin.random.Random


object Politicos {

private val politicos = mutableListOf(
    Politico("Cristopher", "Orellana", 20),
    Politico("Joseph", "Rodriguez", 22),
    Politico("Nathalie", "Orellana", 17)
)


fun damePresidente() =politicos[1]

    fun dameLista() = politicos.toList()

    fun add(p : Politico){
        if(Random.nextDouble() > 0.60)
        throw RuntimeException("Error al añadir")
        politicos.add(p)
    }

    fun del(p : Politico){
        if( Random.nextDouble() > 0.40)
            throw RuntimeException("Error al borrar")
            politicos.remove(p)
    }

}