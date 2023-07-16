package tech.leonam.estockando.view.atividades

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import tech.leonam.estockando.R
import tech.leonam.estockando.controller.Produtos
import tech.leonam.estockando.controller.util.UtilImage
import tech.leonam.estockando.databinding.ActivityMainBinding
import tech.leonam.estockando.model.CadastrarDAO

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
            nomeDoProduto = "Zé Buceta"
            descricaoDoProduto = "Caraleo"
            qntDoProduto = "0"
            dataCadastro = "22/09/2000"
            preco = "10.00"
            produto.imagemDoProduto=UtilImage.deBitmapParaBase64(UtilImage.drawableToBitmap(
                AppCompatResources.getDrawable(this@MainActivity,R.drawable.champion)!!
            ))
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