package com.anwesh.uiprojects.barexpanderblockview

/**
 * Created by anweshmishra on 06/08/20.
 */

import android.view.View
import android.view.MotionEvent
import android.content.Context
import android.graphics.Paint
import android.graphics.Color
import android.graphics.RectF
import android.graphics.Canvas
import android.app.Activity

val colors : Array<String> = arrayOf("#3F51B5", "#4CAF50", "#F44336", "#03A9F4", "#FFEB3B")
val parts : Int = 3
val scGap : Float = 0.02f / parts
val sizeFactor : Float = 3.5f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val deg : Float = 90f

fun Int.inverse() : Float = 1f / this
fun Float.maxScale(i : Int, n : Int) : Float = Math.max(0f, this - i * n.inverse())
fun Float.divideScale(i : Int, n : Int) : Float = Math.min(n.inverse(), maxScale(i, n)) * n
fun Float.sinify() : Float = Math.sin(this * Math.PI).toFloat()

fun Canvas.drawBarExpanderBlock(i : Int, scale : Float, w : Float, h : Float, paint : Paint) {
    val size : Float = Math.min(w, h) / sizeFactor
    val sf : Float = scale.sinify()
    val sf1 : Float = sf.divideScale(0, 2)
    val sf2 : Float = sf.divideScale(1, 2)
    save()
    scale(1f - 2 * i, 1f - 2 * i)
    translate(-w / 2, 0f)
    drawRect(RectF(0f, -size / 2, size * sf1, size / 2), paint)
    drawRect(RectF(-(w / 2 - size) * sf2, -size / 2, 0f, size / 2), paint)
    restore()
}

fun Canvas.drawBEBNode(i : Int, scale : Float, paint : Paint) {
    val w : Float = width.toFloat()
    val h : Float = height.toFloat()
    paint.color = Color.parseColor(colors[i])
    save()
    translate(w / 2, h / 2)
    drawBarExpanderBlock(i, scale, w, h, paint)
    restore()
}

class BarExpanderBlockView(ctx : Context) : View(ctx) {

    override fun onDraw(canvas : Canvas) {

    }

    override fun onTouchEvent(event : MotionEvent) : Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {

            }
        }
        return true
    }

    data class State(var scale : Float = 0f, var dir : Float = 0f, var prevScale : Float = 0f) {

        fun update(cb : (Float) -> Unit) {
            scale += scGap * dir
            if (Math.abs(scale - prevScale) > 1) {
                scale = prevScale + dir
                dir = 0f
                prevScale = scale
                cb(prevScale)
            }
        }

        fun startUpdating(cb : () -> Unit) {
            if (dir == 0f) {
                dir = 1f - 2 * prevScale
                cb()
            }
        }
    }
}