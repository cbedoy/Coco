package iambedoy.coco.explorer

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
import iambedoy.coco.common.CommonTitleItem
import kotlinx.android.synthetic.main.fragment_common.*
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.lifecycleScope


/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ExplorerFragment : Fragment(){

    private val adapter = GroupAdapter<GroupieViewHolder>()

    private val viewModel : ExplorerViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_common, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        common_recycler_view.visibility = View.VISIBLE
        common_recycler_view.layoutManager = LinearLayoutManager(context)
        common_recycler_view.adapter = adapter

        adapter.add(CommonTitleItem("Explorer"))

        viewModel.feeds.observe(viewLifecycleOwner, Observer { feeds ->
            adapter.update(feeds)
        })
    }

    override fun onResume() {
        super.onResume()

        viewModel.loadExplorer()
    }
}