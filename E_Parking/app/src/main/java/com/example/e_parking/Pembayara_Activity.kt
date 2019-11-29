package com.example.e_parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import kotlinx.android.synthetic.main.activity_pembayara_.*

class Pembayara_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayara_)
        kembali.setOnClickListener {
            val i = Intent(baseContext, Home_Activity::class.java)
            startActivity(i)
            finish()
        }


    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            val i = Intent(baseContext, Home_Activity::class.java)
            startActivity(i)
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}
