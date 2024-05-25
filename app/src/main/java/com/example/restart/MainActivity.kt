package com.example.restart

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val signupbtn=findViewById<Button>(R.id.btnSignUp)
        val etName=findViewById<TextInputEditText>(R.id.etName)
        val etMail=findViewById<TextInputEditText>(R.id.etMail)
        val userid=findViewById<TextInputEditText>(R.id.etUserName)
        val userpassword=findViewById<TextInputEditText>(R.id.etPassword)

        signupbtn.setOnClickListener{
            val name=etName.text.toString()
            val mail=etMail.text.toString()
            val uniqueid=userid.text.toString()
            val password=userpassword.text.toString()
            val user=users(name,mail,password,uniqueid)
            database= FirebaseDatabase.getInstance().getReference("users")
            database.child(uniqueid).setValue(user).addOnSuccessListener { Toast.makeText(this,"user registered",
                Toast.LENGTH_SHORT).show() }.addOnFailureListener{ Toast.makeText(this,"user registered",Toast.LENGTH_SHORT).show() }
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}