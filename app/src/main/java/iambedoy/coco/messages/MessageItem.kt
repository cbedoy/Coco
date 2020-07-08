package iambedoy.coco.messages

import iambedoy.coco.models.RandomUserResultResponse
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class MessageItem (val user: RandomUserResultResponse, val messageText: String): YashaItem