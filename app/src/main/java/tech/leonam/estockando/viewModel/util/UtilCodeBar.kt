package tech.leonam.estockando.viewModel.util

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.oned.EAN13Writer

class UtilCodeBar {
    companion object {
        fun deTextoParaCodigoDeBarras(numeracao: String): Bitmap {
            if (numeracao.length != 13) {
                throw Exception("Númeração menor do que 13")
            }
            val codigoMatrix = EAN13Writer().encode(numeracao, BarcodeFormat.EAN_13, 1024, 1024)
            val bitmap =
                Bitmap.createBitmap(codigoMatrix.width, codigoMatrix.height, Bitmap.Config.RGB_565)
            for (x in 0 until codigoMatrix.width) {
                for (y in 0 until codigoMatrix.height) {
                    bitmap.setPixel(x, y, if (codigoMatrix[x, y]) Color.BLACK else Color.WHITE)
                }
            }
            return bitmap
        }
    }
}