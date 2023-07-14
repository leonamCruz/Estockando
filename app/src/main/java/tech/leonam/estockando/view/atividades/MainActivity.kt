package tech.leonam.estockando.view.atividades

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setarNome()
    }

    private fun setarNome() {
        val sharedPreferences = getSharedPreferences("a",MODE_PRIVATE)
        val saudacao = getString(R.string.ol)
        val nome = sharedPreferences.getString("chave","nao achado")

        val teste = nome!!.toCharArray()
        for (letra in teste){
            println(letra)
        }
        val mensagem = "$saudacao$nome."

        binding.nomeDoCidadao.text = mensagem
    }
}