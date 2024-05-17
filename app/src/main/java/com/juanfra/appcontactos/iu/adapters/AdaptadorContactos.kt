import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanfra.appcontactos.data.model.Contacto
import com.juanfra.appcontactos.databinding.HolderContactoBinding
import com.juanfra.appcontactos.iu.adapters.ClickListener

class AdaptadorContactos(var listado: List<Contacto>, val listener: ClickListener) :
    RecyclerView.Adapter<AdaptadorContactos.MiCelda>() {

        var listaCopia = ArrayList<Contacto>()
    //Your holder here
    inner class MiCelda(var binding: HolderContactoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiCelda {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderContactoBinding.inflate(layoutInflater, parent, false)
        return MiCelda(binding)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: MiCelda, position: Int) {
        val contacto = listado[position]

        with(holder){
            binding.tvNomYApe.text = contacto.nombre + " " + contacto.apellidos
            binding.tvTlf.text = contacto.telefono.toString()
            binding.tvEmail.text = contacto.email

            itemView.setOnClickListener{
                listener.onClick(contacto)
            }
        }
    }

    fun actualizarContactos(contactos: List<Contacto>){
        listado = contactos
        notifyDataSetChanged()
    }


}