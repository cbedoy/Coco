package iambedoy.coco.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iambedoy.coco.R
import iambedoy.coco.chat.components.ChatContentView
import iambedoy.coco.chat.components.ChatContentView.THEME.Black
import iambedoy.coco.chat.components.ChatContentView.THEME.White
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.view_holder_in_plain_message.*
import kotlinx.android.synthetic.main.view_holder_in_plain_message.chat_content_view
import kotlinx.android.synthetic.main.view_holder_in_plain_message.plain_message_text
import kotlinx.android.synthetic.main.view_holder_out_plain_message.*
import zlc.season.yasha.linear

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chat_recycler_view.linear(ChatDataSource(
            randomMessages = RandomMetadataUtil.randomMessages
        )) {
            renderItem<ChatInMessageItem> {
                res(R.layout.view_holder_in_plain_message)
                onBind {
                    plain_message_text.text = data.message.text
                    chat_content_view.theme = Black
                    chat_content_view.metadata = data.message.metadata
                }
            }

            renderItem<ChatOutMessageItem> {
                res(R.layout.view_holder_out_plain_message)
                onBind {
                    plain_message_text.text = data.message.text
                    chat_content_view.theme = White
                    chat_content_view.metadata = data.message.metadata
                }
            }
        }
    }
}