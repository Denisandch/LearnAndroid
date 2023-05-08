package com.example.customviews.PieDiagram.customView

interface ArcInterface {
    fun setDataChart(list: List<Pair<Int, String>>)

    fun startAnimation()
}