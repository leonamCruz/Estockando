package tech.leonam.estockando.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "inventario.db"
        private const val DATABASE_VERSION = 1
        const val NOME_TABELA = "produtos"

        const val TABLE_PRODUTOS = "produtos"
        const val COLUMN_ID = "id"
        const val COLUMN_NOME = "nome"
        const val COLUMN_DESCRICAO = "descricao"
        const val COLUMN_DATA_CADASTRO = "data_cadastro"
        const val COLUMN_PRECO = "preco"
        const val COLUMN_CODIGO_BARRAS = "codigo_barras"
        const val COLUMN_IMAGEM = "imagem"
        const val COLUMN_QUANTIDADE = "quantidade"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS produtos ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOME TEXT, $COLUMN_DESCRICAO TEXT, $COLUMN_DATA_CADASTRO TEXT, $COLUMN_PRECO TEXT, $COLUMN_CODIGO_BARRAS TEXT, $COLUMN_IMAGEM TEXT, $COLUMN_QUANTIDADE TEXT)"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}
}