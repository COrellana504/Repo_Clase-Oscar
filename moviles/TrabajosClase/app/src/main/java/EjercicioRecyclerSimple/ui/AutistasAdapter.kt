
package EjercicioRecyclerSimple.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trabajosclase.R
import EjercicioRecyclerSimple.domain.model.Autistas

class AutistasAdapter(
    private val listener: OnAutistaClickListener
) : RecyclerView.Adapter<AutistasAdapter.AutistaViewHolder>() {

    interface OnAutistaClickListener {
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

    override fun getItemCount(): Int {
        return listaAutistas.size
    }

    class AutistaViewHolder(
        itemView: View,
        private val listener: OnAutistaClickListener
    ) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val tvNombre: TextView =
            itemView.findViewById(R.id.tvNombreAutista)

        private val tvOcupacion: TextView =
            itemView.findViewById(R.id.tvOcupacion)

        private lateinit var autista: Autistas

        init {
            itemView.setOnClickListener(this)
        }

        fun mostrar(autista: Autistas) {
            this.autista = autista

            tvNombre.text = autista.nombre
            tvOcupacion.text = autista.matao
        }

        override fun onClick(v: View?) {
            listener.onAutistaClick(autista)
        }
    }
}
