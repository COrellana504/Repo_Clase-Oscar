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
) : ViewModel() {

    private val _state = MutableLiveData(MainState())
    val state: LiveData<MainState> = _state

    init {
        handleCargarLista()
    }

    fun handleCargarLista() {
        try {
            val lista = cargarlista.cargarAutista()

            _state.value = _state.value?.copy(
                listaAutistas = lista,
                error = null
            )
        } catch (e: Exception) {
            mostrarError(e.message ?: "Error al cargar los personajes")
        }
    }

    fun handleAddAleatorio() {
        try {
            val persona = Gente.dameAleatorioNoAnadido()
            handleAdd(persona)
        } catch (e: Exception) {
            mostrarError(
                e.message ?: "Error al añadir un personaje"
            )
        }
    }

    fun handleDameFavorito() {
        try {
            val favorito = autistafavorito.dameAutistafav()

            _state.value = _state.value?.copy(
                autistafavorito = favorito.nombre,
                error = null
            )
        } catch (e: Exception) {
            mostrarError(e.message ?: "Error al obtener el favorito")
        }
    }

    fun handleAdd(p: Autistas) {
        try {
            anadirAutista.addAutista(p)
            handleCargarLista()
        } catch (e: Exception) {
            mostrarError(e.message ?: "Error al añadir el personaje")
        }
    }

    fun handleDelete(p: Autistas) {
        try {
            borrarAutista.deleteAutista(p)
            handleCargarLista()
        } catch (e: Exception) {
            mostrarError(e.message ?: "Error al eliminar el personaje")
        }
    }

    private fun mostrarError(mensaje: String) {
        _state.value = _state.value?.copy(error = mensaje)
    }
}