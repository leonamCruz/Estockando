package tech.leonam.estockando.viewModel.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal


class PadronizaPrecoTest {
    @Test
    fun testPadronizar() {
        // Arrange
        val de = BigDecimal("10.00")
        val ate = BigDecimal("5.00")

        // Act
        val resultado1 = PadronizaPreco.padronizar(de, ate)
        val resultado2 = PadronizaPreco.padronizar(ate, de)

        // Assert
        assertEquals(ate, resultado1[0]) // O menor valor deve estar na posição 0
        assertEquals(de, resultado1[1]) // O maior valor deve estar na posição 1

        assertEquals(ate, resultado2[0]) // O menor valor deve estar na posição 0
        assertEquals(de, resultado2[1]) // O maior valor deve estar na posição 1
    }
}