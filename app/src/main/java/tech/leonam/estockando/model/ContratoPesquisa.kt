package tech.leonam.estockando.model

import tech.leonam.estockando.controller.Produtos

interface ContratoPesquisa {
    fun pegaTudo():ArrayList<Produtos>
    fun pegaQntdDeProdutos():Long
}