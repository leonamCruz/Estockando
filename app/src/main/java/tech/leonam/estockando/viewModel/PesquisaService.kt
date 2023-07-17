package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.ContratoPesquisa
import tech.leonam.estockando.model.PesquisaDAO

class PesquisaService(context: Context) : ContratoPesquisa {
    private val contexto = context
    override fun pegaTudo(): ArrayList<Produtos> {
        return PesquisaDAO(contexto).pegaTudo()
    }

    override fun pegaQntdDeProdutos(): Long {
        return PesquisaDAO(contexto).pegaQntdDeProdutos()
    }
}