package com.example.customviews.PieDiagram.customView

import android.content.Context
import android.util.AttributeSet
import android.view.View


class Arc @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :  View(context, attrs, defStyleAttr), ArcInterface {


    override fun setDataChart(list: List<Pair<Int, String>>) {}


    override fun startAnimation() {}

}