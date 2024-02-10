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
        val number = speedTestResult.uploadSpeed
        val df = DecimalFormat("#.#")
        val result = df.format(number)
        val finalStr = "$result Mb/s"

        val number2 = speedTestResult.downloadSpeed
        val df2 = DecimalFormat("#.#")
        val result2 = df2.format(number2)
        val finalStr2 = "$result2 Mb/s"

        callback.onUploadSpeedResult(finalStr)
        callback.onDownloadSpeedResult(finalStr2)
        callback.onTestFinished()



        Log.i("UploadSpeedTest", "UploadSpeedTest$finalStr")
        Log.i("DownloadSpeedTest", "DownloadSpeedTest$finalStr2")
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

    override fun onDownloadTestProgress(p0: Int, p1: Double, p2: Double) {

        Log.d("SpeedTestManager", "onDownloadTestProgress")
    }

    override fun onDownloadTestFinished(p0: Double) {
        Log.d("SpeedTestManager", "onDownloadTestFinished")
    }

    override fun onUploadTestStarted() {
        Log.d("SpeedTestManager", "onUploadTestStarted")
    }

    override fun onUploadTestProgress(p0: Int, p1: Double, p2: Double) {
        Log.d("SpeedTestManager", "onUploadTestProgress")
    }

    override fun onUploadTestFinished(p0: Double) {
        Log.d("SpeedTestManager", "onUploadTestFinished")
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
