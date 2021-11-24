package com.example.ddb.UI

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ddb.ParcelViewModel
import com.example.ddb.R
import com.example.ddb.databinding.FragmentParcelTypeBinding

class ParcelTypeFrag : Fragment() {

    private val model: ParcelViewModel by activityViewModels()
    private var _binding: FragmentParcelTypeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParcelTypeBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.parcelViewModel = model

        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_parcelType_to_parcelSpec)
        }
        binding.backBtn.setOnClickListener {
            startActivity(Intent(activity, MainActivity::class.java))
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}