package com.ramon.variosactivitiescondatos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramon.variosactivitiescondatos.databinding.ActivityVentana2Binding
import modelo.AlmacenPersonas
import modelo.Persona

class Ventana2 : AppCompatActivity() {
    lateinit var binding: ActivityVentana2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVentana2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        var nombre = intent.getStringExtra("nombre")
        var edad = intent.getStringExtra("edad")

        var persona: Persona = Persona(nombre,edad)
        binding.cajaNombre.setText(nombre)
        binding.cajaEdad.setText(edad)

        binding.boton.setOnClickListener{
            irAventana1()
        }
        AlmacenPersonas.aniadirPersona(persona)
        var cadena:String = "";
        var i = 0;
        for (p in AlmacenPersonas.personas){
            cadena=" "+i+". "+p.nombre+" "+p.edad+"\n"
            i++
            binding.multiLine.setText(cadena)
        }

        binding.boton.setOnClickListener(
            finish()
        )

    }


    private fun irAventana1(){
        var miIntent : Intent = Intent(this,MainActivity::class.java)
        startActivity(miIntent)
    }
}


}
