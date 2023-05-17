package com.example.customviewbyold

import android.content.Context
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customviewbyold.databinding.CustomItemBinding


typealias OnBottomButtonsActionListener = () -> Unit

class CustomItem(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int,
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes), CustomItemListeners {

    private val binding: CustomItemBinding

    var buttonText: String?
        set(value) {
            binding.button.text = value ?: "null"
        }
        get() {
            return binding.button.text.toString()
        }

    var textViewText: String? = null
        set(value) {
            field = value ?: "Default"
            binding.text.text = field
        }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context,
        attrs,
        defStyleAttr,
        R.style.CustomViewStyleDefaultRes
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.attr.defaultStyleAttr
    )

    constructor(context: Context) : this(context, null)

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_item, this, true)
        binding = CustomItemBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomItem, defStyleAttr, defStyleRes)

        with(binding) {
            val buttonBackgroundColor =
                typedArray.getColor(R.styleable.CustomItem_buttonBackground, Color.BLACK)
            buttonText = typedArray.getString(R.styleable.CustomItem_buttonText)
            button.setBackgroundColor(buttonBackgroundColor)

            val progressMod = typedArray.getBoolean(R.styleable.CustomItem_progressMod, false)
            progress.visibility = if (progressMod) VISIBLE else GONE

            textViewText = typedArray.getString(R.styleable.CustomItem_textViewText)
        }

        typedArray.recycle()
    }

    override fun setButtonListener(listener: OnBottomButtonsActionListener) {
        binding.button.setOnClickListener { listener.invoke() }
    }

    override fun setTextViewListener(listener: OnBottomButtonsActionListener) {
        binding.text.setOnClickListener { listener.invoke() }
    }


    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()!!
        val savedState = SavedState(superState)
        savedState.buttonText = buttonText
        savedState.textViewText = textViewText

        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(state.superState)
        buttonText = savedState.buttonText
        textViewText = savedState.textViewText
    }

    private class SavedState : BaseSavedState {

        var buttonText: String? = null
        var textViewText: String? = null

        constructor(superState: Parcelable) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            buttonText = parcel.readString()
            textViewText = parcel.readString()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(buttonText)
            out.writeString(textViewText)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(p0: Parcel): SavedState {
                    return SavedState(p0)
                }

                override fun newArray(p0: Int): Array<SavedState?> {
                    return Array(p0) { null }
                }

            }
        }
    }

}