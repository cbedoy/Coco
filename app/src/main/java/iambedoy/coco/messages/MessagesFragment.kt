package iambedoy.coco.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import coil.api.load
import coil.transform.CircleCropTransformation
import iambedoy.coco.R
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.view_holder_chat_message.*
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
                    MessagesDataSource(source)
                ){
                    renderItem<MessageItem> {
                        res (R.layout.view_holder_chat_message)
                        onBind {
                            chat_message_nickname.text = "${data.response.name.first} ${data.response.name.last}"
                            chat_message_avatar.load(data.response.picture.medium){
                                crossfade(true)
                                transformations(CircleCropTransformation())
                            }
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