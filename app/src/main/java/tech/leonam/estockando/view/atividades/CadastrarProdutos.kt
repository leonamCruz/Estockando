package tech.leonam.estockando.view.atividades

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import tech.leonam.estockando.databinding.ActivityCadastrarProdutosBinding

class CadastrarProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarProdutosBinding
    private lateinit var scannerView: DecoratedBarcodeView
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrarProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        scannerView = binding.visualizaoCamera
        if (!checkCameraPermission()) {
            requestCameraPermission()
        }
        binding.check.isVisible = false
        cadastrar()
        clickPegadorDeCodigoDeBarras()
    }

    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }

    override fun onResume() {
        super.onResume()
        if (checkCameraPermission()) {
            scannerView.resume()
            scannerView.decodeContinuous(callback) // Configura o callback de leitura contínua
        }
    }

    override fun onPause() {
        super.onPause()
        scannerView.pause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                scannerView.resume()
                scannerView.decodeContinuous(callback) // Configura o callback de leitura contínua
            } else {
                requestCameraPermission()
            }
        }
    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            println(result?.text)
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    private fun clickPegadorDeCodigoDeBarras() {
        binding.imageCodeButton.setOnClickListener {
            // Chame o scanner quando o botão for clicado (ou implemente a lógica que desejar)
            scannerView.decodeSingle(callback)
        }
    }

    private fun cadastrar() {
        // Implemente a lógica de cadastro aqui
    }
}
