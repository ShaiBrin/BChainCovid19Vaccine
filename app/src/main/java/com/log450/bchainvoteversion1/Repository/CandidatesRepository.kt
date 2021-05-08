package com.log450.bchainvoteversion1.Repository

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.Utils.Functions
import kotlinx.coroutines.tasks.await

class CandidatesRepository {


    suspend fun getTotalVaccine(fbase : FirebaseFirestore): Int {
        var nbVaccine = 0
        fbase.collection("applications")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (!result.isEmpty) {
                        nbVaccine += document.get("nbVaccines").toString().toInt()
                    }
                }
            }.await()
        return nbVaccine
    }
    suspend fun getVaccineType(fbase : FirebaseFirestore,vaccineName: String): Int {
        var nbVaccine = 0
        fbase.collection("applications")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (!result.isEmpty) {
                        if (vaccineName == document.get("vaccine").toString()) {
                            nbVaccine += document.get("nbVaccines").toString().toInt()
                        }
                    }
                }
            }.await()
        return nbVaccine
    }

    suspend fun getCandidatesDB(fbase : FirebaseFirestore): ArrayList<Candidate> {
        val candidatesDB = arrayListOf<Candidate>()
        lateinit var candidate: Candidate
        fbase.collection("applications").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        candidate = Candidate(
                            document.get("name").toString(),
                            Functions.getVaccineFromApplication(document.get("vaccine").toString())
                        )
                        candidatesDB.add(candidate)
                    }
                }
            }
            .await()
        return candidatesDB
    }

    suspend fun createApplication(fbase : FirebaseFirestore,name: String, vaccine: VaccineType): Boolean {
        val candidate = Candidate(name, vaccine)
        fbase
            .collection("applications")
            .add(candidate)
            .await()
        return true
    }



    suspend fun voteCandidate(fbase : FirebaseFirestore,candidateName: String): Boolean {
        var candidateUpdated = false
        fbase.collection("applications")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (!result.isEmpty) {
                        if (candidateName == document.get("name").toString()) {
                            fbase
                                .collection("applications")
                                .document(document.id)
                                .update("nbVaccines", FieldValue.increment(1))
                            candidateUpdated = true
                            break
                        }
                    }

                }
            }.await()
        return candidateUpdated
    }
}