package com.example.ddb.DATA


import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.example.ddb.Entities.Parcel

object ParcelRepository {

    private val database = Firebase.database
    private val parcelsRef = database.getReference("parcels")


    fun addParcel(parcel: Parcel) : Boolean{
        val key = parcelsRef.push().key
        if (key != null) {
            parcel.id = key
            parcelsRef.child(key).setValue(parcel)
            return true //succeeded
        }
        return false //failed

    }
}