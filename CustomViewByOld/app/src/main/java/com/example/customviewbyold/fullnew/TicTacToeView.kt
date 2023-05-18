package com.example.customviewbyold.fullnew

import android.content.Context
import android.graphics.Color
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import com.example.customviewbyold.R
import java.lang.Float.min
import java.lang.Integer.max
import kotlin.properties.Delegates

class TicTacToeView(
    context: Context,
    attributeSet: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : View(context, attributeSet, defStyleAttr, defStyleRes) {

    var ticTacToeField: TicTacToeField? = null
        set(value) {
            field?.listeners?.remove(listener)
            field = value
            field?.listeners?.add(listener)
            updateViewSizes()
            requestLayout()
            invalidate()
        }

    private var playerFirstColor by Delegates.notNull<Int>()
    private var playerSecondColor by Delegates.notNull<Int>()
    private var gridColor by Delegates.notNull<Int>()

    private val fieldRect = RectF(0f, 0f, 0f, 0f)
    private var cellSize = 0f
    private var cellPadding = 0f

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attributeSet,
        defStyleAttr,
        R.style.DefaultTicTacToeStyle
    )

    constructor(context: Context, attributeSet: AttributeSet?) : this(
        context,
        attributeSet,
        R.attr.ticTacToeFieldStyle
    )

    constructor(context: Context) : this(context, null)

    init {
        if (attributeSet != null) {
            initAttributes(attributeSet, defStyleAttr, defStyleRes)
        } else {
            initDefault()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        ticTacToeField?.listeners?.add(listener)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minWidth = suggestedMinimumWidth + paddingLeft + paddingRight
        val minHeight = suggestedMinimumHeight + paddingTop + paddingBottom

        val desiredCellSizeInPixels = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            DESIRED_CELL_SIZE,
            resources.displayMetrics
        ).toInt()

        val rows = ticTacToeField?.rows ?: 0
        val columns = ticTacToeField?.columns ?: 0

        val desiredWidth =
            max(columns * desiredCellSizeInPixels + paddingLeft + paddingRight, minWidth)
        val desiredHeight =
            max(rows * desiredCellSizeInPixels + paddingBottom + paddingTop, minHeight)

        setMeasuredDimension(
            resolveSize(desiredWidth, widthMeasureSpec),
            resolveSize(desiredHeight, heightMeasureSpec)
        )
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateViewSizes()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        ticTacToeField?.listeners?.remove(listener)
    }

    private fun updateViewSizes() {
        val field = this.ticTacToeField ?: return

        val safeWidth = width - paddingLeft - paddingRight
        val safeHeight = height - paddingTop - paddingBottom

        val cellWidth = safeWidth / field.columns.toFloat()
        val cellHeight = safeHeight / field.rows.toFloat()

        cellSize = min(cellHeight, cellWidth)
        cellPadding = cellSize * 0.2f

        val fieldWidth = cellSize * field.columns
        val fieldHeight = cellHeight * field.rows

        fieldRect.left = paddingLeft + (safeWidth - fieldWidth) / 2
        fieldRect.top = paddingTop + (safeHeight - fieldHeight) / 2
        fieldRect.right = fieldRect.left + fieldWidth
        fieldRect.bottom = fieldRect.top + fieldHeight
    }

    private fun initAttributes(attributeSet: AttributeSet, defStyleAttr: Int, defStyleRes: Int) {
        val typedArray = context.obtainStyledAttributes(
            attributeSet,
            R.styleable.TicTacToeView,
            defStyleAttr,
            defStyleRes
        )

        playerFirstColor =
            typedArray.getColor(
                R.styleable.TicTacToeView_playerFirstColor,
                FIRST_PLAYER_DEFAULT_COLOR
            )
        playerSecondColor =
            typedArray.getColor(
                R.styleable.TicTacToeView_playerSecondColor,
                SECOND_PLAYER_DEFAULT_COLOR
            )
        gridColor =
            typedArray.getColor(R.styleable.TicTacToeView_gridColor, GRID_DEFAULT_COLOR)

        typedArray.recycle()
    }

    private fun initDefault() {
        playerFirstColor = FIRST_PLAYER_DEFAULT_COLOR
        playerSecondColor = SECOND_PLAYER_DEFAULT_COLOR
        gridColor = GRID_DEFAULT_COLOR
    }

    private val listener: OnFieldChangedListener = {

    }

    companion object {
        const val FIRST_PLAYER_DEFAULT_COLOR = Color.BLACK
        const val SECOND_PLAYER_DEFAULT_COLOR = Color.BLACK
        const val GRID_DEFAULT_COLOR = Color.BLACK

        const val DESIRED_CELL_SIZE = 50F
    }
}