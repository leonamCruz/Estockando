package tech.leonam.estockando.view.adapt

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import tech.leonam.estockando.view.fragment.SearchStockFragmet
import tech.leonam.estockando.view.fragment.SearchSaleFragment

class SearchFragmentAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private lateinit var vendas: SearchSaleFragment
    private lateinit var estoque: SearchStockFragmet
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        vendas = SearchSaleFragment()
        estoque = SearchStockFragmet()
        return when (position) {
            0 -> vendas
            else -> estoque
        }
    }
}