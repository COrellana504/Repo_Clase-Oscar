package com.example.simpsons

import EjercicioRecyclerSimple.domain.model.Autistas
import EjercicioRecyclerSimple.domain.useCases.AddAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.CargarAutistaUseCase
import EjercicioRecyclerSimple.domain.useCases.DameAutistaFavoritoUseCase
import EjercicioRecyclerSimple.domain.useCases.DeleteAutistaUseCase
import EjercicioRecyclerSimple.ui.AutistasAdapter
import EjercicioRecyclerSimple.ui.MainViewModel
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: AutistasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val root = findViewById<android.view.View>(R.id.main)

        ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
            val systemBars = insets.getInsets(
                WindowInsetsCompat.Type.systemBars()
            )
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.listado)
        val botonFavorito = findViewById<Button>(R.id.button)
        val botonAnadir = findViewById<Button>(R.id.buttonAdd)
        val textoFavorito = findViewById<TextView>(R.id.textView)

        viewModel = ViewModelProvider(
            this,
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(
                    modelClass: Class<T>
                ): T {
                    if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                        @Suppress("UNCHECKED_CAST")
                        return MainViewModel(
                            DameAutistaFavoritoUseCase(),
                            CargarAutistaUseCase(),
                            DeleteAutistaUseCase(),
                            AddAutistaUseCase()
                        ) as T
                    }
                    throw IllegalArgumentException("ViewModel desconocido")
                }
            }
        )[MainViewModel::class.java]

        adapter = AutistasAdapter(
            object : AutistasAdapter.OnAutistaClickListener {
                override fun onAutistaClick(autista: Autistas) {
                    viewModel.handleDelete(autista)
                }
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.state.observe(this) { state ->
            adapter.actualizarLista(state.listaAutistas)
            textoFavorito.text = state.autistafavorito

            state.error?.let { mensaje ->
                Toast.makeText(
                    this,
                    mensaje,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        botonFavorito.setOnClickListener {
            viewModel.handleDameFavorito()
        }

        botonAnadir.setOnClickListener {
            viewModel.handleAddAleatorio()
        }
    }
}