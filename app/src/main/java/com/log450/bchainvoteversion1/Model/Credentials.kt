package com.log450.bchainvoteversion1.Model

data class Credentials(
    private val personInfo : Person,
    private val fingerPrint:String){

    public fun getFirstName():String{
        return personInfo.getFirstName()
    }

    public fun getLastName():String{
        return personInfo.getLastName()
    }

    public fun getFingerPrint():String{
        return fingerPrint
    }


}
