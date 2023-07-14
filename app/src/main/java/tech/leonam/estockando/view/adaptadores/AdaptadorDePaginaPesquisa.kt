package tech.leonam.estockando.view.adaptadores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tech.leonam.estockando.view.fragmentos.PesquisaEstoque
import tech.leonam.estockando.view.fragmentos.PesquisaVendas

class AdaptadorDePaginaPesquisa(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private lateinit var vendas: PesquisaVendas
    private lateinit var estoque: PesquisaEstoque
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        vendas = PesquisaVendas()
        estoque = PesquisaEstoque()
        return when (position) {
            0 -> vendas
            else -> estoque
        }
    }
}