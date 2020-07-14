package iambedoy.coco.chat

import iambedoy.coco.models.Message
import zlc.season.yasha.YashaItem

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
class ChatInMessageItem(val message: Message) : YashaItem

class ChatOutMessageItem(val message: Message) : YashaItem

class ChatEventItem(val text: String) : YashaItem

class ChatDateItem(val text: String) : YashaItem