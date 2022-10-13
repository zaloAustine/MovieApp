package com.zalo.movieapp.util


import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ErrorHandler {
    fun handleErrors(exception: Exception): String {
        return when (exception) {
            is IOException -> {
                "Unable to connect, check your connection"
            }

            is HttpException -> {
                extractHttpExceptions(exception)
            }

            is UnknownHostException -> {
                "Unable to connect, check your connection"
            }
            is SocketTimeoutException -> {
                "Unable to connect, check your connection"
            }
            else -> exception.message.toString()
        }
    }

    private fun extractHttpExceptions(e: HttpException): String {
        val body = e.response()?.errorBody()
        val jsonString = body?.string()

        val message = try {
            val jsonObject = JSONObject(jsonString)
            jsonObject.getString("message")
        } catch (e: JSONException) {
            "Unable to complete request your request, try again later"
        }
        return message
    }
}