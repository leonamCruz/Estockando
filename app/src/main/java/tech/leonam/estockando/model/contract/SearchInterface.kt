package tech.leonam.estockando.model.contract

import tech.leonam.estockando.viewModel.Product

interface SearchInterface {
    fun pegaTudo():ArrayList<Product>
    fun pegaQntdDeProdutos():Long
    fun pegaPorId(id: Long):ArrayList<Product>

}