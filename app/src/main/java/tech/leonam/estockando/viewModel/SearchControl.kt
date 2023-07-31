package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.contract.SearchInterface
import tech.leonam.estockando.model.dao.SearchDao
import java.math.BigDecimal

class SearchControl(context: Context) : SearchInterface {
    private val contexto = context
    override fun pegaTudo(): ArrayList<Product> {
        return SearchDao(contexto).pegaTudo()
    }

    override fun pegaQntdDeProdutos(): Long {
        return SearchDao(contexto).pegaQntdDeProdutos()
    }

    override fun pegaPorId(id: Long): ArrayList<Product> {
        return SearchDao(contexto).pegaPorId(id)
    }

    override fun pegaPorPreco(de: BigDecimal, ate: BigDecimal): ArrayList<Product> {
        TODO("Not yet implemented")
    }
}