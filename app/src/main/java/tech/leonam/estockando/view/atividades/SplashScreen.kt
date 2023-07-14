package tech.leonam.estockando.view.atividades

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tech.leonam.estockando.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoSplash.alpha = 0f
        binding.logoSplash.animate().setDuration(1200).alpha(1f).withEndAction{
            val intencao = Intent(this,BoasVindas::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                this,
                android.R.animator.fade_in,
                android.R.anim.fade_out
            )
            startActivity(intencao, options.toBundle())
            finish()
        }
    }
}