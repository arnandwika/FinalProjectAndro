package com.example.e_parking

import android.provider.ContactsContract
import android.renderscript.Sampler
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.util.*

class Firebase {
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myRef: DatabaseReference = database.getReference()

    fun connect(username:String, jam:String, id: String){

        val uid: String= FirebaseAuth.getInstance().currentUser!!.uid.toString()
        val user = username
        val book = ModelQrcode(id,jam, user)
        myRef.child("booking").child(uid).setValue(book)
//        myRef.apply {
////            myRef.child("username").setValue(username)
//            myRef.child(uid).child("id_tempat").setValue(id)
//            myRef.child(uid).child("jam_masuk").setValue(jam)
//            myRef.child(uid).child("user").setValue(user)
//        }

//        Log.d("PRINTNYA", myRef.key!!.get(0).toString())
//        myRef.setValue("Hello, World!");
    }

}