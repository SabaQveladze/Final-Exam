package com.example.finalexamgreencard

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text

class Profile: Fragment(R.layout.fragment_profile) {
    private lateinit var logOutBtn: Button
    private lateinit var textNumber: TextView
    private lateinit var textID: TextView
    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")
    private lateinit var textGvari: TextView
    private lateinit var textSaxeli: TextView
    private lateinit var textViewGvari: TextView
    private lateinit var textViewSaxeli: TextView
    private lateinit var progressBar :ProgressBar
    private lateinit var textViewID:TextView
    private lateinit var foto : ImageView
    private lateinit var textViewNomeri:TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewSaxeli= view.findViewById(R.id.textViewSaxeli)
        textViewGvari= view.findViewById(R.id.textViewGvari)
        textViewID= view.findViewById(R.id.textViewID)
        textViewNomeri= view.findViewById(R.id.textViewNomeri)
        progressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility=View.VISIBLE
        foto = view.findViewById(R.id.foto)

        dbUserInfo.child(auth.currentUser?.uid!!).addValueEventListener(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
               val userinfo = snapshot.getValue(UserInfo::class.java) ?: return
                textViewSaxeli.text = userinfo.saxeli
                textViewGvari.text=userinfo.gvari
                textViewID.text=userinfo.piradiNom
                textViewNomeri.text=userinfo.nomeri
                progressBar.visibility=View.GONE

                Glide.with(this@Profile).load(userinfo.url).into(foto)


            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

        textNumber = view.findViewById(R.id.textNumber)
        textID = view.findViewById(R.id.textID)
        textGvari = view.findViewById(R.id.textGvari)
        textSaxeli = view.findViewById(R.id.textSaxeli)
        logOutBtn = view.findViewById(R.id.logOutBtn)



        logOutBtn.setOnClickListener {
            findNavController().popBackStack()
            auth.signOut()
            Toast.makeText(activity, "Log Out წარმატებით განხორციელდა!", Toast.LENGTH_SHORT).show()
        }
    }
}



