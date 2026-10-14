package EjercicioRecyclerSimple.domain.useCases

import EjercicioRecyclerSimple.data.Gente
import EjercicioRecyclerSimple.domain.model.Autistas

class DeleteAutistaUseCase {
    fun deleteAutista(d: Autistas) = Gente.deleteGente(d)
}