package tech.leonam.estockando.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Helper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "produtos.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sql = "CREATE TABLE IF NOT EXISTS produtos (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "nome TEXT,\n" +
                "descricao TEXT,\n" +
                "dataCadastro TEXT,\n" +
                "preco TEXT,\n" +
                "codigoDeBarras TEXT,\n" +
                "imagemDoProduto TEXT,\n" +
                "qntDoProduto TEXT\n" +
                ")"
        p0?.execSQL(sql)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}