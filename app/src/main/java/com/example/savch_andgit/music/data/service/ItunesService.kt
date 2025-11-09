package com.example.savch_andgit.music.data.service

import com.example.savch_andgit.music.data.dto.ItunesSearchResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request

class ItunesService(
    private val client: OkHttpClient = OkHttpClient(),
    private val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
) {
    private val adapter = moshi.adapter(ItunesSearchResponse::class.java)

    suspend fun searchTracks(query: String): ItunesSearchResponse {
        val url: HttpUrl = HttpUrl.Builder()
            .scheme("https")
            .host("itunes.apple.com")
            .addPathSegment("search")
            .addQueryParameter("media", "music")
            .addQueryParameter("entity", "song")
            .addQueryParameter("term", query)
            .build()
        val req = Request.Builder().url(url).get().build()
        client.newCall(req).execute().use { resp ->
            if (!resp.isSuccessful) throw IllegalStateException("http_${'$'}{resp.code}")
            val body = resp.body?.string() ?: throw IllegalStateException("empty_body")
            return adapter.fromJson(body) ?: ItunesSearchResponse(0, emptyList())
        }
    }
}
