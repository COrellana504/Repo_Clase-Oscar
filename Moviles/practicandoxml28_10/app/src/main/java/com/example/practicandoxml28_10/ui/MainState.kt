package com.example.practicandoxml28_10.ui

import com.example.practicandoxml28_10.domain.model.Politico

data class MainState (
    val presidente: String,
    val listapoliticos : List<Politico>,
    val error : String? = null
)