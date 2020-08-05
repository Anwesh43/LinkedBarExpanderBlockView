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
val strokeFactor : Int = 90
val sizeFactor : Float = 3.5f
val backColor : Int = Color.parseColor("#BDBDBD")
val delay : Long = 20
val deg : Float = 90f
