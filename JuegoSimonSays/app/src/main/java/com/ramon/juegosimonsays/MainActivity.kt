package com.ramon.juegosimonsays

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ramon.juegosimonsays.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val simonSequence = mutableListOf<Int>()
    private val playerSequence = mutableListOf<Int>()
    private var currentRound = 1 // Iniciar en la ronda 1
    private var currentIndex = 0
    private val handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.btnStart.setOnClickListener {
            startSimonSays()
            binding.btnStart.isEnabled = false
        }
        binding.imgazul.setOnClickListener {
            handlePlayerInput(0)
        }
        binding.imgrosa.setOnClickListener {
            handlePlayerInput(1)
        }
        binding.imgamarillo.setOnClickListener {
            handlePlayerInput(2)
        }
        binding.imgverde.setOnClickListener {
            handlePlayerInput(3)
        }
    }

    private fun startSimonSays() {
        simonSequence.clear()
        playerSequence.clear()
        currentIndex = 0

        // Generar una secuencia aleatoria para la ronda actual
        for (i in 0 until currentRound) {
            addNextColorToSimonSequence()
        }

        showSimonSequence()
    }

    private fun showSimonSequence() {
        binding.imgazul.isEnabled = false
        binding.imgrosa.isEnabled = false
        binding.imgamarillo.isEnabled = false
        binding.imgverde.isEnabled = false

        handler.postDelayed({
            if (currentIndex < simonSequence.size) {
                val colorView = getColorViewFromNumber(simonSequence[currentIndex])
                colorView.visibility = View.VISIBLE

                handler.postDelayed({
                    colorView.visibility = View.INVISIBLE
                    currentIndex++

                    if (currentIndex == simonSequence.size) {
                        // Habilitar la entrada del jugador después de mostrar la secuencia
                        binding.imgazul.isEnabled = true
                        binding.imgrosa.isEnabled = true
                        binding.imgamarillo.isEnabled = true
                        binding.imgverde.isEnabled = true
                    }
                }, 1000)
            } else {
                // El jugador debe repetir la secuencia
                currentIndex = 0
                playerSequence.clear()
            }
        }, 1000)
    }

    private fun handlePlayerInput(colorNumber: Int) {
        playerSequence.add(colorNumber)

        if (playerSequence[currentIndex] == simonSequence[currentIndex]) {
            currentIndex++
            if (currentIndex == simonSequence.size) {
                // El jugador completó la secuencia
                if (playerSequence.size == simonSequence.size) {
                    // El jugador completó con éxito la ronda actual
                    Toast.makeText(this, "¡Bien hecho! Ronda $currentRound superada", Toast.LENGTH_SHORT).show()
                    currentRound++
                    startSimonSays() // Iniciar la siguiente ronda
                }
            }
        } else {
            // El jugador cometió un error
            // Aquí puedes mostrar un mensaje de "game over" o reiniciar el juego.
            binding.btnStart.isEnabled = true
            currentRound = 1 // Reiniciar a la primera ronda en caso de error
        }
    }

    private fun addNextColorToSimonSequence() {
        val randomColor = Random().nextInt(4)
        simonSequence.add(randomColor)
    }

    private fun getColorViewFromNumber(colorNumber: Int): View {
        return when (colorNumber) {
            0 -> binding.imgazul
            1 -> binding.imgrosa
            2 -> binding.imgamarillo
            3 -> binding.imgverde
            else -> binding.imgazul
        }
    }
}