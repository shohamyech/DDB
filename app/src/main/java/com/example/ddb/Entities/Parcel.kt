package com.example.ddb.Entities

import android.telephony.PhoneNumberUtils
import androidx.databinding.BaseObservable

data class Parcel(var id:String = "") : BaseObservable() {
    var type: ParcelType = ParcelType.SMALL
    var isFragile:Boolean = false
    var weight:String = ""
    var latitude: String = ""
        set(value) {
            field = getValidLocation(field,value)
            notifyChange()
        }
    var longitude: String = ""
        set(value) {
            field = getValidLocation(field,value)
            notifyChange()
        }
    var recipient:String = ""
        set(value) {
            field = getValidRecipient(field,value)
            notifyChange()
        }
    var address: String = ""
        set(value) {
            field = getValidAddress(field,value)
            notifyChange()
        }
    var content: String = ""
        set(value) {
            field = getValidParcelContent(field,value)
            notifyChange()
        }
    var phone: String = ""
    var email: String = ""
    var status: Status = Status.IN_WAREHOUSE
    var delivers = ArrayList<User>()
    var chosenDeliverer:User? = null

    private fun getValidLocation(oldVal : String, newVal:String) : String
    {
        if (newVal.isEmpty()) return ""
        val res = newVal.toDoubleOrNull()
        if (res != null) return res.toString()
        return oldVal
    }

    private fun getValidRecipient(oldVal : String, newVal:String) : String
    {
        if (newVal.length > 20)
            return oldVal

        val re = "[^\\u0590-\\u05fe 'a-zA-Z]".toRegex()
        if (re.find(newVal) == null || newVal.isEmpty())
            return newVal

        return oldVal
    }

    private fun getValidParcelContent(oldVal : String, newVal:String) : String
    {
        if (newVal.length > 20)
            return oldVal

        val re = "[^\\u0590-\\u05fe 'a-zA-Z0-9]".toRegex()
        if (re.find(newVal) == null || newVal.isEmpty())
            return newVal

        return oldVal
    }

    private fun getValidAddress(oldVal : String, newVal:String) : String
    {
        if (newVal.length > 20)
            return oldVal

        val re = "[^\\u0590-\\u05fe 'a-zA-Z0-9]".toRegex()
        if (re.find(newVal) == null || newVal.isEmpty())
            return newVal

        return oldVal
    }
}