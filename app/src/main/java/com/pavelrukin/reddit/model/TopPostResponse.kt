package com.pavelrukin.reddit.model


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TopPostResponse(
    @SerializedName("kind")
    var kind: String, // Listing
    @SerializedName("data")
    var dataTop: DataTop
) {
    data class DataTop(
        @SerializedName("modhash")
        var modhash: String,
        @SerializedName("dist")
        var dist: Int, // 2
        @SerializedName("children")
        var children: List<Children>,
        @SerializedName("after")
        var after: String, // t3_jrqtae
        @SerializedName("before")
        var before: String? // null
    ) {
        data class Children(
            @SerializedName("kind")
            var kind: String, // t3
            @SerializedName("data")
            var dataTopItem: DataTopItem
        ) {
            @Parcelize
            @Entity(tableName = "redditPosts")
            data class DataTopItem(
                @PrimaryKey
                @SerializedName("id")
                val key: String,
                @SerializedName("thumbnail")
                var thumbnail: String, // https://b.thumbs.redditmedia.com/JEffkfaUD-RDaeoqwexWXZUXIW3LnY8ESJjZ-kUX6vU.jpg
                @SerializedName("created")
                var created: Double, // 1605066965.0
                @SerializedName("author")
                var author: String, // ImInSpainButWithNo-S
                @SerializedName("num_comments")
                var numComments: Int, // 2177
                @SerializedName("url")
                var url: String, // 2177
            ) : Parcelable
        }
    }
}

@Entity(tableName = "redditKeys")
data class RedditKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
)
