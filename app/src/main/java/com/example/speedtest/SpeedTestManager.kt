package com.example.speedtest


import android.content.Context
import android.util.Log
import com.speedchecker.android.sdk.Public.SpeedTestListener
import com.speedchecker.android.sdk.Public.SpeedTestResult
import com.speedchecker.android.sdk.SpeedcheckerSDK
import java.text.DecimalFormat

class SpeedTestManager(
    context: Context, private val callback: SpeedTestCallback) : SpeedTestListener {

    private val mContext: Context = context

    interface SpeedTestCallback {
        fun onUploadSpeedResult(result: String?)
        fun onDownloadSpeedResult(result: String?)
        fun onTestFinished()
        fun onTestInterrupted()
        fun onUploadProgress(progress: Double)
        fun onDownloadProgress(progress: Double)
    }

    init {
        SpeedcheckerSDK.init(mContext)
        SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this)
    }

    override fun onTestStarted() {
        TODO("Not yet implemented")
    }

    override fun onFetchServerFailed() {
        TODO("Not yet implemented")
    }

    override fun onFindingBestServerStarted() {
        TODO("Not yet implemented")
    }

    override fun onTestFinished(speedTestResult: SpeedTestResult) {
        callback.onTestFinished()

    }

    override fun onPingStarted() {
        Log.d("SpeedTestManager", "Ping started")
    }

    override fun onPingFinished(p0: Int, p1: Int) {
        Log.d("SpeedTestManager", "onPingFinished")
    }

    override fun onDownloadTestStarted() {
        Log.d("SpeedTestManager", "onDownloadTestStarted")
    }


    override fun onUploadTestStarted() {
        Log.d("SpeedTestManager", "onUploadTestStarted")
    }

    override fun onUploadTestProgress(p0: Int, p1: Double, p2: Double) {
        Log.d("SpeedTestManager", "onUploadTestProgress: progress=$p1")
        callback.onUploadProgress(p1)
    }

    override fun onDownloadTestProgress(p0: Int, p1: Double, p2: Double) {
        Log.d("SpeedTestManager", "onDownloadTestProgress: progress=$p1")
        callback.onDownloadProgress(p1)
    }


    override fun onUploadTestFinished(p0: Double) {
        val df = DecimalFormat("#.#")
        val finalStr = "${df.format(p0)} Mb/s"
        Log.d("SpeedTestManager", "onUploadTestFinished: $finalStr")
        callback.onUploadSpeedResult(finalStr)
    }

    override fun onDownloadTestFinished(p0: Double) {
        val df = DecimalFormat("#.#")
        val finalStr = "${df.format(p0)} Mb/s"
        Log.d("SpeedTestManager", "onDownloadTestFinished: $finalStr")
        callback.onDownloadSpeedResult(finalStr)
    }

    override fun onTestWarning(p0: String?) {
        Log.d("SpeedTestManager", "onTestWarning")
    }

    override fun onTestFatalError(p0: String?) {
        Log.d("SpeedTestManager", "onTestFatalError")
    }

    override fun onTestInterrupted(p0: String?) {
        Log.d("SpeedTestManager", "onTestInterrupted")
    }
}
