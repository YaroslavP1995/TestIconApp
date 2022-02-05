package play.top20play.testiconapp.data

data class Blog(
    val name: String,
    val active: Boolean,
    val theme: Theme,
    val url: String,
    val share_likes: Boolean,
    val share_following: Boolean,
    val can_be_followed: Boolean
)
