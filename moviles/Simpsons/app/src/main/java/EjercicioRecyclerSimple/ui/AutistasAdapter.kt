
package EjercicioRecyclerSimple.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import EjercicioRecyclerSimple.domain.model.Autistas
import com.example.simpsons.R
class AutistasAdapter(
    private val listener: OnAutistaClickListener
) : RecyclerView.Adapter<AutistasAdapter.AutistaViewHolder>() {

    fun interface OnAutistaClickListener {
        fun onAutistaClick(autista: Autistas)
    }

    private val listaAutistas = mutableListOf<Autistas>()

    fun actualizarLista(nuevaLista: List<Autistas>) {
        listaAutistas.clear()
        listaAutistas.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AutistaViewHolder {
        val vista = LayoutInflater.from(parent.context)
            .inflate(R.layout.fila_autista, parent, false)

        return AutistaViewHolder(vista, listener)
    }

    override fun onBindViewHolder(
        holder: AutistaViewHolder,
        position: Int
    ) {
        holder.mostrar(listaAutistas[position])
    }

    override fun getItemCount(): Int = listaAutistas.size

    class AutistaViewHolder(
        itemView: View,
        private val listener: OnAutistaClickListener
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvNombre: TextView =
            itemView.findViewById(R.id.tvNombreAutista)

        private val tvOcupacion: TextView =
            itemView.findViewById(R.id.tvOcupacion)

        private var autistaActual: Autistas? = null

        init {
            itemView.setOnClickListener {
                autistaActual?.let { listener.onAutistaClick(it) }
            }
        }

        fun mostrar(autista: Autistas) {
            autistaActual = autista
            tvNombre.text = autista.nombre
            tvOcupacion.text = autista.matao
        }
    }
}