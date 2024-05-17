package com.juanfra.appcontactos.iu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.juanfra.appcontactos.data.ContactViewModel
import com.juanfra.appcontactos.data.model.Contacto
import com.juanfra.appcontactos.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ContactViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.contactoActual.observe(viewLifecycleOwner) {
            tratarDatos(it)
        }

    }

    fun tratarDatos(contacto: Contacto?) {
        if (contacto != null) {
            val cont = contacto!!

            binding.tiNombre.setText(cont.nombre)
            binding.tiApellido.setText(cont.apellidos)
            binding.tiTelefono.setText(cont.telefono.toString())
            binding.tiEmail.setText(cont.email)
            binding.tiEdad.setText(cont.edad.toString())
            binding.cbFav.isChecked = cont.fav

            binding.btSave.setOnClickListener {
                cont.nombre = binding.tiNombre.text.toString()
                cont.apellidos = binding.tiApellido.text.toString()
                cont.telefono = binding.tiTelefono.text.toString().toInt()
                cont.email = binding.tiEmail.text.toString()
                cont.edad = binding.tiEdad.text.toString().toInt()
                cont.fav = binding.cbFav.isChecked

                viewModel.updateContact(cont)
                findNavController().popBackStack()
            }

            binding.tvDelete.setOnClickListener {
                viewModel.deleteContact(cont)
                findNavController().popBackStack()
            }
        } else {
            binding.btSave.setOnClickListener {
                viewModel.addContact(
                    Contacto(
                        binding.tiNombre.text.toString(),
                        binding.tiApellido.text.toString(),
                        binding.tiTelefono.text.toString().toInt(),
                        binding.tiEmail.text.toString(),
                        binding.tiEdad.text.toString().toInt(),
                        binding.cbFav.isChecked
                    )
                )
                findNavController().popBackStack()
            }
            binding.tvDelete.visibility = TextView.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}