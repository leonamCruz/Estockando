package tech.leonam.estockando.controller.util

class UtilPreco {
    companion object {
        fun normalizaPreco(preco:String) : String{
            return String.format("R$%.2f", preco.toDouble())
        }
    }
}