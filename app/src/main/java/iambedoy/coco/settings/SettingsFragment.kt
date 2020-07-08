package iambedoy.coco.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import iambedoy.coco.R
import iambedoy.coco.common.CommonTitleItem
import kotlinx.android.synthetic.main.fragment_common.*
import kotlinx.android.synthetic.main.view_holder_common_title.*
import zlc.season.yasha.linear

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class SettingsFragment : Fragment(){

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
        common_recycler_view.linear(
            SettingsDataSource(title = "Settings")
        ){
            renderItem<CommonTitleItem> {
                res(R.layout.view_holder_common_title)
                onBind {
                    common_title_view.text = data.text
                }
            }
        }
    }
}