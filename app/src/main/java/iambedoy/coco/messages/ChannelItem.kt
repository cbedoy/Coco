package iambedoy.coco.messages

import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.ChatActivity
import iambedoy.coco.R
import iambedoy.coco.models.channel.Channel
import kotlinx.android.synthetic.main.view_holder_chat_message.*

/**
 * Coco
 *
 * Created by bedoy on 21/07/20.
 */
class ChannelItem (private val channel: Channel) : Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.chat_message_nickname.text = channel.name
        viewHolder.chat_message_avatar.load(channel.avatar){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        viewHolder.chat_message_text.text = channel.lastMessage.text
        viewHolder.itemView.setOnClickListener {
            it.context?.let { context ->
                context.startActivity(Intent(context, ChatActivity::class.java).apply {
                    putExtra("channel", channel)
                })
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_chat_message
    }

}