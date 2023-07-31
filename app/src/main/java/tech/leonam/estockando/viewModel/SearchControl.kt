package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.contract.SearchInterface
import tech.leonam.estockando.model.dao.SearchDao
import tech.leonam.estockando.viewModel.util.PadronizaPreco
import java.math.BigDecimal
const val DE = 0
const val ATE = 1
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
        val padronizado = PadronizaPreco.padronizar(de,ate)
        return SearchDao(contexto).pegaPorPreco(padronizado[DE],padronizado[ATE])
    }
}