package com.example.speedtest


import android.content.Context
import android.util.Log
import com.speedchecker.android.sdk.Public.SpeedTestListener
import com.speedchecker.android.sdk.Public.SpeedTestResult
import com.speedchecker.android.sdk.SpeedcheckerSDK

class SpeedTestManager(context: Context) : SpeedTestListener {

    private val mContext: Context = context

    init {
        SpeedcheckerSDK.init(mContext)
        SpeedcheckerSDK.SpeedTest.setOnSpeedTestListener(this)
    }

    override fun onTestStarted() {
        Log.i("onTestStarted", "onTestStarted")

    }

    override fun onFetchServerFailed() {
        Log.i("onFetchServerFailed", "onFetchServerFailed")

    }

    override fun onFindingBestServerStarted() {
        Log.i("onFindingBestServerStarted", "onFindingBestServerStarted")

    }

    override fun onTestFinished(speedTestResult: SpeedTestResult) {

        Log.i("onTestFinished", "onTestFinished")
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
