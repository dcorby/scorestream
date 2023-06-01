package com.example.scorestream

import org.json.JSONArray
import org.json.JSONObject

class Json {
    companion object {
        // https://stackoverflow.com/questions/44870961/how-to-map-a-json-string-to-kotlin-map
        private fun JSONObject.toMap(): Map<String, *> = keys().asSequence().associateWith {
            when (val value = this[it])
            {
                is JSONArray ->
                {
                    val map = (0 until value.length()).associate { Pair(it.toString(), value[it]) }
                    JSONObject(map).toMap().values.toList()
                }
                is JSONObject -> value.toMap()
                JSONObject.NULL -> null
                else            -> value
            }
        }

        fun parse(string: String): Map<String, *> {
            val jsonObject = JSONObject(string)
            return jsonObject.toMap()
        }
    }
}