package com.example.e_parking

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_.*


class Home_Activity : AppCompatActivity() {
    var sp: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        sp = getSharedPreferences("sp", Context.MODE_PRIVATE)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_)
        Toast.makeText(baseContext, "masuk HOME", Toast.LENGTH_LONG).show()

        btnBayar.setOnClickListener {
           // Toast.makeText(baseContext, "berhasil Bayar", Toast.LENGTH_LONG).show()
            val i = Intent(baseContext, Pembayara_Activity::class.java)
            startActivity(i)
            finish()
        }

        btnScan.setOnClickListener {
//            Toast.makeText(baseContext, "berhasil scan", Toast.LENGTH_LONG).show()
            val i = Intent(baseContext, scan_Activity::class.java)
            startActivity(i)
        }

        btnLogout.setOnClickListener{
            sp?.edit()?.putBoolean("isLogin",false)?.commit()
            sp?.edit()?.putString("username","-")?.commit()
            sp?.edit()?.putString("password","-")?.commit()

            val i:Intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}
