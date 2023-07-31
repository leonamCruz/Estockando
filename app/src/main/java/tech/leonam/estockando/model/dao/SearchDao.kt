package tech.leonam.estockando.model.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import tech.leonam.estockando.model.contract.SearchInterface
import tech.leonam.estockando.model.dao.Helper.Companion.COLUMN_PRECO
import tech.leonam.estockando.viewModel.Product
import java.math.BigDecimal

class SearchDao(context: Context) : SearchInterface {
    private val SQL_PEGA_TUDO = "SELECT * FROM ${Helper.NOME_TABELA}"
    private val db: SQLiteDatabase

    init {
        db = Helper(context).writableDatabase
    }

    override fun pegaTudo(): ArrayList<Product> {
        val cursor = db.rawQuery(SQL_PEGA_TUDO, null)
        val lista = ArrayList<Product>()

        cursor.use {
            if (cursor.moveToFirst()) {
                while (!cursor.isAfterLast) {
                    val produto = Product()
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

    override fun pegaPorId(id: Long): ArrayList<Product> {
        val sql = "SELECT * FROM ${Helper.NOME_TABELA} WHERE ID = $id"
        val rawQuery = db.rawQuery(sql, null)
        val product = Product()
        with(rawQuery) {
            with(product) {
                with(Helper) {
                    if (moveToFirst()) {
                        product.id = getLong(COLUMN_ID_POSITION)
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
            val array = ArrayList<Product>()
            array.add(product)
            return array
        }
    }

    override fun pegaPorPreco(de: BigDecimal, ate: BigDecimal): ArrayList<Product> {
        val lista = ArrayList<Product>()
        val query = "SELECT * FROM ${Helper.NOME_TABELA} WHERE CAST($COLUMN_PRECO AS DECIMAL(10, 2)) BETWEEN ${de.toDouble()} AND ${ate.toDouble()} ORDER BY CAST($COLUMN_PRECO AS DECIMAL(10, 2)) ASC"


        val rawQuery = db.rawQuery(query, null)

        rawQuery.use {
            if (rawQuery.moveToFirst()) {
                while (!rawQuery.isAfterLast) {
                    val produto = Product()
                    with(produto) {
                        with(Helper) {
                            id = rawQuery.getLong(COLUMN_ID_POSITION)
                            nomeDoProduto = rawQuery.getString(COLUMN_NOME_POSITION)
                            descricaoDoProduto = rawQuery.getString(COLUMN_DESCRICAO_POSITION)
                            dataCadastro = rawQuery.getString(COLUMN_DATA_CADASTRO_POSITION)
                            preco = rawQuery.getString(COLUMN_PRECO_POSITION)
                            codigoDeBarras = rawQuery.getString(COLUMN_CODIGO_BARRAS_POSITION)
                            imagemDoProduto = rawQuery.getString(COLUMN_IMAGEM_POSITION)
                            qntDoProduto = rawQuery.getString(COLUMN_QUANTIDADE_POSITION)
                        }
                    }
                    lista.add(produto)
                    rawQuery.moveToNext()
                }
            }
        }
        Thread {
            db.close()
        }.start()
        return lista
    }
}