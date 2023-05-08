package com.example.customviews.PieDiagram.model

import android.os.Parcelable
import android.view.View

class ArcState(
    private val superSavedState: Parcelable?,
    val dataList: List<Pair<Int, String>>
) : View.BaseSavedState(superSavedState), Parcelable {
}