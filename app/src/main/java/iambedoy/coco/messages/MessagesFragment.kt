package iambedoy.coco.messages

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
import iambedoy.coco.chat.RandomMetadataUtil.randomMessages
import iambedoy.coco.common.CommonTitleItem
import kotlinx.android.synthetic.main.fragment_common.*
import org.koin.android.ext.android.inject

/**
 * Coco
 *
 * Created by bedoy on 07/07/20.
 */
class MessagesFragment : Fragment(){

    private val viewModel: MessagesViewModel by inject()
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        common_recycler_view.layoutManager = LinearLayoutManager(context)
        common_recycler_view.adapter = adapter
        common_recycler_view.visibility = View.VISIBLE

        viewModel.channels.observe(viewLifecycleOwner, Observer { channels ->
            adapter.update(channels)
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadChannels()
    }
}