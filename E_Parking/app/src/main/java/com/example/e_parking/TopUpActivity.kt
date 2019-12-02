package com.example.e_parking

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_home_.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_top_up.*

class TopUpActivity : AppCompatActivity() {
    public var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)

        btnTopUp.setOnClickListener {
            var nominal: Int
            var jum: String = txtTopUp.text.toString()
            val uid = FirebaseAuth.getInstance().currentUser!!.uid.toString()
            val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("pembayaran/$uid")
            myRef.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                }

                override fun onDataChange(datasnapshot: DataSnapshot) {
                    if(count < 1){
                        val hasil = datasnapshot.getValue(ClassSaldo::class.java)
                        nominal = hasil!!.saldo + jum.toInt()
                        myRef.child("saldo").setValue(nominal)
                        count++
                        val builder = AlertDialog.Builder(this@TopUpActivity)
                        builder.setTitle("Pemberitahuan")
                        builder.setMessage("Berhasil Top Up, Jumlah Saldo Rp " + nominal)
                        builder.setPositiveButton("Yes"){dialog: DialogInterface?, id: Int ->
                            Toast.makeText(this@TopUpActivity, "yes", Toast.LENGTH_SHORT).show()
                            val i = Intent(baseContext, Home_Activity::class.java)
                            startActivity(i)
                        }
                        builder.show()
                    }

                }

            })
        }

        btnBatal.setOnClickListener {
            val i = Intent(baseContext, Home_Activity::class.java)
            startActivity(i)
        }
    }
}
