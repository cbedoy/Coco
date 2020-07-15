package iambedoy.coco.messages

import coil.api.load
import coil.transform.CircleCropTransformation
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import iambedoy.coco.ChatActivity
import iambedoy.coco.R
import iambedoy.coco.models.RandomUserResultResponse
import iambedoy.coco.startActivityWhenOnClickListener
import kotlinx.android.synthetic.main.view_holder_chat_message.*


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class MessageItem (val user: RandomUserResultResponse, val messageText: String): Item(){
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.chat_message_nickname.text = "${user.name.first} ${user.name.last}"
        viewHolder.chat_message_avatar.load(user.picture.large){
            crossfade(true)
            transformations(CircleCropTransformation())
        }
        viewHolder.chat_message_text.text = messageText
        viewHolder.chat_message_view.startActivityWhenOnClickListener(ChatActivity::class.java)
    }

    override fun getLayout(): Int {
        return R.layout.view_holder_chat_message
    }
}
