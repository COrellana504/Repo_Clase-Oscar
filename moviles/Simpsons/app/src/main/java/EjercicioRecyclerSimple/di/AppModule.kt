package EjercicioRecyclerSimple.di

import EjercicioRecyclerSimple.domain.useCases.AddAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.CargarAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.DameAutistaFavoritoUseCase
import EjercicioRecyclerSimple.domain.useCases.DeleteAutistaUseCase

object AppModule {
    val dameAutistaFavoritoUseCase = DameAutistaFavoritoUseCase()
    val addAutistaUseCase = AddAutistaUseCase()
    val cargarAutistaUseCase = CargarAutistaUseCase()
    val deleteAutistaUseCase = DeleteAutistaUseCase()
}