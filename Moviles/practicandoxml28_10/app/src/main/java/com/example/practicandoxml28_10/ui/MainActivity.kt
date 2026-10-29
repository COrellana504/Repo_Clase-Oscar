
package com.example.practicandoxml28_10.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practicandoxml28_10.R
import com.example.practicandoxml28_10.databinding.ReciclerviewMainBinding
import com.example.practicandoxml28_10.di.AppModule
import com.example.practicandoxml28_10.domain.model.Politico

class MainActivity : AppCompatActivity() {

    private val binding: ReciclerviewMainBinding by lazy {
        ReciclerviewMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(
            AppModule.damePresidenteUseCase,
            AppModule.cargarPoliticiosUseCase,
            AppModule.addPoliticoUseCase,
            AppModule.delPoliticoUseCase
        )
    }

    private lateinit var adapter: PoliticoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
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

        configRecycler()
        observarEstado()
        setupEventos()
    }

    private fun configRecycler() {
        binding.listadoreciclerview.layoutManager =
            LinearLayoutManager(this)

        adapter = PoliticoAdapter { politico ->
            viewModel.handleDelete(politico)
        }

        binding.listadoreciclerview.adapter = adapter
    }

    private fun setupEventos() {
        binding.button.setOnClickListener {
            viewModel.handledamepresidente()
        }

        binding.buttonAdd.setOnClickListener {
            val politico = Politico(
                nombre = "kiko",
                partido = "rivera"
            )
            viewModel.handleAdd(politico)
        }
    }

    private fun observarEstado() {
        viewModel.state.observe(this) { state ->
            binding.textView.text = state.presidente

            adapter.submitList(state.listapoliticos)

            state.error?.let { error ->
                Toast.makeText(
                    this,
                    error,
                    Toast.LENGTH_SHORT
                ).show()

                viewModel.handlelimpiarerror()
            }
        }
    }
}
