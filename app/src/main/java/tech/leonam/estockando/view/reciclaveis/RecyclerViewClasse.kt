package tech.leonam.estockando.view.reciclaveis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tech.leonam.estockando.databinding.ActivityReciclavelMostrarTudoBinding
import tech.leonam.estockando.view.adaptadores.ViewGenericaAdaptadora
import tech.leonam.estockando.viewModel.Pesquisar
import tech.leonam.estockando.viewModel.Produtos

class RecyclerViewClasse : AppCompatActivity() {
    private lateinit var binding: ActivityReciclavelMostrarTudoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclavelMostrarTudoBinding.inflate(layoutInflater)

        when(intent.extras!!.getInt("opcao")){
            0-> adaptador(Pesquisar(baseContext).pegaTudo())
            1-> adaptador(Pesquisar(baseContext).pegaPorId(intent.getStringExtra("id")!!.toLong()))
        }


        setContentView(binding.root)
    }

    private fun adaptador(pesquisa: ArrayList<Produtos>) {
        with(binding.reciclavel){
            adapter = ViewGenericaAdaptadora(pesquisa, context)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
    }
}