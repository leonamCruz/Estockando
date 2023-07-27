package tech.leonam.estockando.viewModel.util

class TextoUtility {
    companion object {
        fun normalizaNome(name: String): String {
            val nome = name.trim().lowercase()
            var retorno = ""
            retorno += nome[0].uppercase()
            for (i in 1 until nome.length) {
                if (nome[i - 1] == ' ') {
                    retorno += nome[i].uppercase()
                } else {
                    retorno += nome[i]
                }
            }
            return retorno
        }


    }
}