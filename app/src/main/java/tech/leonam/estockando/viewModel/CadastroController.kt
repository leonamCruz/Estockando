package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.contratos.ContratoCadastro
import tech.leonam.estockando.model.dao.CadastrarDAO

class CadastroController(produtos: Produtos, context:Context):ContratoCadastro {
    private var p = produtos
    private var c = context
    override fun saveInDatabase() {
        sanatizarDados()
        CadastrarDAO(c, p).saveInDatabase()
    }

    private fun sanatizarDados() {

    }
}