package com.ramon.formulario2actvitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ramon.formulario2actvitys.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        var personas: MutableList<Persona> = mutableListOf()
    }

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener{
            if (comprobarAnindado()){
                if (comprobarDni(personas,binding.ettDni.text.toString().trim())){
                    val p = Persona(
                        binding.ettName.text.toString().trim(),
                        binding.ettSurname.text.toString().trim(),
                        binding.ettDni.text.toString().trim(),
                        binding.ettGmail.text.toString().trim(),
                        binding.ettPassword.text.toString().trim())

                    personas.add(p)
                }else{
                    Toast.makeText(this, "El DNI ya está introducido", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnActivity2.setOnClickListener{
            val intent = Intent(this, ActivityResultados::class.java)
            startActivity(intent)
        }
    }


    private fun comprobarCampos(): Boolean{
        val ident = listOf(
            binding.ettName, binding.ettSurname, binding.ettDni, binding.ettGmail, binding.ettPassword, binding.ettPassword2
        )
        for (i in ident){
            if (i.text.toString().isEmpty()){
                return false
            }
        }
        return true
    }
    private fun comprobarPsswrdLength(): Boolean{
        return(binding.ettPassword.text.toString().length<6)
    }

    private fun comprobarPsswrd(): Boolean{
        return binding.ettPassword2.text.toString().equals(binding.ettPassword.text.toString())
    }

    private fun comprobarDni(personas: MutableList<Persona>, dniP: String):Boolean {
        return !personas.any { it.dni == dniP }
    }

    private fun comprobarAnindado(): Boolean{
        if (comprobarCampos()){
            if (comprobarPsswrd()){
                if (!comprobarPsswrdLength()){

                    return true
                }else{
                    Toast.makeText(this, "La contraseña debe tener al menos 6 digitos", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Las contraseñas deben coincidir", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}