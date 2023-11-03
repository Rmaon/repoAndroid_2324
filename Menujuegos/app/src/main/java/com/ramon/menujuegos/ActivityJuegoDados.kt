package com.ramon.menujuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.bumptech.glide.Glide
import com.ramon.menujuegos.databinding.ActivityJuegoDadosBinding

class ActivityJuegoDados : AppCompatActivity() {

    private var clickCount = 0
    private var totalSum = 0
    private var clickCountD = 0
    private var totalSumD = 0
    lateinit var binding: ActivityJuegoDadosBinding
    private var ronda = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJuegoDadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imgGif.setOnClickListener {
            if (clickCount < 5) { // Si los clics son menos de 5
                rollDice(binding)
                clickCount++ // Incrementa el contador
                binding.txtN.text = clickCount.toString()
                checkAndCompareScores()
            } else {
                Toast.makeText(this, "No se permiten más clics", Toast.LENGTH_SHORT).show()
            }

        }

        binding.imgGifD.setOnClickListener {
            if (clickCountD < 5) {
                rollDiceD(binding)
                clickCountD++
                binding.txtND.text = clickCountD.toString()
                checkAndCompareScores()
            } else {
                Toast.makeText(this, "No se permiten más clics", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnRound.setOnClickListener {
            ronda += 1
            resetGame(binding)
        }

        binding.btnReinicio.setOnClickListener {
            resetApp(binding)
        }
    }

    fun rollDice(binding: ActivityJuegoDadosBinding) {
        // Muestra el GIF
        Glide.with(this).load(R.drawable.d0g).into(this.binding.imgGif)

        // Espera un segundo (1000 milisegundos) antes de ejecutar el siguiente código
        Handler(Looper.getMainLooper()).postDelayed({
            // Genera un número aleatorio del 1 al 6
            val randomNumber = (1..6).random()

            // Suma el número generado al acumulador
            totalSum += randomNumber
            binding.txtN2.text = totalSum.toString()

            // Obtiene el ID del recurso de la imagen basado en el número generado
            val imageResId = resources.getIdentifier("d$randomNumber", "drawable", packageName)

            // Muestra la imagen correspondiente
            this.binding.imgGif.setImageResource(imageResId)
        }, 1000) // Tiempo de espera en milisegundos


    }

    fun rollDiceD(binding: ActivityJuegoDadosBinding) {
        Glide.with(this).load(R.drawable.d0g).into(binding.imgGifD)

        Handler(Looper.getMainLooper()).postDelayed({
            val randomNumber = (1..6).random()

            totalSumD += randomNumber
            binding.txtN2D.text = totalSumD.toString()

            val imageResId = resources.getIdentifier("d$randomNumber", "drawable", packageName)

            binding.imgGifD.setImageResource(imageResId)
        }, 1000)
    }

    private fun resetApp(binding: ActivityJuegoDadosBinding) {
        ronda = 0
        binding.txtN1D.text = (0).toString()
        binding.txtN1.text = (0).toString()
        clickCount = 0
        totalSum = 0
        clickCountD = 0
        totalSumD = 0

        binding.txtN.text = "0"
        binding.txtN2.text = "0"
        binding.imgGif.setImageResource(R.drawable.d0)

        binding.txtND.text = "0"
        binding.txtN2D.text = "0"
        binding.imgGifD.setImageResource(R.drawable.d0)

    }

    private fun resetGame(binding: ActivityJuegoDadosBinding) {
        clickCount = 0
        totalSum = 0
        clickCountD = 0
        totalSumD = 0

        binding.txtN.text = "0"
        binding.txtN2.text = "0"
        binding.imgGif.setImageResource(R.drawable.d0)

        binding.txtND.text = "0"
        binding.txtN2D.text = "0"
        binding.imgGifD.setImageResource(R.drawable.d0)
    }

    private fun checkAndCompareScores() {

        if (clickCount == 5 && clickCountD == 5) {

            Handler(Looper.getMainLooper()).postDelayed({
                // Dentro de este bloque se ejecutará después de 1 segundo
                // Incrementa la variable i
                var i = 0

                // Si ambos contadores de clic alcanzan 5, comparamos las sumas
                if (totalSum > totalSumD) {
                    // Si totalSum es mayor, aumentamos el puntaje en txtN1
                    val score = binding.txtN1.text.toString().toIntOrNull() ?: 0
                    binding.txtN1.text = (score + 1).toString()
                } else if (totalSum < totalSumD) {
                    // Si totalSumD es mayor, aumentamos el puntaje en txtN1D
                    val scoreD = binding.txtN1D.text.toString().toIntOrNull() ?: 0
                    binding.txtN1D.text = (scoreD + 1).toString()
                } else {
                    // Si totalSum es igual a totalSumD, mostramos un mensaje de empate
                    Toast.makeText(this, "Empate!!!", Toast.LENGTH_SHORT).show()
                }
            }, 1200) // 1000 milisegundos = 1 segundo

        }
    }

}