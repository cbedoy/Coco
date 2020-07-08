package iambedoy.coco.chat.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import iambedoy.coco.R
import iambedoy.coco.visibleIfTrueOtherwiseGone
import kotlinx.android.synthetic.main.view_chat_input.view.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){
    init {
        LayoutInflater.from(context).inflate(R.layout.view_chat_input, this, true)

        chat_input_field.addTextChangedListener { text ->
            chat_input_camera.visibleIfTrueOtherwiseGone(text?.length?:0> 0)
        }
    }
}