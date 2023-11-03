import Modelo.Personaje
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.cardview.widget.CardView
import com.ramon.recyclerpersonajes.R

class PersonajeAdapter(private val context: ArrayList<Personaje>) :

    RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {

    private val personajesList = ArrayList<Personaje>()

    // Función para establecer la lista de personajes
    fun setPersonajes(personajes: List<Personaje>) {
        personajesList.clear()
        personajesList.addAll(personajes)
        notifyDataSetChanged() // Notificar cambios en el adaptador
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personaje = personajesList[position]

        holder.txtNombre.text = personaje.nombre
        holder.txtTipo.text = personaje.tipo

        // Configura la imagen utilizando una ImageView y recursos de imagen
        val imagenResource = when (personaje.imagen) {
            "chiquiibai" -> R.drawable.chiquiibai
            "jordiwild" -> R.drawable.jordi
            "josebaselautentico" -> R.drawable.josebas
            "papagiorgio" -> R.drawable.papagiorgio
            else -> R.drawable.mbape
        }

        holder.imgImagen.setImageResource(imagenResource)
    }

    override fun getItemCount(): Int {
        return personajesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView: CardView = itemView.findViewById(R.id.cardView)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombre)
        val txtTipo: TextView = itemView.findViewById(R.id.txtTipo)
        val imgImagen: ImageView = itemView.findViewById(R.id.imgImagen)
    }
}
