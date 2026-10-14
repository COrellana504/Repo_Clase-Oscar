package EjercicioRecyclerSimple.ui

import EjercicioRecyclerSimple.data.Gente
import EjercicioRecyclerSimple.domain.model.Autistas
import EjercicioRecyclerSimple.domain.useCases.AddAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.CargarAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.DameAutistaFavoritoUseCase
import EjercicioRecyclerSimple.domain.useCases.DeleteAutistaUseCase
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel(
    private val autistafavorito: DameAutistaFavoritoUseCase,
    private val cargarlista: CargarAutistaUseCase,
    private val borrarAutista: DeleteAutistaUseCase,
    private val anadirAutista: AddAutistaUseCase
): ViewModel() {

    private val _state = MutableLiveData(
        MainState("Pulse el boton para ver al favorito", emptyList())
    )

    val state: LiveData<MainState> = _state

    init {
        handleCargarLista()
    }

    fun handleDameFavorito() {

        val autistafav = autistafavorito.dameAutistafav()

        _state.value = _state.value?.copy(
             autistafavorito = autistafav.nombre)
    }

    fun handleCargarLista() {
        _state.value = state.value?.copy(
            autistafavorito = cargarlista.cargarAutista().toString()
        )
    }

    fun handleAdd(p: Autistas) {
        try{
            anadirAutista.addAutista(p)
            handleCargarLista()
        }catch(e: Exception) {
            _state.value = state.value?.copy(
                error = e.message ?: "Error al añadir una persona"
            )
        }
    }


    fun handleDelete(p: Autistas){
        try{
            borrarAutista.deleteAutista(p)
            handleCargarLista()
        }catch(e: Exception){
            _state.value = state.value?.copy(
                error = e.message?: "Error al eliminar esta persona"
            )
        }
    }



}