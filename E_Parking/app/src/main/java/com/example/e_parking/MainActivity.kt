package com.example.e_parking

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    var sp: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("sp", Context.MODE_PRIVATE)

        if(sp?.getBoolean("isLogin",false)!!){

                    val i:Intent = Intent(applicationContext, Home_Activity::class.java)
                    startActivity(i)
                    finish()
            }

        else{
            setContentView(R.layout.activity_main)
            btnLogin.setOnClickListener {

                val user = edtUsername.text.toString()
                val pass = edtPassword.text.toString()

                if(user.isEmpty() || pass.isEmpty()){
                    Toast.makeText(this, "Tolong Masukkan Email and Password", Toast.LENGTH_LONG).show()

                }
                if(user == "admin" && pass == "admin"){
                    Toast.makeText(baseContext, "login berhasil", Toast.LENGTH_LONG).show()

                    sp?.edit()?.putBoolean("isLogin",true)?.commit()
                    sp?.edit()?.putString("username","admin")?.commit()
                    sp?.edit()?.putString("password","admin")?.commit()
                    val i:Intent = Intent(applicationContext, Home_Activity::class.java)
                    startActivity(i)
                    finish()
                }

                else{
                    Toast.makeText(this, "Email atau Password Salah", Toast.LENGTH_LONG).show()
                }
            }
        }




    }
}
