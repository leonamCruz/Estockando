package tech.leonam.estockando.model.contratos

import tech.leonam.estockando.viewModel.Produtos

interface ContratoPesquisa {
    fun pegaTudo():ArrayList<Produtos>
    fun pegaQntdDeProdutos():Long
}