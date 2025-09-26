package com.example.cvplus.ui.slideshow

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cvplus.databinding.FragmentSlideshowBinding
import androidx.core.net.toUri
import com.example.cvplus.R

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uri = "android.resource://${requireContext().packageName}/${R.raw.demo}".toUri()
        binding.videoView.setVideoURI(uri)

         val controller = MediaController(requireContext())
         controller.setAnchorView(binding.videoView)
         binding.videoView.setMediaController(controller)

        binding.videoView.setOnPreparedListener { mp ->
            binding.videoView.start()
        }

        val uriImg = "android.resource://${requireContext().packageName}/${R.raw.profile_photo}".toUri()
        binding.imageProfile.setImageURI(uriImg)

        binding.imageProfile.background = ContextCompat.getDrawable(requireContext(), R.drawable.circle_background)
        binding.imageProfile.clipToOutline = true // recorta al contorno oval del background
        binding.imageProfile.scaleType = ImageView.ScaleType.CENTER_CROP

        setupRedirectionsLinks()
    }

    private fun setupRedirectionsLinks() {
        fun openExternal(urlText: String) {
            var url = urlText.trim()
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "https://$url"
            }
            val uri = Uri.parse(url)
            val intent = Intent(Intent.ACTION_VIEW, uri)

            if (intent.resolveActivity(requireContext().packageManager) != null) {
                startActivity(intent)
            } else {
                Toast.makeText(requireContext(), "No hay navegador disponible", Toast.LENGTH_SHORT).show()
            }
        }

        binding.contactLinkedin.setOnClickListener {
            openExternal(binding.contactLinkedin.text.toString())
        }
        binding.contactGithub.setOnClickListener {
            openExternal(binding.contactGithub.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}