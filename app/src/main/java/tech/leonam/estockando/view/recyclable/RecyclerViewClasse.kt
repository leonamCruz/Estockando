package tech.leonam.estockando.view.recyclable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tech.leonam.estockando.databinding.ActivityReciclavelMostrarTudoBinding
import tech.leonam.estockando.view.adapt.RecyclerViewAdapter
import tech.leonam.estockando.viewModel.SearchControl
import tech.leonam.estockando.viewModel.Product

class RecyclerViewClasse : AppCompatActivity() {
    private lateinit var binding: ActivityReciclavelMostrarTudoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReciclavelMostrarTudoBinding.inflate(layoutInflater)

        when(intent.extras!!.getInt("opcao")){
            0-> adaptador(SearchControl(baseContext).pegaTudo())
            1-> adaptador(SearchControl(baseContext).pegaPorId(intent.getStringExtra("id")!!.toLong()))
        }


        setContentView(binding.root)
    }

    private fun adaptador(pesquisa: ArrayList<Product>) {
        with(binding.reciclavel){
            adapter = RecyclerViewAdapter(pesquisa, context)
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            setHasFixedSize(true)
        }
    }
}