package play.top20play.testiconapp.data

data class Trail(
    val blog: Blog,
    val post: Post,
    val content_raw: String,
    val content: String,
    val is_current_item: Boolean,
    val is_root_item: Boolean
)
