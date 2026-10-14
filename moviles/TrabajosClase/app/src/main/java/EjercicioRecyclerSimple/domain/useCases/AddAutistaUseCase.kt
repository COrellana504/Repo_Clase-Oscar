package EjercicioRecyclerSimple.domain.useCases

import EjercicioRecyclerSimple.data.Gente
import EjercicioRecyclerSimple.domain.model.Autistas

class AddAutistaUseCase {

    fun addAutista(a: Autistas) = Gente.addGente(a)

}