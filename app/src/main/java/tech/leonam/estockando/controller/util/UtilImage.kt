package tech.leonam.estockando.controller.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import org.apache.commons.codec.binary.Base64
import java.io.ByteArrayOutputStream

class UtilImage {
    companion object {
        fun deBase64ParaBitmap(base64: String): Bitmap {
            val decodedByteArray = Base64.decodeBase64(base64)
            return BitmapFactory.decodeByteArray(decodedByteArray,0,decodedByteArray.size)
        }

        fun deBitmapParaBase64(bitmap: Bitmap): String {
            val listaByteOutput = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,100,listaByteOutput)
            val array = listaByteOutput.toByteArray()
            return Base64.encodeBase64String(array)
        }
    }
}