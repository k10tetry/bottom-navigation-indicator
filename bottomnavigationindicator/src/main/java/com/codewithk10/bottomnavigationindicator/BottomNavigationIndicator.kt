package com.codewithk10.bottomnavigationindicator

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.min

class BottomNavigationIndicator : View {

    companion object {
        private const val TAG = "BottomViewSelectionBar"
        private const val DEFAULT_BAR_COLOR = Color.BLACK
        private const val DEFAULT_BAR_SWEEP_COUNT = 4
        private const val DEFAULT_BAR_HEIGHT = 4f
        private const val DEFAULT_BAR_MAX_HEIGHT = 8f
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var barColor = DEFAULT_BAR_COLOR
        set(value) {
            field = value
            invalidate()
        }
    var barHeight = dpToFloat(DEFAULT_BAR_HEIGHT)
        set(value) {
            field = value
            invalidate()
        }
    var barCorners = BarCorners.ROUND
        set(value) {
            field = value
            invalidate()
        }
    var enableAnimation = true
    var barSweepCount = DEFAULT_BAR_SWEEP_COUNT
        set(value) {
            field = value
            invalidate()
        }
    private var currentSelectionIndex = 0f
    private var newSelectionIndex = 0f
    private var barCornerRadius = 0f
    var barMode = BarMode.FIXED
        set(value) {
            field = value
            invalidate()
        }
    private lateinit var barRectangle: RectF
    private lateinit var animator: ValueAnimator
    var currentIndex = 0
        get() = currentSelectionIndex.toInt()
        set(value) {
            field = value
            currentSelectionIndex = value.toFloat()
            invalidate()
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
        initAttributes(attributes)
    }

    private fun initAttributes(attributes: AttributeSet) {
        val typeArray = context.theme.obtainStyledAttributes(
            attributes,
            R.styleable.BottomNavigationIndicator,
            0,
            0
        )

        barColor = typeArray.getColor(R.styleable.BottomNavigationIndicator_barColor, Color.BLACK)
        barHeight =
            typeArray.getDimension(
                R.styleable.BottomNavigationIndicator_barHeight,
                dpToFloat(DEFAULT_BAR_HEIGHT)
            )
        barCorners = BarCorners.values()[typeArray.getInt(
            R.styleable.BottomNavigationIndicator_barCorners,
            0
        )]
        barMode = BarMode.values()[typeArray.getInt(
            R.styleable.BottomNavigationIndicator_barMode,
            0
        )]
        barSweepCount =
            typeArray.getInt(
                R.styleable.BottomNavigationIndicator_barSweepCount,
                DEFAULT_BAR_SWEEP_COUNT
            )
        typeArray.recycle()
    }

    fun setSelectedIndex(index: Int) {
        if (index in 0 until barSweepCount) {
            if (enableAnimation) {
                this.newSelectionIndex = index.toFloat()
                animateBar()
            } else {
                invalidate()
            }
            this.currentSelectionIndex = index.toFloat()
        }
    }

    private fun animateBar() {
        animator = ObjectAnimator.ofFloat(currentSelectionIndex, newSelectionIndex)
        animator.addUpdateListener {
            currentSelectionIndex = it.animatedValue as Float
            invalidate()
        }
        animator.duration = 300L
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        measureBarRectangle()
        drawBar(canvas)
    }

    private fun drawBar(canvas: Canvas?) {
        paint.color = barColor
        paint.style = Paint.Style.FILL
        canvas?.drawRoundRect(barRectangle, barCornerRadius, barCornerRadius, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(widthMeasureSpec, calculateBarHeight().toInt())
    }

    private fun calculateBarHeight(): Float {
        return min(barHeight, dpToFloat(DEFAULT_BAR_MAX_HEIGHT))
    }

    private fun dpToFloat(dips: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dips,
            context.resources.displayMetrics
        )
    }

    private fun measureBarRectangle() {
        val dividedWidth = (width / barSweepCount)
        var leftPoint = dividedWidth * currentSelectionIndex
        var rightPoint = dividedWidth * (currentSelectionIndex + 1f)

        if (barMode == BarMode.FIXED) {
            leftPoint += (dividedWidth / 4)
            rightPoint -= (dividedWidth / 4)
        }

        if (barCorners == BarCorners.ROUND) {
            barCornerRadius = barHeight / 2
        }

        barRectangle = RectF(leftPoint, 0f, rightPoint, barHeight)
    }

    enum class BarMode {
        FIXED, STRETCH
    }

    enum class BarCorners {
        ROUND, SHARP
    }

}