package com.example.speedtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import kotlin.math.min

class SpeedometerView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GRAY
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 10f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 80f
        textAlign = Paint.Align.CENTER
    }

    private val scalePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        strokeWidth = 5f
        style = Paint.Style.STROKE
    }

    private val circleBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, androidx.appcompat.R.color.primary_material_dark)
        style = Paint.Style.FILL
    }

    private val goTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.GREEN
        textSize = 80f
        textAlign = Paint.Align.CENTER
    }

    private var progress = 0f
    private var speedText = ""
    private var isTestRunning = false

    companion object {
        fun dpToPx(dp: Int, context: Context): Int = (dp * context.resources.displayMetrics.density).toInt()
    }



    fun setProgress(progress: Float) {
        this.progress = progress
        speedText = String.format("%.1f Mb/s", progress)
        isTestRunning = true
        invalidate()
    }

    fun reset() {
        setProgress(0f)
        speedText = ""
        isTestRunning = false
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val defaultSizePx = dpToPx(450, context)
        val width = resolveSize(defaultSizePx, widthMeasureSpec)
        val height = resolveSize(defaultSizePx, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val diameter = min(width, height) - progressPaint.strokeWidth
        val angle = 360 * progress / 100
        val cx = width / 2f
        val cy = height / 2f
        val rectF = RectF(cx - diameter / 2, cy - diameter / 2, cx + diameter / 2, cy + diameter / 2)
        val radius = diameter / 2 - progressPaint.strokeWidth / 2
        canvas.drawCircle(cx, cy, radius, circleBackgroundPaint)

        val scaleRectF = RectF(cx - diameter / 2 + progressPaint.strokeWidth, cy - diameter / 2 + progressPaint.strokeWidth, cx + diameter / 2 - progressPaint.strokeWidth, cy + diameter / 2 - progressPaint.strokeWidth)
        for (i in 0..100 step 10) {
            val scaleAngle = 360 * i / 100f
            canvas.drawArc(scaleRectF, scaleAngle - 90 - 1, 2f, false, scalePaint)
        }

        canvas.drawOval(rectF, backgroundPaint)
        canvas.drawArc(rectF, -90f, angle, false, progressPaint)

        val textY = cy - (textPaint.descent() + textPaint.ascent()) / 2
        if (isTestRunning) {
            canvas.drawText(speedText, cx, textY, textPaint)
        } else {
            canvas.drawText("0.0 Mb/s", cx, textY, goTextPaint)
        }
    }
}
