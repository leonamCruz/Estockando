package tech.leonam.estockando.view.atividades

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.ActivityCadastrarProdutosBinding

class CadastrarProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarProdutosBinding
    private lateinit var scannerView: DecoratedBarcodeView
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
    private val REQUEST_GALLERY_IMAGE = 101
    private val REQUEST_CAMERA_IMAGE = 102
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
        pegaImagem()
    }

    private fun pegaImagem() {
        binding.camera.setOnClickListener {
            val build: AlertDialog.Builder = AlertDialog.Builder(this)
            build.setTitle(getString(R.string.quer_da_c_mera_ou_galeria))
            build.setMessage(getString(R.string.escolha_entre_sua_c_mera_e_galeria_para_selecionar_a_foto_do_produto))
            build.setPositiveButtonIcon(AppCompatResources.getDrawable(this, R.drawable.galeria))
            build.setNegativeButtonIcon(AppCompatResources.getDrawable(this, R.drawable.cam))
            build.setPositiveButton(getString(R.string.galeria)) { _, _ ->

            }
            build.setNegativeButton(getString(R.string.c_mera)) { _, _ ->

            }
        }
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
            scannerView.decodeContinuous(callback)
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
            binding.codigoDeBarras.setText(result?.text)
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    private fun cadastrar() {
        // Implemente a lógica de cadastro aqui
    }
}
