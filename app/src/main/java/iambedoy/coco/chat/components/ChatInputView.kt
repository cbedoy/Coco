package iambedoy.coco.chat.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.core.widget.addTextChangedListener
import iambedoy.coco.R
import iambedoy.coco.chat.components.ChatInputView.Action.*
import iambedoy.coco.visibleIfTrueOtherwiseGone
import kotlinx.android.synthetic.main.view_chat_input.view.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */

typealias ActionListener = (ChatInputView.Action) -> Unit

class ChatInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    sealed class Action {
        data class SendAction(val text: String) : Action()
        data class EmojiAction(val show: Boolean) : Action()
        object CameraAction : Action()
        object AttachAction : Action()
    }

    init {
        LayoutInflater.from(context).inflate(R.layout.view_chat_input, this, true)

        chat_input_field.addTextChangedListener { text ->
            chat_input_camera.visibleIfTrueOtherwiseGone(text?.length == 0)
            chat_input_attach.visibleIfTrueOtherwiseGone(text?.length == 0)
        }
        chat_input_send.setOnClickListener {
            if(chat_input_field.text.isNotEmpty()){
                listener?.invoke(SendAction(chat_input_field.text.toString()))
                chat_input_field.setText("")
            }
        }
        chat_input_attach.setOnClickListener {
            listener?.invoke(AttachAction)
        }
        chat_input_camera.setOnClickListener {
            listener?.invoke(CameraAction)
        }
        chat_input_emoticon.setOnClickListener {
            listener?.invoke(EmojiAction(show = true))
        }
    }

    var listener : ActionListener? = null

}