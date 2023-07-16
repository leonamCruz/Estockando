package tech.leonam.estockando.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import tech.leonam.estockando.controller.Produtos

class PesquisaDAO(context: Context) {
    private val SQL_PEGA_TUDO = "SELECT * FROM ${Helper.NOME_TABELA}"
    private val db: SQLiteDatabase

    init {
        db = Helper(context).writableDatabase
    }

    fun pegaTudo(): Produtos {
        val cursor = db.rawQuery(SQL_PEGA_TUDO, null)
        val produto = Produtos()
        with(cursor) {
            if (moveToFirst()) {
                with(Helper) {
                    with(produto) {
                        id = getLong(COLUMN_ID_POSITION)
                        nomeDoProduto = getString(COLUMN_NOME_POSITION)
                        descricaoDoProduto = getString(COLUMN_DESCRICAO_POSITION)
                        dataCadastro = getString(COLUMN_DATA_CADASTRO_POSITION)
                        preco = getString(COLUMN_PRECO_POSITION)
                        codigoDeBarras = getString(COLUMN_CODIGO_BARRAS_POSITION)
                        imagemDoProduto = getString(COLUMN_IMAGEM_POSITION)
                        qntDoProduto = getString(COLUMN_QUANTIDADE_POSITION)
                        Thread{
                            cursor.close()
                            db.close()
                        }.start()
                    }
                }
            }
        }
        return produto
    }
}