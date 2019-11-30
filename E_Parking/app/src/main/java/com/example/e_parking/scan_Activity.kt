package com.example.e_parking

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Vibrator
import android.provider.MediaStore
import android.util.SparseArray
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class scan_Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_)
        val user= intent.getStringExtra("user")
        val surfaceview: SurfaceView =findViewById(R.id.camerapreview)
        val text: TextView =findViewById(R.id.textView)

        var barcode: BarcodeDetector?=null
        barcode =  BarcodeDetector.Builder(applicationContext).setBarcodeFormats(Barcode.QR_CODE).build()
        val camerasource: CameraSource = CameraSource.Builder(this, barcode).setRequestedPreviewSize(640,480).build()

        surfaceview!!.holder!!.addCallback(object: SurfaceHolder.Callback {
            override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

            }

            override fun surfaceDestroyed(p0: SurfaceHolder?) {
                camerasource.stop()
            }

            override fun surfaceCreated(p0: SurfaceHolder?) {
                if(ActivityCompat.checkSelfPermission(applicationContext,android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    return
                }
                camerasource.start(p0)
            }

        })
        barcode.setProcessor(object: Detector.Processor<Barcode>{
            override fun release() {

            }

            override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
                val qrCodes: SparseArray<Barcode>
                qrCodes = detections!!.detectedItems
                if(qrCodes.size()!=0){
                    text.post(Runnable {
                        kotlin.run {
                            val vibrator: Vibrator
                            vibrator=applicationContext.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                            vibrator.vibrate(1000)
                            val kode = qrCodes.valueAt(0).displayValue
                            val current = LocalDateTime.now()
                            val formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss")
                            val formatted = current.format(formatter)
                            val i:Intent = Intent(baseContext, Pembayara_Activity::class.java)
                            i.putExtra("data", kode)
                            i.putExtra("jam", formatted)
                            i.putExtra("user",user)
                            Firebase().connect(user,formatted,kode)
                            startActivity(i)
                            finish()
                        }
                    })
                }
            }

        })
    }
}
