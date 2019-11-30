package com.example.e_parking

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var sp :SharedPreferences?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp=getSharedPreferences("sp", Context.MODE_PRIVATE)
//        if(sp?.getBoolean("isLogin", false)!!){
//            val i= Intent(baseContext, Home_Activity::class.java)
//            i.putExtra("user", edtUsername.text.toString())
//            startActivity(i)
//            finish()
//        }else{
            setContentView(R.layout.activity_main)
            btnLogin.setOnClickListener {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(edtUsername.text.toString(),edtPassword.text.toString())
                    .addOnSuccessListener {
                        Toast.makeText(baseContext, "Login Berhasil", Toast.LENGTH_LONG).show()
                        val i= Intent(baseContext, Home_Activity::class.java)
                        i.putExtra("user", edtUsername.text.toString())
//                        sp?.edit()?.putBoolean("isLogin", true)?.commit()
                        startActivity(i)
                        finish()
                    }
                    .addOnFailureListener {
                        //                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(edtEmail.text.toString(), edtPassword.text.toString())
                        Toast.makeText(baseContext, "Username atau password salah!", Toast.LENGTH_LONG).show()
                    }
            }
//        }


    }
}
