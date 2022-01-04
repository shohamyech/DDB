package com.example.ddb.UI

import android.content.Intent
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.ddb.ParcelViewModel
import com.example.ddb.R
import com.example.ddb.databinding.FragmentParcelDestBinding
import com.google.i18n.phonenumbers.PhoneNumberUtil

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberType

import com.google.i18n.phonenumbers.Phonenumber
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber
import java.lang.Exception
import android.text.TextUtils
import android.util.Patterns


class ParcelDestFrag : Fragment() {

    private val model: ParcelViewModel by activityViewModels()
    private var _binding: FragmentParcelDestBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentParcelDestBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set the viewmodel for databinding - this allows the bound layout access
        // to all the data in the ViewModel
        binding.parcelViewModel = model
        binding.lifecycleOwner = this

        binding.nextBtn.setOnClickListener {
            if (binding.recipientEditText.text.isEmpty() ||
                    binding.addressEditText.text.isEmpty() ||
                    binding.phoneEditText.text.isEmpty()) {
                binding.errorMsgText.setText(R.string.FillAllFieldsErrorMsg)
            }
            else if (!isPhoneNumberValid(model.parcel.phone)){
                binding.errorMsgText.setText(R.string.InvalidPhoneNumber)
            }
            else if(!isValidEmail(model.parcel.email))
            {
                binding.errorMsgText.setText(R.string.InvalidEmail)
            }
            else {
                binding.errorMsgText.text = ""
                val success =  model.saveParcelToDataBase()
                if (success)
                    Toast.makeText(activity?.applicationContext,getString(R.string.ParcelSavedSuccess),Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(activity?.applicationContext,getString(R.string.ParcelSavedFail),Toast.LENGTH_LONG).show()


                startActivity(Intent(activity, MainActivity::class.java))
            }

        }
        binding.backBtn.setOnClickListener {
            findNavController().navigate(R.id.action_parcelDest_to_parcelSpec)

        }
        return view
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun isPhoneNumberValid(mobNumber: String): Boolean {

        val re = "^0?([5]{1}\\d{8})\$".toRegex()
        return re.matches(mobNumber)
    }

    fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}