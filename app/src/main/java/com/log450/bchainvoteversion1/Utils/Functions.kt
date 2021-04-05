package com.log450.bchainvoteversion1.Utils

import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import com.log450.bchainvoteversion1.Model.VaccineType
private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

fun validateCredentials(mEditText:EditText, emailAddress:Boolean):String {
    var text = ""
    if(emailAddress){
        if(!validateEmail(mEditText.text.toString().trim())){
            mEditText.error = "Invalid Email Address";
        }

        else{
            text = mEditText.text.toString().trim()
            if (TextUtils.isEmpty(text)) {
                mEditText.error = "Input is required";
            }
        }
    }
    else {
         text = mEditText.text.toString().trim()
        if (TextUtils.isEmpty(text)) {
            mEditText.error = "Input is required";
        }
    }
    return text
}

fun validateEmail(email:String): Boolean {
    var isValid = false
    if (email.matches(emailPattern.toRegex())) {
        isValid = true
    }
    return isValid
}

fun getVaccineFromApplication(vaccine: String): com.log450.bchainvoteversion1.Model.VaccineType{
    var v  =  VaccineType.MODERNA
    if(vaccine == "PFIZER"){
        v =   com.log450.bchainvoteversion1.Model.VaccineType.PFIZER
    }
    if(vaccine == "MODERNA"){
        v =   com.log450.bchainvoteversion1.Model.VaccineType.MODERNA
    }
    if(vaccine == "JOHNSON"){
        v =  com.log450.bchainvoteversion1.Model.VaccineType.JOHNSON
    }
    return v
}
