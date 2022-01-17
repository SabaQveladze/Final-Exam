package com.example.finalexamgreencard

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class Avtorizacia: Fragment(R.layout.fragment_avtorizacia) {

    private lateinit var register: Button
    private lateinit var avtorizacia : Button
    private lateinit var agdgena : Button
    private lateinit var email : EditText
    private lateinit var paroli:EditText
    private lateinit var checkbox: CheckBox


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register=view.findViewById(R.id.registracia)
        avtorizacia=view.findViewById(R.id.avtorizacia)
        agdgena = view.findViewById(R.id.agdgena)
        email= view.findViewById(R.id.email)
        paroli= view.findViewById(R.id.paroli)
        checkbox=view.findViewById(R.id.checkBox)

        register.setOnClickListener(){

            findNavController().navigate(R.id.action_avtorizacia2_to_register2)

        }
        avtorizacia.setOnClickListener() {
            val email = email.text.toString().trim()
            val paroli = paroli.text.toString().trim()
            
                if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
                    Toast.makeText(
                        activity,
                        "გთხოვთ შეიყვანოთ მეილის სწორი ფორმატი",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                } else if (paroli.isEmpty()) {
                    Toast.makeText(activity, "გთხოვთ შეიყვანოთ პაროლი", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (!checkbox.isChecked) {
                    Toast.makeText(
                        activity,
                        "გთხოვთ დაეთანხმოთ წესებს და პირობებს",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,paroli).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    findNavController().navigate(R.id.action_avtorizacia2_to_profile)
                }else
                    Toast.makeText(activity, "გთხოვთ პირველ რიგში დარეგისტრირდეთ", Toast.LENGTH_SHORT).show()
            }
               



        }
        agdgena.setOnClickListener() {
            findNavController().navigate(R.id.action_avtorizacia2_to_passwordReset2)
        }

        }

}

