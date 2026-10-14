package EjercicioRecyclerSimple.ui

import EjercicioRecyclerSimple.domain.model.Autistas

data class MainState (
    val autistafavorito: String,
    val NombredelAustista: List<Autistas>,
    val error: String? = null
)