package com.log450.bchainvoteversion1.Repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Voter.Voter
import kotlinx.coroutines.tasks.await

class VotersRepository {

    private lateinit var voters: ArrayList<Voter>

    suspend fun initVoters(fbase : FirebaseFirestore) {
        voters = arrayListOf(
            Voter(1, "Frank", "Lucas", "FrankL"),
            Voter(2, "Vito", "Corleone", "VitoC"),
            Voter(3, "Al", "Capone", "AlC"),
            Voter(4, "Donald", "Carducci", "DonaldC"),
            Voter(5, "Tony", "Montanna", "TonyM")
        )

        for (voter in voters) {
            fbase.collection("voters").document(voter.getId().toString()).set(voter).await()
        }
    }
    suspend fun retrieveUserID(fbase : FirebaseFirestore, fauth : FirebaseAuth, email:String, password:String):Int{
        var isSigned = false
        var id = 99
        fauth.signInWithEmailAndPassword(email,password).addOnSuccessListener {
            isSigned = true
        }.await()

        if(isSigned){
            fbase.collection("voters").get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                        if (!result.isEmpty) {
                            if (document.get("authenticated").toString().toBoolean()
                                && document.get("email") == email
                            ) {
                                id = document.get("id").toString().toInt()
                                break
                            }
                            else{
                            }
                        }
                    }
                }.await()
        }
        return id

    }
    suspend fun createUserWithKey(fbase : FirebaseFirestore,fauth : FirebaseAuth,email:String, password: String, authenticationKey: String): Int {
        var userID = 99
        var user2 = ""
        var userAlreadyRegistered = true
        fbase.collection("voters").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (!result.isEmpty) {
                        if (authenticationKey == document.get("authenticationKey")
                                .toString() && !document.get("authenticated").toString().toBoolean()
                        ) {
                            userAlreadyRegistered = false
                            user2 = document.get("id").toString()
                            userID = document.get("id").toString().toInt()
                            break
                        }
                        else{
                        }
                    }
                }
            }.await()

        fbase.collection("voters").document()

        if(!userAlreadyRegistered){
            fauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{}.await()
            fbase.collection("voters").document(user2).update("email", email).await()
            fbase.collection("voters").document(user2).update("authenticated", "true").await()
        }

        return userID
    }

    suspend fun getHasVoted(fbase : FirebaseFirestore,id: String) : Boolean{

        val docRef = fbase.collection("voters")
            .document(id)
            .get()
            .addOnSuccessListener{}
            .await()
        return docRef.get("hasVoted").toString().toBoolean()

    }

    suspend fun updateVote(fbase : FirebaseFirestore,fauth : FirebaseAuth,id: String) : Boolean{
        var i = false
        fbase.collection("voters")
            .document(id)
            .update("hasVoted", "true")
            .addOnSuccessListener {
                i = true
            }
            .await()
        return i
    }

}