package io.mishkav.customattributessample.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import io.mishkav.customattributessample.R

class PacManView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private var ghostColor: Int
    private var pacmanColor: Int
    private var isEating: Boolean

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PacManView,
            0, 0
        ).apply {
            try {
                pacmanColor = getColor(R.styleable.PacManView_pacmanColor, Color.YELLOW)
                ghostColor = getColor(R.styleable.PacManView_ghostColor, Color.RED)
                isEating = getBoolean(R.styleable.PacManView_isEating, false)
            } finally {
                recycle()
            }
        }
    }

    fun setIsEating(eating: Boolean) {
        isEating = eating
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()
        val pacmanRadius = (width / 2.5).toFloat()
        val pacmanEyeRadius = (width / 16).toFloat()

        val ghostRadius = (width / 8).toFloat()
        val ghostEyeRadius = (width / 36).toFloat()

        if (isEating) {
            drawPacman(
                canvas,
                centerX,
                centerY,
                pacmanRadius,
                pacmanEyeRadius
            )
        } else {

            drawEatPacman(
                canvas,
                centerX,
                centerY,
                pacmanRadius,
                pacmanEyeRadius
            )

            drawGhost(
                canvas,
                centerX,
                centerY,
                ghostRadius,
                ghostEyeRadius
            )
        }
    }

    private fun drawGhost(
        canvas: Canvas?,
        centerX: Float,
        centerY: Float,
        ghostRadius: Float,
        ghostEyeRadius: Float
    ) {
        val ghostPaint = Paint().apply {
            color = ghostColor
            style = Paint.Style.FILL
        }

        val ghostRectF = RectF().apply {
            left = centerX + width / 4 - ghostRadius
            top = centerY - ghostRadius
            right = centerX + width / 4 + ghostRadius
            bottom = centerY + ghostRadius
        }

        val ghostLeftLegRectF = RectF().apply {
            left = centerX + width / 4 - ghostRadius
            top = centerY - ghostRadius / 2
            right = centerX + width / 4
            bottom = centerY + ghostRadius / 2
        }

        val ghostRightLegRectF = RectF().apply {
            left = centerX + width / 4
            top = centerY - ghostRadius / 2
            right = centerX + width / 4 + ghostRadius
            bottom = centerY + ghostRadius / 2
        }

        val ghostEyePaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        val pupilPaint = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.FILL
        }

        //Ghost
        canvas?.drawArc(ghostRectF, 180F, 180F, true, ghostPaint)
        canvas?.drawArc(ghostLeftLegRectF, 0F, 180F, true, ghostPaint)
        canvas?.drawArc(ghostRightLegRectF, 0F, 180F, true, ghostPaint)

        //Eyes
        canvas?.drawCircle(
            centerX + width / 3 - ghostEyeRadius,
            centerY - ghostRadius / 2,
            ghostEyeRadius,
            ghostEyePaint
        )

        canvas?.drawCircle(
            centerX + width / 3 - ghostEyeRadius,
            centerY - ghostRadius / 2,
            ghostEyeRadius / 4,
            pupilPaint
        )

        canvas?.drawCircle(
            centerX + width / 4,
            centerY - ghostRadius / 2,
            ghostEyeRadius,
            ghostEyePaint
        )

        canvas?.drawCircle(
            centerX + width / 4,
            centerY - ghostRadius / 2,
            ghostEyeRadius / 4,
            pupilPaint
        )
    }

    private fun drawPacman(
        canvas: Canvas?,
        centerX: Float,
        centerY: Float,
        pacmanRadius: Float,
        pacmanEyeRadius: Float
    ) {
        val pacmanPaint = Paint().apply {
            color = pacmanColor
            strokeWidth = 5F
            style = Paint.Style.FILL
        }

        val pacmanEyePaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        val pupilPaint = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.FILL
        }

        val mousePaint = Paint().apply {
            color = Color.BLACK
            strokeWidth = 12F
            style = Paint.Style.FILL
        }

        // Pacman
        canvas?.drawCircle(
            centerX,
            centerY,
            pacmanRadius,
            pacmanPaint
        )

        // Eyes
        canvas?.drawCircle(
            centerX,
            centerY - pacmanRadius / 2,
            pacmanEyeRadius,
            pacmanEyePaint
        )
        canvas?.drawCircle(
            centerX + pacmanEyeRadius / 2,
            centerY - pacmanRadius / 2 ,
            pacmanEyeRadius / 4,
            pupilPaint
        )

        //Mouse
        canvas?.drawLine(
            centerX,
            centerY,
            centerX + pacmanRadius,
            centerY ,
            mousePaint
        )
    }

        private fun drawEatPacman(
        canvas: Canvas?,
        centerX: Float,
        centerY: Float,
        pacmanRadius: Float,
        pacmanEyeRadius: Float
    ) {
        val pacmanPaint = Paint().apply {
            color = pacmanColor
            strokeWidth = 5F
            style = Paint.Style.FILL
        }

        val pacmanRectF = RectF().apply {
            left = centerX - pacmanRadius
            top = centerY - pacmanRadius
            right = centerX + pacmanRadius
            bottom = centerY + pacmanRadius
        }

        val pacmanEyePaint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.FILL
        }

        val pupilPaint = Paint().apply {
            color = Color.BLUE
            style = Paint.Style.FILL
        }

        // Pacman
        canvas?.drawArc(pacmanRectF, 45F, 270F, true, pacmanPaint)

        // Eyes
        canvas?.drawCircle(
            centerX,
            centerY - pacmanRadius / 2,
            pacmanEyeRadius,
            pacmanEyePaint
        )
        canvas?.drawCircle(
            centerX + pacmanEyeRadius / 4,
            centerY - pacmanRadius / 2 + pacmanEyeRadius / 4,
            pacmanEyeRadius / 4,
            pupilPaint
        )
    }
}