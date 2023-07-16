package tech.leonam.estockando.model

import android.content.ContentValues
import android.content.Context
import tech.leonam.estockando.controller.Produtos

class CadastrarDAO(
    context: Context,
    produto: Produtos
) {
    private var helper: Helper
    private val pd = produto

    init {
        helper = Helper(context)
    }

    fun saveInDatabase() {
        val db = helper.writableDatabase
        val contentValues = ContentValues()
        with(contentValues) {
            with(pd) {
                with(Helper) {
                    put(COLUMN_NOME, nomeDoProduto)
                    put(COLUMN_DESCRICAO, descricaoDoProduto)
                    put(COLUMN_DATA_CADASTRO,dataCadastro)
                    put(COLUMN_CODIGO_BARRAS,codigoDeBarras)
                    put(COLUMN_IMAGEM,imagemDoProduto)
                    put(COLUMN_PRECO,preco)
                    put(COLUMN_QUANTIDADE,qntDoProduto)

                    db.insert(NOME_TABELA, null,contentValues)
                    Thread{
                        db.close()
                        helper.close()
                    }.start()
                }
            }
        }
    }
}