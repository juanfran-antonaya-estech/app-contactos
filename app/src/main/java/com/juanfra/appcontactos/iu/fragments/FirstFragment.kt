package com.juanfra.appcontactos.iu.fragments

import AdaptadorContactos
import android.animation.TimeInterpolator
import android.graphics.Interpolator
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.juanfra.appcontactos.R
import com.juanfra.appcontactos.data.ContactViewModel
import com.juanfra.appcontactos.data.model.Contacto
import com.juanfra.appcontactos.databinding.FragmentFirstBinding
import com.juanfra.appcontactos.iu.adapters.ClickListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var adapter : AdaptadorContactos
    private val viewModel: ContactViewModel by activityViewModels<ContactViewModel> {
        ContactViewModel.ContactViewModelFactory(requireContext())
    }
    private var menuOpened = false
    private var favorites = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO buscar por nombre
        setupAdapter()
        viewModel.nameLiveData.value = ""
        viewModel.nameLiveData.observe(viewLifecycleOwner){
            viewModel.buscarPorNombre(it)
        }
        viewModel.contactosLiveData.observe(viewLifecycleOwner){
            adapter.actualizarContactos(it)
        }

        binding.fabMain.setOnClickListener{
            openCloseMenu()
        }
        closeMenu()

        val listenah = object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        }

        binding.svContacts.setOnQueryTextListener(listenah)


    }

    fun addRandom(count: Int){

    }

    fun openCloseMenu(){
        if (menuOpened){

            closeMenu()
        } else {
            menuOpened = !menuOpened

            openMenu()
        }

    }
    fun closeMenu(){
        menuOpened = false
        binding.fabMain.setImageResource(R.drawable.arrowup)
        binding.fabRemoveall.animate().translationY(200f).rotation(180F).alpha(0f).setInterpolator(null).setDuration(300).start()
        binding.fabAdd.animate().translationY(300f).rotation(180F).alpha(0f).setInterpolator(null).setDuration(300).start()

        binding.fabAdd.setOnClickListener{
        }
        binding.fabRemoveall.setOnClickListener{
        }
    }
    fun openMenu(){
        menuOpened = true
        binding.fabMain.setImageResource(R.drawable.arrowdown)
        binding.fabRemoveall.animate().translationY(0f).rotation(0F).alpha(1f).setInterpolator(null).setDuration(300).start()
        binding.fabAdd.animate().translationY(0f).rotation(0F).alpha(1f).setInterpolator(null).setDuration(300).start()

        binding.fabAdd.setOnClickListener{
            viewModel.contactoActual.value = null
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.fabRemoveall.setOnClickListener{
            viewModel.borrarTodo()
        }
    }

    fun setupAdapter(){
        adapter = AdaptadorContactos(ArrayList<Contacto>(), object : ClickListener{
            override fun onClick(contacto: Contacto) {
                viewModel.contactoActual.value = contacto
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }

        })
        val layoutManager = LinearLayoutManager(requireContext())
        binding.rv1.adapter = adapter
        binding.rv1.layoutManager = layoutManager


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}