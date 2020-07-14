package iambedoy.coco.messages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import iambedoy.coco.ChatActivity
import iambedoy.coco.R
import iambedoy.coco.chat.ChatEventItem
import iambedoy.coco.common.CommonTitleItem
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.view_holder_chat_message.*
import kotlinx.android.synthetic.main.view_holder_common_title.*
import org.koin.android.ext.android.inject
import zlc.season.yasha.linear

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
class MessagesFragment : Fragment(){

    private val viewModel: MessagesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userListState.observe(viewLifecycleOwner, Observer { source ->
            if(source.isNotEmpty()){
                common_recycler_view.visibility = View.VISIBLE
                common_recycler_view.linear(
                    MessagesDataSource(title = "Messages", source = source)
                ){
                    renderItem<MessageItem> {
                        res (R.layout.view_holder_chat_message)
                        onBind {
                            chat_message_nickname.text = "${data.user.name.first} ${data.user.name.last}"
                            chat_message_avatar.load(data.user.picture.large){
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
                            chat_message_text.text = data.messageText
                            chat_message_view.setOnClickListener {
                                startActivity(
                                    Intent(context, ChatActivity::class.java).apply {

                                    }
                                )
                            }
                        }
                    }
                    renderItem<CommonTitleItem> {
                        res(R.layout.view_holder_common_title)
                        onBind {
                            common_title_view.text = data.text
                        }
                    }
                    renderItem<ChatEventItem> {
                        res(R.layout.view_holder_event_message)
                        onBind {
                            chat_message_time_ago.text = data.text
                        }
                    }
                }
            }
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadUserList()
    }
}