package com.example.ddb


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ddb.DATA.ParcelRepository
import com.example.ddb.Entities.Parcel
import com.example.ddb.Entities.ParcelType

class ParcelViewModel : ViewModel(){
    private var _parcel = MutableLiveData<Parcel>(Parcel())
    val parcel : Parcel get() = _parcel.value!!

    fun onTypeParcelChanged(type: String){

       when (type){
           "Envelope" -> parcel.type = ParcelType.Envelope
           "Big" -> parcel.type = ParcelType.Big
           "Small" -> parcel.type = ParcelType.Small
       }
    }

    fun saveParcelToDataBase() : Boolean =
         ParcelRepository.addParcel(parcel)

    fun validateLongitude() {


    }
}