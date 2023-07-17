package tech.leonam.estockando.view.atividades

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import tech.leonam.estockando.R
import tech.leonam.estockando.controller.util.UtilTexto
import tech.leonam.estockando.databinding.ActivityBoasVindasBinding

class BoasVindas : AppCompatActivity() {
    private lateinit var binding: ActivityBoasVindasBinding
    private val KEY_FIRST_TIME = "first_time"
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("a", MODE_PRIVATE)
        binding = ActivityBoasVindasBinding.inflate(layoutInflater)

        if (sharedPreferences.getBoolean(KEY_FIRST_TIME, true)) {
            setContentView(binding.root)
            clickSalvarNome(sharedPreferences)
            salvarQueNaoEhAPrimeiraVez()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
    private fun salvarQueNaoEhAPrimeiraVez() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_FIRST_TIME, false)
        editor.apply()
    }

    private fun clickSalvarNome(sharedPreferences: SharedPreferences) {
        binding.botaoComecar.setOnClickListener {
            if (binding.nomeInicial.text.isBlank()) {
                val snackbar = Snackbar.make(
                    binding.root.rootView,
                    getString(R.string.insira_seu_nome_por_favor),
                    Snackbar.LENGTH_SHORT
                )
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    snackbar.setTextColor(getColor(R.color.branco))
                    snackbar.setBackgroundTint(getColor(R.color.preto))
                }
                snackbar.show()
            } else {
                val name = binding.nomeInicial.text.toString()

                val editor = sharedPreferences.edit()

                editor.apply {
                    val salvar = UtilTexto.normalizaNome(name)
                    putString("chave",salvar)
                }.apply()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }


}