package tech.leonam.estockando.viewModel

import android.content.Context
import tech.leonam.estockando.model.contract.RegisterInterface
import tech.leonam.estockando.model.dao.RegisterDao

class RegisterControl(product: Product, context:Context):RegisterInterface {
    private var p = product
    private var c = context
    override fun saveInDatabase() {
        sanatizarDados()
        RegisterDao(c, p).saveInDatabase()
    }

    private fun sanatizarDados() {

    }
}