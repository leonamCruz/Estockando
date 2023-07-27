package tech.leonam.estockando.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.FragmentPesquisaEstoqueBinding
import tech.leonam.estockando.view.recyclable.RecyclerViewClasse

class SearchStockFragmet : Fragment() {
    private lateinit var binding: FragmentPesquisaEstoqueBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_stock, container, false)
        binding = FragmentPesquisaEstoqueBinding.bind(view)
        binding.cardTodosOsProdutos.setOnClickListener {
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 0)
            startActivity(intent)
        }
        binding.cardPesquisaPorId.setOnClickListener {
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 1)
            val id = binding.numeroDoIdPesquisa.text.toString()
            if (id.isNotBlank()) {
                intent.putExtra("id", id)
                startActivity(intent)
            } else {
                val snackbar = Snackbar.make(
                    binding.root,
                    getString(R.string.insira_um_valor),
                    Snackbar.LENGTH_SHORT
                )
                snackbar.setTextColor(requireContext().getColor(R.color.branco))
                snackbar.setBackgroundTint(requireContext().getColor(R.color.preto))
                snackbar.show()
            }
        }
        return view
    }
}