package com.example.ddb.Entities

import androidx.databinding.BaseObservable


data class User(var id:String = "") : BaseObservable(){
    var name:String = ""
    var email:String=""
    var phone:String=""

}