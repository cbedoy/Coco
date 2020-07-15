package iambedoy.coco.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import iambedoy.coco.R
import kotlinx.android.synthetic.main.fragment_chat.*
import org.koin.android.ext.android.inject


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatFragment : Fragment(){

    private val viewModel : ChatViewModel by inject()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chat_recycler_view.setHasFixedSize(true)
        chat_recycler_view.layoutManager = LinearLayoutManager(context)
        chat_recycler_view.adapter = adapter

        viewModel.messages.observe(viewLifecycleOwner, Observer { messages ->
            adapter.addAll(messages)
        })
    }
}