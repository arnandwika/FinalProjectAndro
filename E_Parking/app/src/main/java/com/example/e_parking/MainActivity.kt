package com.example.e_parking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            val username = edtUsername.text.toString();
            val password = edtPassword.text.toString();

            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Tolong Masukkan Email and Password", Toast.LENGTH_LONG).show()

            }
            if(username == "admin" && password == "admin"){

                setContentView(R.layout.home)
            }else{
                Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_LONG).show()
            }
        }
    }
}
