package tech.leonam.estockando.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import tech.leonam.estockando.model.contratos.ContratoPesquisa
import tech.leonam.estockando.viewModel.Produtos

class PesquisaDAO(context: Context) : ContratoPesquisa {
    private val SQL_PEGA_TUDO = "SELECT * FROM ${Helper.NOME_TABELA}"
    private val db: SQLiteDatabase

    init {
        db = Helper(context).writableDatabase
    }

    override fun pegaTudo(): ArrayList<Produtos> {
        val cursor = db.rawQuery(SQL_PEGA_TUDO, null)
        val lista = ArrayList<Produtos>()

        cursor.use {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    val produto = Produtos()
                    with(produto) {
                        with(Helper) {
                            id = cursor.getLong(COLUMN_ID_POSITION)
                            nomeDoProduto = cursor.getString(COLUMN_NOME_POSITION)
                            descricaoDoProduto = cursor.getString(COLUMN_DESCRICAO_POSITION)
                            dataCadastro = cursor.getString(COLUMN_DATA_CADASTRO_POSITION)
                            preco = cursor.getString(COLUMN_PRECO_POSITION)
                            codigoDeBarras = cursor.getString(COLUMN_CODIGO_BARRAS_POSITION)
                            imagemDoProduto = cursor.getString(COLUMN_IMAGEM_POSITION)
                            qntDoProduto = cursor.getString(COLUMN_QUANTIDADE_POSITION)

                        }
                    }
                    lista.add(produto)
                    cursor.moveToNext()
                }
            }
        }
        Thread {
            db.close()
        }.start()
        return lista
    }

    override fun pegaQntdDeProdutos(): Long {
        val sql = "SELECT COUNT(${Helper.COLUMN_ID}) FROM ${Helper.NOME_TABELA}"
        val rawQuery = db.rawQuery(sql, null)
        val retorno = if (rawQuery.moveToFirst()) rawQuery.getLong(0) else 0
        Thread {
            rawQuery.close()
            db.close()
        }.start()
        return retorno
    }

    override fun pegaPorId(id: Long): ArrayList<Produtos> {
        val sql = "SELECT * FROM ${Helper.NOME_TABELA} WHERE ID = $id"
        val rawQuery = db.rawQuery(sql, null)
        val produtos = Produtos()
        with(rawQuery) {
            with(produtos) {
                with(Helper) {
                    if (moveToFirst()) {
                        produtos.id = getLong(COLUMN_ID_POSITION)
                        nomeDoProduto = getString(COLUMN_NOME_POSITION)
                        descricaoDoProduto = getString(COLUMN_DESCRICAO_POSITION)
                        dataCadastro = getString(COLUMN_DATA_CADASTRO_POSITION)
                        preco = getString(COLUMN_PRECO_POSITION)
                        codigoDeBarras = getString(COLUMN_CODIGO_BARRAS_POSITION)
                        imagemDoProduto = getString(COLUMN_IMAGEM_POSITION)
                        qntDoProduto = getString(COLUMN_QUANTIDADE_POSITION)
                    }
                }
            }
            val array = ArrayList<Produtos>()
            array.add(produtos)
            return array
        }
    }
}