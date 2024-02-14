package com.example.speedtest


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.speedchecker.android.sdk.SpeedcheckerSDK

class MainActivity : AppCompatActivity() {
    private lateinit var speedometerView: SpeedometerView
    private lateinit var startSpeedTestButton: Button
    private lateinit var downloadSpeedTextView: TextView
    private lateinit var uploadSpeedTextView: TextView
    private var mSpeedTestManager: SpeedTestManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        speedometerView = findViewById(R.id.speedometer)
        startSpeedTestButton = findViewById(R.id.startSpeedTestButton)
        downloadSpeedTextView = findViewById(R.id.downloadSpeedTextView)
        uploadSpeedTextView = findViewById(R.id.uploadSpeedTextView)
        startSpeedTestButton.setOnClickListener { SpeedcheckerSDK.SpeedTest.startTest(this@MainActivity) }


        mSpeedTestManager = SpeedTestManager(this, object : SpeedTestManager.SpeedTestCallback {


            override fun onUploadSpeedResult(result: String?) {
                uploadSpeedTextView.setText(result)
                Log.d("SpeedTest", "Vitesse d'upload reçue : $result")
            }

            override fun onDownloadSpeedResult(result: String?) {
                downloadSpeedTextView.setText(result)
                Log.d("SpeedTest", "Vitesse de téléchargement reçue : $result")
            }

            override fun onTestFinished() {
                runOnUiThread {
                    speedometerView.reset()
                }
            }

            override fun onTestInterrupted() {
                TODO("Not yet implemented")
            }

            override fun onUploadProgress(progress: Double) {
                runOnUiThread {
                    speedometerView.setProgress(progress.toFloat())
                }
            }

            override fun onDownloadProgress(progress: Double) {
                runOnUiThread {
                    speedometerView.setProgress(progress.toFloat())
                }
            }


        })


    }
}



