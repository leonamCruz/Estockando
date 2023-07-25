package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.contratos.ContratoPesquisa
import tech.leonam.estockando.model.dao.PesquisaDAO

class Pesquisar(context: Context) : ContratoPesquisa {
    private val contexto = context
    override fun pegaTudo(): ArrayList<Produtos> {
        return PesquisaDAO(contexto).pegaTudo()
    }

    override fun pegaQntdDeProdutos(): Long {
        return PesquisaDAO(contexto).pegaQntdDeProdutos()
    }

    override fun pegaPorId(id: Long): ArrayList<Produtos> {
        return PesquisaDAO(contexto).pegaPorId(id)
    }
}