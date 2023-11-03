package com.ramon.recyclerpersonajes

import Adaptadores.DBHelper
import Modelo.Personaje
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ramon.recyclerpersonajes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var miRecyclerView: RecyclerView
    lateinit var binding: ActivityMainBinding

    private lateinit var dbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var contextoPrincipal: Context
        lateinit var personajeList: List<Personaje>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Acceder al RecyclerView y configurarlo
        val miRecyclerView = binding.listaPersonajesRecycler

        // Configurar el listener del botón
        binding.btnInsertar.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.insert_dialog, null)

            val builder = AlertDialog.Builder(this)
            builder.setView(dialogView)
            builder.setTitle("Insertar Personaje")

            val nombreEditText = dialogView.findViewById<EditText>(R.id.editTextNombre)
            val tipoSpinner = dialogView.findViewById<Spinner>(R.id.spinnerTipo)
            val imagenRadioGroup = dialogView.findViewById<RadioGroup>(R.id.radioImagen)

            val tipos = arrayOf("wild", "internauta", "autentico")
            val tipoAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
            tipoSpinner.adapter = tipoAdapter

            builder.setPositiveButton("Insertar") { _, _ ->
                val nombre = nombreEditText.text.toString()
                val tipo = tipoSpinner.selectedItem.toString()
                val imagenId = imagenRadioGroup.checkedRadioButtonId
                val imagen = when (imagenId) {
                    R.id.radioChiquiibai -> "chiquiibai"
                    R.id.radioJordiWild -> "jordiwild"
                    R.id.radioJosebasElAutentico -> "josebaselautentico"
                    R.id.radioPapagiorgio -> "papagiorgio"
                    else -> ""
                }

                if (nombre.isNotEmpty() && tipo.isNotEmpty() && imagen.isNotEmpty()) {
                    // Insertar en la base de datos SQLite
                    val values = ContentValues()
                    values.put(DBHelper.COLUMN_NOMBRE, nombre)
                    values.put(DBHelper.COLUMN_TIPO, tipo)
                    values.put(DBHelper.COLUMN_IMAGEN, imagen)

                    db.insert(DBHelper.TABLE_NAME, null, values)
                }
            }

            builder.setNegativeButton("Cancelar") { _, _ -> }

            val dialog = builder.create()
            dialog.show()
        }

        contextoPrincipal = this
    }

    // Crear una función para cargar los datos de la base de datos y configurar el RecyclerView
    private fun cargarPersonajesDesdeDB() {
        val personajesList = ArrayList<Personaje>()

        // Realiza una consulta a la base de datos SQLite para obtener los personajes
        val cursor = db.query(DBHelper.TABLE_NAME, null, null, null, null, null, null)

        if (cursor.moveToFirst()) {
            do {
                val nombre = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NOMBRE))
                val tipo = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_TIPO))
                val imagen = cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_IMAGEN))

                val personaje = Personaje(nombre, tipo, imagen)
                personajesList.add(personaje)
            } while (cursor.moveToNext())
        }

        cursor.close()

        // Configura el RecyclerView
        val adapter = PersonajeAdapter(context, personajesList) // Debes ajustar esto
        binding.listaPersonajesRecycler.adapter = adapter
    }
}
