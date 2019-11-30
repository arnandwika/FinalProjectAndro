package com.example.e_parking

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.zxing.*
import com.google.zxing.common.BitMatrix
import kotlinx.android.synthetic.main.activity_pembayara_.*
import org.w3c.dom.Text
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class Pembayara_Activity : AppCompatActivity() {
    internal var bitmap:Bitmap?=null
    private var qr:ImageView?=null
    val uid = FirebaseAuth.getInstance().currentUser!!.uid.toString()
    val myRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("booking/$uid")
    var tempat =""
    var jam =""
    var username=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayara_)

        myRef.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(datasnapshot: DataSnapshot) {
                val code = datasnapshot.getValue(ModelQrcode::class.java)
                tempat=code!!.id_tempat
                jam=code!!.jam_masuk
                username=code!!.user
                var hasil= username+"\n"+tempat+"\n"+jam
                qr=findViewById(R.id.qrcode) as ImageView
                txtData.text=username+"\n"+tempat+"\n"+jam
                try{
                    val qrcode = hasil
                    bitmap= TextToImageEncode(qrcode)
                    qr!!.setImageBitmap(bitmap)
                }catch (e: WriterException){
                    e.printStackTrace()
                }
            }

        })


        kembali.setOnClickListener {
            val i = Intent(baseContext, Home_Activity::class.java)
            startActivity(i)
            finish()
        }
    }
    @Throws(WriterException::class)
    private fun TextToImageEncode(Value: String): Bitmap? {
        val bitMatrix: BitMatrix
        try {
            bitMatrix = MultiFormatWriter().encode(
                Value,
                BarcodeFormat.QR_CODE,
                1000, 1000, null
            )

        } catch (Illegalargumentexception: IllegalArgumentException) {

            return null
        }

        val bitMatrixWidth = bitMatrix.getWidth()

        val bitMatrixHeight = bitMatrix.getHeight()

        val pixels = IntArray(bitMatrixWidth * bitMatrixHeight)

        for (y in 0 until bitMatrixHeight) {
            val offset = y * bitMatrixWidth

            for (x in 0 until bitMatrixWidth) {

                pixels[offset + x] = if (bitMatrix.get(x, y))
                    Color.BLACK
                else
                    Color.WHITE
            }
        }
        val bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_8888)

        bitmap.setPixels(pixels, 0, 1000, 0, 0, bitMatrixWidth, bitMatrixHeight)
        return bitmap
    }
}
