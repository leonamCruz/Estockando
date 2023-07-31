package tech.leonam.estockando.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.FragmentSearchStockBinding
import tech.leonam.estockando.view.recyclable.RecyclerViewClasse

class SearchStockFragmet : Fragment() {
    private lateinit var binding: FragmentSearchStockBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search_stock, container, false)
        binding = FragmentSearchStockBinding.bind(view)
        clickTodosOsProdutos(binding)
        clickPesquisaPorId(binding)
        clickPesquisaPorPreco(binding)

        return view
    }

    private fun clickPesquisaPorPreco(binding: FragmentSearchStockBinding) {
        binding.cardPesquisaPorPreO.setOnClickListener {
            val de = binding.de.text.toString()
            val ate = binding.ate.text.toString()
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 2)

            if (de.isNotBlank() || ate.isNotBlank()) {
                intent.putExtra("de", de)
                intent.putExtra("ate", ate)
                startActivity(intent)
            } else {
                val snackbar = Snackbar.make(
                    binding.root, getString(R.string.verifique_os_valores),
                    Snackbar.LENGTH_SHORT
                )
                snackbar.setTextColor(requireContext().getColor(R.color.branco))
                snackbar.setBackgroundTint(requireContext().getColor(R.color.preto))
                snackbar.show()
            }
        }
    }

    private fun clickPesquisaPorId(binding: FragmentSearchStockBinding) {
        binding.cardPesquisaPorId.setOnClickListener {
            val id = binding.numeroDoIdPesquisa.text.toString()

            if (id.isNotBlank()) {
                val intent = Intent(context, RecyclerViewClasse::class.java)
                intent.putExtra("opcao", 1)
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
    }

    private fun clickTodosOsProdutos(binding: FragmentSearchStockBinding) {
        binding.cardTodosOsProdutos.setOnClickListener {
            val intent = Intent(context, RecyclerViewClasse::class.java)
            intent.putExtra("opcao", 0)
            startActivity(intent)
        }
    }
}