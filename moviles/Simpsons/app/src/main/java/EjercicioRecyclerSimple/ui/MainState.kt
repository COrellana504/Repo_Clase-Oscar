package EjercicioRecyclerSimple.ui

import EjercicioRecyclerSimple.domain.model.Autistas

data class MainState(
    val autistafavorito: String =
        "Pulsa el botón para ver al favorito",
    val listaAutistas: List<Autistas> = emptyList(),
    val error: String? = null
)