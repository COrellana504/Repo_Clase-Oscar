package EjercicioRecyclerSimple.domain.useCases

import EjercicioRecyclerSimple.data.Gente
import EjercicioRecyclerSimple.domain.model.Autistas

class CargarAutistaUseCase {
    fun cargarAutista() = Gente.dameLista()
}

