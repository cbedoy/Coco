package iambedoy.coco.chat.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import coil.api.load
import iambedoy.coco.R
import iambedoy.coco.models.chat.MetadataMessage
import iambedoy.coco.reloadTextIfEmptyThenGone
import iambedoy.coco.visibleIfTrueOtherwiseGone
import kotlinx.android.synthetic.main.view_media_content.view.*

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){
    init {
        LayoutInflater.from(context).inflate(R.layout.view_media_content, this, true)
    }

    var incoming: Boolean = false
        set(value) {
            field = value

            if(field){
                view_media_content.setBackgroundResource(0)
            }else{
                view_media_content.setBackgroundResource(R.color.CurrentLine)
            }
        }

    var metadata: MetadataMessage =
        MetadataMessage()
        set(value) {
            field = value

            view_media_content_thumbnail.visibleIfTrueOtherwiseGone(field.thumbnail.isNotEmpty())

            view_media_content_thumbnail.load(field.thumbnail){
                crossfade(true)
            }

            view_media_content_title.reloadTextIfEmptyThenGone(field.title)
            view_media_content_description.reloadTextIfEmptyThenGone(field.description)
            view_media_content_service.reloadTextIfEmptyThenGone(field.service)
        }

}