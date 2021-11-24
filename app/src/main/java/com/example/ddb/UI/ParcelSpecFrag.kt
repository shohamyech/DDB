package com.example.ddb.UI

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ddb.ParcelViewModel
import com.example.ddb.R
import com.example.ddb.databinding.FragmentParcelSpecBinding


class ParcelSpecFrag : Fragment() {

    private val model: ParcelViewModel by activityViewModels()
    private var _binding: FragmentParcelSpecBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParcelSpecBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.parcelViewModel = model
        binding.lifecycleOwner = this

        binding.nextBtn.setOnClickListener {
            if (binding.weightEditText.text.isEmpty() ||
                    binding.longitudeEditText.text.isEmpty() ||
                    binding.latitudeEditText.text.isEmpty()) {
                binding.errorMsgText.setText(R.string.FillAllFieldsErrorMsg) }
            else {
                binding.errorMsgText.setText("")
                findNavController().navigate(R.id.action_parcelSpec_to_parcelDest)
            }
        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_parcelSpec_to_parcelType)
        }

        binding.weightEditText.inputType=InputType.TYPE_CLASS_NUMBER

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}