package tech.leonam.estockando.view.atividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.ActivityMainBinding
import tech.leonam.estockando.viewModel.Pesquisar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setarNome()
        clickPesquisa()
        clickCadastrar()
    }

    private fun clickCadastrar() {
        binding.bolaCadastrar.setOnClickListener {
            startActivity(Intent(this,CadastrarProdutos::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        atualizarInfos()
    }
    private fun atualizarInfos() {
        binding.textoQntDeProdutos.text = Pesquisar(this).pegaQntdDeProdutos().toString()
    }

    private fun clickPesquisa() {
        binding.cardPesquisa.setOnClickListener {
            startActivity(Intent(this, Pesquisa::class.java))
        }
    }

    private fun setarNome() {
        val sharedPreferences = getSharedPreferences("a", MODE_PRIVATE)
        val saudacao = getString(R.string.ol)
        val nome = sharedPreferences.getString("chave", "nao achado")

        val mensagem = "$saudacao$nome."

        binding.nomeDoCidadao.text = mensagem

    }
}