package com.example.e_parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_.*


class Home_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_)
        val user = intent.getStringExtra("user")
        Toast.makeText(baseContext, "masuk HOME", Toast.LENGTH_LONG).show()
        val uid = FirebaseAuth.getInstance().currentUser!!.uid.toString()
        val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("pembayaran/$uid")
        myRef.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                val hasil = datasnapshot.getValue(ClassSaldo::class.java)
                txtSaldo.text="Rp "+hasil!!.saldo
            }

        })

        btnBayar.setOnClickListener {
           // Toast.makeText(baseContext, "berhasil Bayar", Toast.LENGTH_LONG).show()
            val i = Intent(baseContext, Pembayara_Activity::class.java)
            i.putExtra("user",user)
            startActivity(i)
            finish()
        }

        btnScan.setOnClickListener {
//            Toast.makeText(baseContext, "berhasil scan", Toast.LENGTH_LONG).show()
            val i = Intent(baseContext, scan_Activity::class.java)
            i.putExtra("user", user)
            startActivity(i)
            finish()
        }
        imgTopUp.setOnClickListener {
            val i = Intent(baseContext, TopUpActivity::class.java)

            startActivity(i)
        }
    }
}
