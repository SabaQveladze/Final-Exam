package com.example.finalexamgreencard

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth

class PasswordReset:Fragment(R.layout.fragment_password_reset) {
    private lateinit var mail: EditText
    private lateinit var send: Button
    private lateinit var mAuth: FirebaseAuth



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        mail=view.findViewById(R.id.enterMail)
        send=view.findViewById(R.id.Send)



        send.setOnClickListener(){
            val mail = mail.text.toString().trim()

            if(android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                mAuth.sendPasswordResetEmail(mail).addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        Toast.makeText(activity, "პაროლის შეცვლის მოთხოვნა წარმატებით გაიგზავნა მეილზე", Toast.LENGTH_SHORT).show()
                        findNavController().popBackStack()

                    }
                }

            }
            else{
            Toast.makeText(activity, "მეილი შეიყვანეთ სწორი ფორმატით მაგალითად:'btu@gmail.com' ", Toast.LENGTH_SHORT).show()

        }
        }

    }
}