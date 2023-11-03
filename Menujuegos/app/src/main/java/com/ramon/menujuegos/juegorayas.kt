package com.ramon.menujuegos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.ramon.menujuegos.databinding.ActivityJuegoDadosBinding
import com.ramon.menujuegos.databinding.ActivityRayasBinding
import kotlin.properties.Delegates

class juegorayas : AppCompatActivity() {

    lateinit var binding: ActivityRayasBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityRayasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var victoria = false
        var empate = false
        var signo: Boolean = true;
        var casillasMarcadas = 0

        val buttons = arrayOf(
            binding.img1,
            binding.img2,
            binding.img3,
            binding.img4,
            binding.img5,
            binding.img6,
            binding.img7,
            binding.img8,
            binding.img9,)

        buttons.forEach { imageView ->
            imageView.setOnClickListener {
                if (!victoria){
                    if (imageView.tag == "he"){
                        if (signo) {
                            imageView.setImageResource(R.drawable.heo)
                            imageView.tag = "o"
                        } else {
                            imageView.setImageResource(R.drawable.hex)
                            imageView.tag = "x"
                        }
                        signo = cambiarSigno(signo)
                        casillasMarcadas++

                        if (checkTresEnRaya(buttons, "x")){
                            victoria = true
                            binding.txtGanador.setText("VICTORIA X")
                            binding.txtGanador.visibility = View.VISIBLE
                            binding.btnReiniciar.visibility = View.VISIBLE
                        }
                        if (checkTresEnRaya(buttons, "o")){
                            victoria = true
                            binding.txtGanador.setText("VICTORIA O")
                            binding.txtGanador.visibility = View.VISIBLE
                            binding.btnReiniciar.visibility = View.VISIBLE
                        }
                        if (casillasMarcadas == 9){
                            empate = true
                            binding.txtGanador.text = "EMPATE"
                            binding.txtGanador.visibility = View.VISIBLE
                            binding.btnReiniciar.visibility = View.VISIBLE
                        }
                    }else{
                        Toast.makeText(this, "Esa imagen ya se ha seleccionado", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this, "La partida ya ha terminado, vuelva a empezar", Toast.LENGTH_SHORT).show()
                }
            }

            binding.btnReiniciar.setOnClickListener {
                buttons.forEach {
                    imageView-> run {
                        imageView.setImageResource(R.drawable.he)
                        imageView.tag = "he"
                    }
                }
                victoria = false
                empate = false
                casillasMarcadas = 0
                binding.btnReiniciar.visibility = View.GONE
                binding.txtGanador.visibility = View.GONE
            }

        }
    }

    private fun cambiarSigno(x: Boolean): Boolean{
        return !x
    }
    fun checkTresEnRaya(buttons: Array<ImageView>, tag: String): Boolean {
        // Combinaciones ganadoras
        val winningCombinations = arrayOf(
            arrayOf(0, 1, 2), // Primera fila
            arrayOf(3, 4, 5), // Segunda fila
            arrayOf(6, 7, 8), // Tercera fila
            arrayOf(0, 3, 6), // Primera columna
            arrayOf(1, 4, 7), // Segunda columna
            arrayOf(2, 5, 8), // Tercera columna
            arrayOf(0, 4, 8), // Diagonal principal
            arrayOf(2, 4, 6)  // Diagonal secundaria
        )

        // Verificar cada combinación
        for (combination in winningCombinations) {
            if (buttons[combination[0]].tag == tag &&
                buttons[combination[1]].tag == tag &&
                buttons[combination[2]].tag == tag) {
                return true
            }
        }

        // Si no hay 3 en raya, retornar false
        return false
    }


}