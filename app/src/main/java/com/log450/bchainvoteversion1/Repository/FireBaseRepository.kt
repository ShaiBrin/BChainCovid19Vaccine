package com.log450.bchainvoteversion1.Repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import com.log450.bchainvoteversion1.Actors.Electoral.Electoral
import com.log450.bchainvoteversion1.Actors.Voter.Voter
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.Network.VaccineAPI
import com.log450.bchainvoteversion1.Utils.getVaccineFromApplication
import kotlinx.coroutines.tasks.await


class FireBaseRepository {

    private var fbase = FirebaseFirestore.getInstance()
    private var fauth = FirebaseAuth.getInstance()
    private lateinit var voters: ArrayList<Voter>

    init {
        initElectoral()
    }

    suspend fun initVoters() {
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

    private fun initElectoral(){
        val electoral = Electoral (0, "Marc", "Bergevin", "Habs")
        fbase.collection("electoral").document("0").set(electoral)

    }



    suspend fun initiateBlockChain() : Boolean{
        var blockChainExist = false
        val genesis = Block("0", "0")
        fbase.collection("blockchain reference")
            .document("0")
            .get().addOnSuccessListener { document ->
                if (!document.exists()) {
                    fbase.collection("blockchain").document("0").set(genesis)
                    fbase.collection("blockchain reference").document("0").set(genesis)
                }
                else{
                    blockChainExist = true
                }
            }.await()
        return blockChainExist
    }

    /************************* BLOCKCHAIN FUNCTIONS     START     ******************************/
    suspend fun getBlockchain():ArrayList<Block>{
        val blockchain = arrayListOf<Block>()
        lateinit var block: Block
        fbase.collection("blockchain").get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        block = Block(
                            document.get("previousHash").toString(),
                            document.get("data").toString(),
                            document.get("hash").toString(),
                        )
                        blockchain.add(block)
                    }
                }
            }
            .await()
        return blockchain
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
    /************************* BLOCKCHAIN FUNCTIONS     END     ******************************/


    /************************* VOTERS FUNCTIONS     START     ******************************/


    suspend fun retrieveUserID(email:String, password:String):Int{
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
    suspend fun createUserWithKey(authenticationKey: String, email:String, password: String): Int {
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

    suspend fun getHasVoted(id: String) : Boolean{

        val docRef = fbase.collection("voters")
            .document(id)
            .get()
            .addOnSuccessListener{}
            .await()
        return docRef.get("hasVoted").toString().toBoolean()

    }

    suspend fun updateVote(id: String) : Boolean{
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
    /************************* VOTERS FUNCTIONS     END     ******************************/

    /************************* CANDIDATES FUNCTIONS     START     ******************************/
    suspend fun getTotalVaccine(): Int {
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
    suspend fun getVaccineType(vaccineName: String): Int {
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
    /************************* CANDIDATES FUNCTIONS     END     ******************************/


    suspend fun validateElector(electorKey:String):Boolean{
        var validated = false
        fbase.collection("electoral")
            .document("0")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists() && (electorKey == document.get("key").toString())){
                    validated = true
                }
            }.await()
        return validated
    }


    suspend fun getAPI(): VaccineStats {
        return VaccineAPI().getAPI()
    }


}