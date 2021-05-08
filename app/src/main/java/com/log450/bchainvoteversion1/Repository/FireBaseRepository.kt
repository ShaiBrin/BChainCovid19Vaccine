package com.log450.bchainvoteversion1.Repository


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.log450.bchainvoteversion1.Actors.Candidate.Candidate
import com.log450.bchainvoteversion1.Actors.Candidate.VaccineStats
import com.log450.bchainvoteversion1.Actors.Electoral.Electoral
import com.log450.bchainvoteversion1.Model.Block
import com.log450.bchainvoteversion1.Model.VaccineType
import com.log450.bchainvoteversion1.Network.VaccineAPI
import kotlinx.coroutines.tasks.await


class FireBaseRepository {

    private var fbase = FirebaseFirestore.getInstance()
    private var fauth = FirebaseAuth.getInstance()

    private var fCandidatesRepository: CandidatesRepository = CandidatesRepository()
    private var fVotersRepository: VotersRepository = VotersRepository()
    private var fBlockChainRepository: BlockchainRepository = BlockchainRepository()


    init {
        initElectoral()
    }

    suspend fun initVoters() {
        fVotersRepository.initVoters(fbase)
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
        return fBlockChainRepository.getBlockchain(fbase)
    }
    suspend fun setPreviousHash(value: String): Boolean {
        return fBlockChainRepository.setPreviousHash(fbase, value )
    }



    /************************* VOTERS FUNCTIONS     START     ******************************/

    suspend fun retrieveUserID(email:String, password:String):Int{
        return fVotersRepository.retrieveUserID(fbase,fauth,email, password)
    }

    suspend fun createUserWithKey(authenticationKey: String, email:String, password: String): Int {
        return fVotersRepository.createUserWithKey(fbase,fauth,email, password,authenticationKey)
    }

    suspend fun getHasVoted(id: String) : Boolean{
        return fVotersRepository.getHasVoted(fbase,id)
    }

    suspend fun updateVote(id: String) : Boolean{
        return fVotersRepository.updateVote(fbase,fauth, id)
    }

    /************************* CANDIDATES FUNCTIONS     START     ******************************/
    suspend fun getTotalVaccine(): Int {
        return fCandidatesRepository.getTotalVaccine(fbase)
    }
    suspend fun getVaccineType(vaccineName: String): Int {
        return fCandidatesRepository.getVaccineType(fbase, vaccineName)
    }

    suspend fun getCandidatesDB(): ArrayList<Candidate> {
        return fCandidatesRepository.getCandidatesDB(fbase)
    }

    suspend fun createApplication(name: String, vaccine: VaccineType): Boolean {
        return fCandidatesRepository.createApplication(fbase, name, vaccine)
    }

    suspend fun voteCandidate(candidateName: String): Boolean {
        return fCandidatesRepository.voteCandidate(fbase, candidateName)
    }


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


    suspend fun getAPI(countryName:String): VaccineStats {
        return VaccineAPI().getAPI(countryName)
    }


}