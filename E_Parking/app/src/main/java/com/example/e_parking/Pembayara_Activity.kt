package com.example.e_parking

import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pembayara_)
//        var data: String =intent.getStringExtra("data")

        qr=findViewById(R.id.qrcode) as ImageView
        try{
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)
            val formatted = current.format(formatter)
            txtData.text="Data: "+formatted
            bitmap= TextToImageEncode(formatted)
            qr!!.setImageBitmap(bitmap)
        }catch (e: WriterException){
            e.printStackTrace()
        }

        kembali.setOnClickListener {
            val i = Intent(baseContext, Home_Activity::class.java)
            startActivity(i)
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
