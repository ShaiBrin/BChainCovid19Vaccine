package com.log450.bchainvoteversion1.Actors.Candidate

import com.log450.bchainvoteversion1.Model.VaccineType

class Candidate (
    private val name:String,
    private val vaccineType:VaccineType,
) {
    private var accepted:Boolean = false
    private var nbVaccines:Int = 0

    fun setAccepted(accepted:Boolean){
        this.accepted = accepted
    }
    fun incrementeCount(){
        nbVaccines++
    }

    fun getName():String{
        return name
    }

    fun getVaccine():VaccineType{
        return vaccineType
    }

    fun getNbVaccines():Int{
        return nbVaccines
    }

    fun getAccepted():Boolean{
        return accepted
    }
}