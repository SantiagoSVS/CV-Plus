package com.example.cvplus.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cvplus.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar los botones
        setupButtons()
    }

    /*private fun loadProfileData() {
        val sharedPref = requireActivity().getSharedPreferences(
            "profile", context.MODE_PRIVATE
        )
        binding.etName.setText(sharedPref.getString("name", "Deyvid Santiago Prada Ramos"))
        binding.etEmail.setText(sharedPref.getString("email", "dprada@poligran.edu.co"))
        binding.etPhone.setText(sharedPref.getString("phone", "3107125946"))
        binding.etAddress.setText(sharedPref.getString("address", "Bogotá, Colombia"))
    }*/

    private fun setupButtons() {
        // Botón de contacto (llamada)
        binding.btnContact.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:3016664770")
            }
            startActivity(intent)
        }

        // Botón de descargar CV (simulado)
        binding.btnDownloadCV.setOnClickListener {
            Toast.makeText(requireContext(), "Descargando CV...", Toast.LENGTH_SHORT).show()
            // Aquí puedes agregar la lógica para descargar un archivo PDF
        }

        // Hacer el número de teléfono clickeable
        binding.tvPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:3107125946")
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}