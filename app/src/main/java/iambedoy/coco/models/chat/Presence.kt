package iambedoy.coco.models.chat

/**
 * Coco
 *
 * Created by bedoy on 14/07/20.
 */
sealed class Presence {
    data class EventPresence(
        private val event: String = "",
        private val channel: String = "",
        private val uuid: String = ""
    ) : Presence()

    data class StatusPresence(
        private val name: String = "",
        private val uuid: String = ""
    ) : Presence()
}