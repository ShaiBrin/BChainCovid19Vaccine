package com.log450.bchainvoteversion1.Repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import com.log450.bchainvoteversion1.Actors.Electoral.Electoral
import com.log450.bchainvoteversion1.Actors.Voter.Voter
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.Network.VaccineAPI
import com.log450.bchainvoteversion1.Utils.getVaccineFromApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext


class FireBaseRepository {

    private var fbase = FirebaseFirestore.getInstance()
    private var fauth = FirebaseAuth.getInstance()
    private lateinit var voters: ArrayList<Voter>
    private lateinit var electoral: Electoral
    private lateinit var candidate: Candidate

    init {
        initVoters()
        Blockchain
    }


    suspend fun setPreviousHash(value: String): Boolean {
        lateinit var block: Block
        lateinit var previousHash: String
        lateinit var previousDoc: String
        var blockAdded = false
        fbase.collection("blockchain reference")
            .document("0")
            .get().addOnSuccessListener { document ->
                if (!document.exists()) {

                } else {
                    previousHash = document.get("hash").toString()
                    previousDoc = document.get("data").toString()

                    block = Block(previousHash, value)
                    addBlock(block, previousDoc.toInt() + 1)
                    updateReference(block)
                    blockAdded = true
                }
            }.await()
        return blockAdded
    }

    private fun addBlock(b: Block, index: Int) {
        fbase.collection("blockchain").document(index.toString()).set(b)
    }

    private fun updateReference(b: Block) {
        fbase.collection("blockchain reference")
            .document("0")
            .update("data", FieldValue.increment(1))

        fbase.collection("blockchain reference")
            .document("0")
            .update("hash", b.getHash())
    }

    suspend fun getVaccines(vaccineName: String): Int {
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

    suspend fun getCandidatesDB(): ArrayList<Candidate> {
        val candidatesDB = arrayListOf<Candidate>()
        lateinit var candidate: Candidate
        fbase.collection("applications").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        candidate = Candidate(
                            document.get("name").toString(),
                            getVaccineFromApplication(document.get("vaccine").toString())
                        )
                        candidatesDB.add(candidate)
                    }
                }
            }
            .await()
        return candidatesDB
    }

    suspend fun createApplication(name: String, vaccine: VaccineType): Boolean {
        val candidate = Candidate(name, vaccine)
        fbase
            .collection("applications")
            .add(candidate)
            .await()
        return true
    }

    suspend fun updateVote(id: String) {
        fbase.collection("voters")
            .document(id)
            .update("hasVoted", "true")
            .addOnSuccessListener {}
            .await()
    }

    suspend fun voteCandidate(candidateName: String): Boolean {
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

    suspend fun createUserWithKey(authenticationKey: String, email: String, password: String): Int {
        //var userCreated = false
        var userID = 99
        fbase.collection("voters").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (!result.isEmpty) {
                        if (authenticationKey == document.get("authenticationKey")
                                .toString() && document.get("authenticated") == false
                        ) {
                            userID = document.get("id").toString().toInt()
                            //                  userCreated = true
                            //fauth.createUserWithEmailAndPassword(email, password)
                            //.addOnCompleteListener { task: Task<AuthResult> ->
                            //  if (task.isSuccessful) {
                            // fbase.collection("voters")
                            //      .document(document.id)
                            //        .update("authenticated", "true")
                            //      userCreated = true
                            //    }
                            //  }

                            break
                        }
                    }
                }
            }.await()
        return userID
    }

    suspend fun authenticatedUser(
        email: String,
        password: String,
        document: QueryDocumentSnapshot
    ): Boolean {
        var userCreated: Boolean = false

        fauth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task: Task<AuthResult> ->
                if (task.isSuccessful) {
                    fbase.collection("voters")
                        .document(document.id)
                        .update("authenticated", "true")
                    userCreated = true
                }
            }.await()

        return userCreated
    }


    suspend fun getVotersDB(): ArrayList<Voter> {
        val votersDB = arrayListOf<Voter>()
        lateinit var voter: Voter
        fbase.collection("voters").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        voter = Voter(
                            document.get("id").toString().toInt(),
                            document.get("firstName").toString(),
                            document.get("lastName").toString(),
                            document.get("authenticationKey").toString()
                        )
                        votersDB.add(voter)
                    }
                }
            }
            .await()
        return votersDB
    }

    suspend fun getAPI(): VaccineStats {
        return VaccineAPI().getAPI()
    }

    suspend fun incrementVaccine() {
        VaccineAPI().updateAPI()
    }

    private fun initVoters() {
        voters = arrayListOf(
            Voter(1, "Frank", "Lucas", "FrankL"),
            Voter(2, "Vito", "Corleone", "VitoC"),
            Voter(3, "Al", "Capone", "AlC"),
            Voter(4, "Donald", "Carducci", "DonaldC"),
            Voter(5, "Tony", "Montanna", "TonyM")
        )

        for (voter in voters) {
            fbase.collection("voters").document(voter.getId().toString()).set(voter)
        }
    }

}