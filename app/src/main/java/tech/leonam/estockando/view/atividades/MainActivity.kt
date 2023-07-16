package tech.leonam.estockando.view.atividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.estockando.R
import tech.leonam.estockando.controller.Produtos
import tech.leonam.estockando.databinding.ActivityMainBinding
import tech.leonam.estockando.model.CadastrarDAO
import tech.leonam.estockando.view.adaptadores.AdaptadorDePaginaPesquisa

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setarNome()
        clickPesquisa()
        cadastroForcado()
    }

    private fun cadastroForcado() {
        val produto = Produtos()
        with(produto) {
            nomeDoProduto = "Teste"
            descricaoDoProduto = "Caraleo"
            qntDoProduto = "0"
            dataCadastro = "22/09/2000"
            preco = "R$10,00"
        }
        val cadastrarDAO = CadastrarDAO(this, produto)
        cadastrarDAO.saveInDatabase()
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

        val teste = nome!!.toCharArray()
        for (letra in teste) {
            println(letra)
        }
        val mensagem = "$saudacao$nome."

        binding.nomeDoCidadao.text = mensagem

    }
}