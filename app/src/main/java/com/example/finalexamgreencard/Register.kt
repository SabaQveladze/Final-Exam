package com.example.finalexamgreencard

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Register: Fragment(R.layout.fragment_register) {

    private lateinit var saxeli : EditText
    private lateinit var gvari : EditText
    private lateinit var email : EditText
    private lateinit var paroli : EditText
    private lateinit var parolisgameoreba : EditText
    private lateinit var piradiNom : EditText
    private lateinit var nomeri : EditText
    private lateinit var checkBox: CheckBox
    private lateinit var register: Button
    private lateinit var url:EditText
    private val auth = FirebaseAuth.getInstance()
    private val dbUserInfo: DatabaseReference = FirebaseDatabase.getInstance().getReference("UserInfo")




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        saxeli = view.findViewById(R.id.saxeli)
        gvari = view.findViewById(R.id.gvari)
        email = view.findViewById(R.id.email)
        paroli = view.findViewById(R.id.paroli)
        url= view.findViewById(R.id.url)
        parolisgameoreba = view.findViewById(R.id.parolisgameoreba)
        piradiNom = view.findViewById(R.id.piradinom)
        nomeri = view.findViewById(R.id.nomeri)
        checkBox = view.findViewById(R.id.checkBox)
        register = view.findViewById(R.id.register)



        register.setOnClickListener(){
            val saxeli = saxeli.text.toString().trim()
            val gvari = gvari.text.toString().trim()
            val email = email.text.toString().trim()
            val paroli = paroli.text.toString().trim()
            val parolisgameoreba = parolisgameoreba.text.toString().trim()
            val piradiNom = piradiNom.text.toString().trim()
            val nomeri = nomeri.text.toString().trim()
            val imageUrl = url.text.toString().trim()



            if (saxeli.isEmpty()){
                Toast.makeText(activity, "შეიყვანეთ სახელი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(gvari.isEmpty()){
                Toast.makeText(activity, "შეიყვანეთ გვარი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(email.isEmpty() || !email.contains("@") || !email.contains(".")){
                Toast.makeText(activity, "მეილი შეიყვანეთ სწორი ფორმატით", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            else if(paroli.isEmpty() || parolisgameoreba.isEmpty()){
                Toast.makeText(activity, "შეიყვანეთ პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(piradiNom.isEmpty() || piradiNom.length !=11){
                Toast.makeText(activity, "პირად ნომერში შეიყვანეთ 11 ციფრი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(parolisgameoreba!=paroli){
                Toast.makeText(activity, "საჭიროა გაიმეოროთ პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if (nomeri.isEmpty()
                || nomeri.contains("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") || nomeri.length != 9){
                Toast.makeText(activity, "შეიყვანეთ ნომერი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else if(!checkBox.isChecked){
                Toast.makeText(activity, "გთხოვთ დაადასტუროთ რომ ხართ სრულად აცრილი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                Toast.makeText(activity, "რეგისტრაცია წარმატებით დასრულდა", Toast.LENGTH_SHORT).show()

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,
                    paroli
                ).
                addOnCompleteListener {task ->
                    if(task.isSuccessful){
                        val userInfo = UserInfo(saxeli,gvari,piradiNom,nomeri,imageUrl)
                        dbUserInfo.child(auth.currentUser?.uid!!).setValue(userInfo)
                    }else{
                        Toast.makeText(activity, "რაღაც შეცდომაა!", Toast.LENGTH_SHORT).show()
                    }
                }
                findNavController().popBackStack()
        }
    }

    }



