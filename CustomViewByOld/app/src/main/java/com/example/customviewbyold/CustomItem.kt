package com.example.customviewbyold

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customviewbyold.databinding.CustomItemBinding

enum class BottomButtonAction {
    BUTTON, TEXT
}

typealias OnBottomButtonsActionListener = (BottomButtonAction) -> Unit
class CustomItem(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
    ): ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: CustomItemBinding

    private var listener: OnBottomButtonsActionListener? = null

    var buttonText: String
        set(value) {
            binding.button.text = value
        }
        get() {
            return binding.button.text.toString()
        }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_item, this, true)
        binding = CustomItemBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
        initializeListener()
    }

    private fun initializeAttributes( attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if(attrs == null) return
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomItem, defStyleAttr, defStyleRes)

        with(binding) {
            val buttonText = typedArray.getString(R.styleable.CustomItem_buttonText)
            val buttonBackgroundColor = typedArray.getColor(R.styleable.CustomItem_buttonBackground, Color.BLACK)
            button.text = buttonText ?: "Тестовый текст"
            button.setBackgroundColor(buttonBackgroundColor)

            val progressMod = typedArray.getBoolean(R.styleable.CustomItem_progressMod, false)
            progress.visibility = if (progressMod) VISIBLE else GONE

            val textViewText = typedArray.getString(R.styleable.CustomItem_textViewText)
            text.text = textViewText ?: "Кнопоччка"
        }

        typedArray.recycle()
    }

    private fun initializeListener() {
        binding.button.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.BUTTON)
        }
        binding.text.setOnClickListener {
            this.listener?.invoke(BottomButtonAction.TEXT)
        }
    }

    fun setListeners(listener: OnBottomButtonsActionListener) {
        this.listener = listener
    }

}