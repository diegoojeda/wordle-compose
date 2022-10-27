package com.apiumhub.wordle_compose.data

import android.content.Context
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class DictionaryProvider(context: Context) {
    val dictionary = readJsonArray(context).toList()

    private fun readJsonArray(context: Context) = JSONArray(
        BufferedReader(
            InputStreamReader(
                context.assets.open("dictionary-es.json")
            )
        ).readText()
    )

    private fun JSONArray.toList(): List<String> {
        val mutableList = mutableListOf<String>()
        for (index: Int in 0 until this.length()) {
            mutableList.add(this.getString(index))
        }
        return mutableList
    }
}