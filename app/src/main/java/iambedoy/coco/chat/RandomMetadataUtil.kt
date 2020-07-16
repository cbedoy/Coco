package iambedoy.coco.chat

import iambedoy.coco.models.chat.MetadataMessage

/**
 * Coco
 *
 * Created by bedoy on 08/07/20.
 */
object RandomMetadataUtil {

    val randomMessages = listOf(
        "Seek success, but always be prepared for random cats.",
        "He was 100% into fasting with her until he understood that meant he couldn't eat.",
        "Pat ordered a ghost pepper pie.",
        "The view from the lighthouse excited even the most seasoned traveler.",
        "Getting up at dawn is for the birds.",
        "There are no heroes in a punk rock band.",
        "All you need to do is pick up the pen and begin.",
        "Rock music approaches at high velocity.",
        "It was her first experience training a rainbow unicorn.",
        "She saw no irony asking me to change but wanting me to accept her for who she is.",
        "One for all and all for one, Muskehounds are always ready.",
        "ne for all and all for one, helping everybody. One for all and all for one, it’s a pretty story. Sharing everything with fun, that’s the way to be. One for all and all for one, Muskehounds are always ready.",
        "If you’ve got a problem chum, think how it could be.",
        "This is my boss, Jonathan Hart, a self-made millionaire, he’s quite a guy. ",
        "I take care of both of them, which ain’t easy, ’cause when they met it was MURDER!",
        "There’s a voice that keeps on calling me.",
        "I’ll want to settle down, Until tomorrow, I’ll just keep moving on. ",
        "Can’t stay for long, just turn around and I’m gone again. Maybe tomorrow",
        "Machine learning and artificial intelligence technology",
        "Sed ut perspiciatis, unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa",
        "blame belongs to those who fail in their duty through weakness of will, which is the same as saying through shrinking from toil",
        "#hastagas"
    )


    private val images = listOf(
        "https://cdn.dribbble.com/users/3884350/screenshots/13005389/media/8d0b300657859eebf0dd8676bf3e4310.jpg",
        "https://cdn.dribbble.com/users/4859/screenshots/12927392/media/d21215d50131aa5b7d2613a7b9c49627.png",
        "https://i1.sndcdn.com/avatars-000715068814-hs77ar-t500x500.jpg",
        "https://cdn2.thelineofbestfit.com/media/2012/04/whitestboyalive.jpg",
        "https://cdn.photographypro.com/wp-content/uploads/2018/01/shoot-panoramic-photos-2@2x-1.jpg",
        "", "", "", "", "", "", "", ""
    )

    private val title = listOf(
        "glad you like it, are people enjoying the icon kit like me? \uD83C\uDF89",
        "Nice work, clean as always!",
        "Clean and fresh work! Love it",
        "", "", "", ""
    )

    private val description = listOf(
        "Great that you enjoying it! yeah, man quite many people like it! every time i'm scrolling dribble feed a can see Kukla kit) and zero negative reviews which is really nice \uD83D\uDE42 so it's pretty good!",
        "Hey Dribbblers \uD83D\uDD25\n" +
                "\n" +
                "Here’s my new shot for “Fitty ” Landing Page.\n" +
                "Press \"L\" on your keyboard for like \uD83D\uDE0D\n" +
                "Don’t forget to add comment and follow me!",
        "", "", "", ""
    )

    private val service = listOf(
        "Spotify", "Youtube", "Github", "", "", "", ""
    )

    fun randomMessage() = MetadataMessage(
        thumbnail = images.shuffled()[0],
        title = title.shuffled()[0],
        description = description.shuffled()[0],
        service = service.shuffled()[0]
    )
}