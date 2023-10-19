package com.ramon.formulario2actvitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.widget.ArrayAdapter
import com.ramon.formulario2actvitys.databinding.ActivityMainBinding
import com.ramon.formulario2actvitys.databinding.ActivityResultadosBinding

class ActivityResultados : AppCompatActivity() {
    lateinit var binding: ActivityResultadosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            MainActivity.personas.map{
                "${it.nombre} ${it.apellido} - DNI: ${it.dni} - Contraseña ${it.contrasena}"
            }
        )
        binding.list1.adapter = adapter

        binding.btnVolver.setOnClickListener{
            finish()
        }
    }
}