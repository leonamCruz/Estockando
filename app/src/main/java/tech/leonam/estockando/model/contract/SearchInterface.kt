package tech.leonam.estockando.model.contract

import tech.leonam.estockando.viewModel.Product
import java.math.BigDecimal

interface SearchInterface {
    fun pegaTudo():ArrayList<Product>
    fun pegaQntdDeProdutos():Long
    fun pegaPorId(id: Long):ArrayList<Product>
    fun pegaPorPreco(de: BigDecimal, ate: BigDecimal):ArrayList<Product>

}