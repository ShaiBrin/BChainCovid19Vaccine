package com.log450.bchainvoteversion1.Utils

import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import com.log450.bchainvoteversion1.Model.VaccineType

fun validateCredentials(mEditText:EditText):String {
    val text = mEditText.text.toString().trim()
    if (TextUtils.isEmpty(text)) {
        mEditText.error = "Input is required";
    }
    return text
}

fun getVaccineFromApplication(vaccine: String): com.log450.bchainvoteversion1.Model.VaccineType{
    var v  =  VaccineType.MODERNA
    if(vaccine == "PFIZER"){
        v =   com.log450.bchainvoteversion1.Model.VaccineType.PFIZER
    }
    if(vaccine == "MODERNA"){
        v =   com.log450.bchainvoteversion1.Model.VaccineType.MODERNA
    }
    if(vaccine == "JOHNSON&amp;JOHNSON"){
        v =  com.log450.bchainvoteversion1.Model.VaccineType.JOHNSON
    }
    return v
}
