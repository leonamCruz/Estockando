package tech.leonam.estockando.view.fragmentos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.FragmentPesquisaEstoqueBinding
import tech.leonam.estockando.view.reciclaveis.RecyclerViewClasse

class PesquisaEstoque : Fragment() {
    private lateinit var binding: FragmentPesquisaEstoqueBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pesquisa_estoque, container, false)
        binding = FragmentPesquisaEstoqueBinding.bind(view)
        binding.cardTodosOsProdutos.setOnClickListener {
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 0)
            startActivity(intent)
        }
        binding.cardPesquisaPorId.setOnClickListener {
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 1)
            intent.putExtra("id",binding.numeroDoIdPesquisa.text.toString())
            startActivity(intent)
        }
        return view
    }
}