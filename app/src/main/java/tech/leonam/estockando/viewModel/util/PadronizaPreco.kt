package tech.leonam.estockando.viewModel.util

import java.math.BigDecimal

internal object PadronizaPreco {
    private var ehMenor = 1
    fun padronizar(de: BigDecimal, ate: BigDecimal): ArrayList<BigDecimal> {
        val lista = ArrayList<BigDecimal>()
        if (ate.compareTo(de) == ehMenor) {
            lista.add(de)
            lista.add(ate)
        } else {
            lista.add(ate)
            lista.add(de)
        }
        return lista
    }
}
