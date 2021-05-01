package com.log450.bchainvoteversion1.Model

import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import java.util.*

class VaccinationCard(
    private val lastName:String,
    private val firstName:String,
    private val typeVaccine:VaccineType,
    private val batchNo:String,
    private val dateOfVaccine:Date,
    private val candidate:Candidate,
) {

    private fun getLastName():String{
        return lastName
    }

    private fun getFirstName():String{
        return firstName
    }

    private fun getNameVaccine():VaccineType{
        return typeVaccine
    }

    private fun getBatchNo():String{
        return batchNo
    }

    private fun getDateOfVaccine():Date{
        return dateOfVaccine
    }

    private fun getCandidate():Candidate{
        return candidate
    }
}