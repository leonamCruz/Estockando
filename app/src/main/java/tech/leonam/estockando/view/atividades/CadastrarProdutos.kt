package tech.leonam.estockando.view.atividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import tech.leonam.estockando.databinding.ActivityCadastrarProdutosBinding

class CadastrarProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarProdutosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.check.isVisible = false
        cadastrar()
        clickPegadorDeCodigoDeBarras()
    }

    private fun clickPegadorDeCodigoDeBarras() {
        binding.imageCodeButton.setOnClickListener {
            startActivity(Intent(this, CodigoDeBarras::class.java))
        }
    }

    private fun cadastrar() {
        TODO()
    }
}