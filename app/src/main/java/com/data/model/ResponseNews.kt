package com.data.model


import com.google.gson.annotations.SerializedName

data class ResponseNews(
    @SerializedName("articles")
    val articles: List<Article>?,
    @SerializedName("status")
    val status: String?, // ok
    @SerializedName("totalResults")
    val totalResults: Int? // 660
) {
    data class Article(
        @SerializedName("author")
        val author: String?, // Boone Ashworth
        @SerializedName("content")
        val content: String?, // Its the unboxing video heard around the metaverse. Theres a fresh leak of Metas upcoming mixed reality headset, the Meta Quest Pro 3. A video posted to X, the social media platform formerly known as … [+3496 chars]
        @SerializedName("description")
        val description: String?, // Plus: Apple changes its mind about right-to-repair legislation, Sony shows off a new portable PlayStation device, and what happens when police go after kids for schoolyard offenses.
        @SerializedName("publishedAt")
        val publishedAt: String?, // 2023-08-26T13:00:00Z
        @SerializedName("source")
        val source: Source?,
        @SerializedName("title")
        val title: String?, // The Leaked Quest 3 Headset Video Teases Meta’s VR Ambitions
        @SerializedName("url")
        val url: String?, // https://www.wired.com/story/meta-quest-3-headset-video-leak/
        @SerializedName("urlToImage")
        val urlToImage: String? // https://media.wired.com/photos/64e901ea3c194283dd3f56a7/191:100/w_1280,c_limit/Meta-Quest-3-Gear-Roundup.jpg
    ) {
        data class Source(
            @SerializedName("id")
            val id: String?, // wired
            @SerializedName("name")
            val name: String? // Wired
        )
    }
}