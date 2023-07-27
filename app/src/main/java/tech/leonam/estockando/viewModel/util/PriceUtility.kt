package tech.leonam.estockando.viewModel.util

class PriceUtility {
    companion object {
        fun normalizaPreco(preco:String) : String{
            return String.format("R$%.2f", preco.toDouble())
        }
    }
}