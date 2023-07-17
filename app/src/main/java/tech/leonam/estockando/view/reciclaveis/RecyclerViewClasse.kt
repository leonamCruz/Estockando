package tech.leonam.estockando.view.reciclaveis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tech.leonam.estockando.viewModel.Pesquisar
import tech.leonam.estockando.databinding.ActivityReciclavelMostrarTudoBinding
import tech.leonam.estockando.view.adaptadores.ViewGenericaAdaptadora

class RecyclerViewClasse : AppCompatActivity() {
    private lateinit var binding: ActivityReciclavelMostrarTudoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclavelMostrarTudoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptador()
    }

    private fun adaptador() {
        with(binding.reciclavel){
            adapter = ViewGenericaAdaptadora(Pesquisar(context).pegaTudo(), context)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
    }
}