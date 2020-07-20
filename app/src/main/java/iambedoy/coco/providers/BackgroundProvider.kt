package iambedoy.coco.providers

import iambedoy.coco.R
import iambedoy.coco.models.chat.User

/**
 * Coco
 *
 * Created by bedoy on 20/07/20.
 */
class BackgroundProvider {
    private val colors = listOf(
        R.color.Cyan,
        R.color.Comment,
        R.color.Green,
        R.color.Orange,
        R.color.Pink,
        R.color.Purple,
        R.color.Red,
        R.color.Yellow
    )

    fun cardColor(user: User): Int {
        return colors[user.nickname.length % colors.size]
    }
}