package tech.leonam.estockando.view.atividades

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.google.zxing.ResultPoint
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import tech.leonam.estockando.R
import tech.leonam.estockando.databinding.ActivityCadastrarProdutosBinding
import tech.leonam.estockando.viewModel.util.UtilImage

@Suppress("DEPRECATION", "OVERRIDE_DEPRECATION")
class CadastrarProdutos : AppCompatActivity() {
    private lateinit var binding: ActivityCadastrarProdutosBinding
    private lateinit var scannerView: DecoratedBarcodeView
    private val CAMERA_PERMISSION_REQUEST_CODE = 1001
    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE_CAPTURE = 2
    private val REQUEST_READ_EXTERNAL_STORAGE = 1003
    private val REQUEST_PICK_IMAGE = 1004
    private var foto: String? = null

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
                if (checkReadExternalStoragePermission()) {
                    openGallery()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_READ_EXTERNAL_STORAGE)
                }
            }
            build.setNegativeButton(getString(R.string.c_mera)) { _, _ ->
                if (checkCameraPermission()) {
                    captureImage()
                } else {
                    requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_CAMERA_PERMISSION)
                }
            }
            build.create().show()
        }
    }

    private fun checkReadExternalStoragePermission(): Boolean {
        return (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_PICK_IMAGE)
    }

    private fun captureImage() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)
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
        if (requestCode == REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery()
            } else {
                Toast.makeText(this, getString(R.string.permissao_negada_armazenamento), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val callback = object : BarcodeCallback {
        override fun barcodeResult(result: BarcodeResult?) {
            binding.codigoDeBarras.setText(result?.text)
        }

        override fun possibleResultPoints(resultPoints: MutableList<ResultPoint>?) {}
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                Thread {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    foto = UtilImage.deBitmapParaBase64(imageBitmap)
                }.start()
            }catch (ex: Exception){
                val snackbar = Snackbar.make(binding.root, getString(R.string.falha_no_processamento_da_imagem),Snackbar.LENGTH_SHORT)
                snackbar.setTextColor(getColor(R.color.branco))
                snackbar.setBackgroundTint(getColor(R.color.preto))
                snackbar.show()
            }

        }
        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            try {
                Thread {
                    val selectedImageUri = data.data
                    foto = selectedImageUri?.let { getBitmapFromUri(it) }?.let { UtilImage.deBitmapParaBase64(it) }
                }.start()
            }catch (ex: Exception){
                val snackbar = Snackbar.make(binding.root, getString(R.string.falha_no_processamento_da_imagem),Snackbar.LENGTH_SHORT)
                snackbar.setTextColor(getColor(R.color.branco))
                snackbar.setBackgroundTint(getColor(R.color.preto))
                snackbar.show()
            }
        }
    }

    private fun getBitmapFromUri(uri: Uri): Bitmap {
        val inputStream = contentResolver.openInputStream(uri)
        return BitmapFactory.decodeStream(inputStream)
    }

    private fun cadastrar() {
        binding.clicarParaCadastrar.setOnClickListener {
            println(foto)
        }
    }
}
