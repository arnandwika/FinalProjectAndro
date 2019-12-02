package com.example.e_parking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        btnDaftar.setOnClickListener {
            signUp()
        }
    }

    private fun signUp(){
        if(edtEmail.text.toString().isEmpty()){
            Toast.makeText(baseContext, "Tolong Masukkan Email", Toast.LENGTH_LONG).show()
            edtEmail.requestFocus()
            return
        }
//        if(Patterns.EMAIL_ADDRESS.matcher(edtEmail.text.toString()).matches()){
//            Toast.makeText(baseContext, "Format Email Salah", Toast.LENGTH_LONG).show()
//            edtEmail.requestFocus()
//            return
//        }
        if(edtPassword.text.toString() != edtrepass.text.toString()){
            Toast.makeText(baseContext, "Password tidak sama", Toast.LENGTH_LONG).show()
            edtrepass.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(edtEmail.text.toString(),edtPassword.text.toString()).addOnCompleteListener(this) {task ->
            if(task.isSuccessful){
               startActivity(Intent(this,MainActivity::class.java))
                finish()
                Toast.makeText(baseContext, "Daftar Berhasil", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(baseContext, "Daftar Gagal", Toast.LENGTH_LONG).show()
            }}
    }

}
