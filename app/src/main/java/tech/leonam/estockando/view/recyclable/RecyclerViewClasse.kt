package tech.leonam.estockando.view.recyclable

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import tech.leonam.estockando.databinding.ActivityRecyclerViewBinding
import tech.leonam.estockando.view.adapt.RecyclerViewAdapter
import tech.leonam.estockando.viewModel.Product
import tech.leonam.estockando.viewModel.SearchControl
import java.math.BigDecimal

class RecyclerViewClasse : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    private var opcao: Int? = null
    private var id: Long? = null
    private lateinit var de: BigDecimal
    private lateinit var ate: BigDecimal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        opcao = intent.extras!!.getInt("opcao")

        seletorDePesquisa(opcao!!)
        setContentView(binding.root)
    }

    private fun seletorDePesquisa(opcao: Int) {
        when (opcao) {
            0 -> {
                pegaTudo()
            }
            1 -> {
                id = intent.getStringExtra("id")!!.toLong()
                pegaPorId(id!!)
            }
            2 -> {
                de = intent.getStringExtra("de")!!.toBigDecimal()
                ate = intent.getStringExtra("ate")!!.toBigDecimal()
                pegaPorPreco(de,ate)
            }
        }
    }

    private fun adaptador(pesquisa: ArrayList<Product>) {
        with(binding.reciclavel) {
            adapter = RecyclerViewAdapter(pesquisa, context)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private fun pegaTudo() {
        adaptador(SearchControl(baseContext).pegaTudo())
    }
    private fun pegaPorId(id: Long){
        adaptador(SearchControl(baseContext).pegaPorId(id))
    }

    private fun pegaPorPreco(de: BigDecimal, ate: BigDecimal) {
        adaptador(SearchControl(baseContext).pegaPorPreco(de,ate))
    }
}
