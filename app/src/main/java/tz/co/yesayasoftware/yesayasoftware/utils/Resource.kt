package tz.co.yesayasoftware.yesayasoftware.utils

import okhttp3.ResponseBody

sealed class Resource<T>(
    val data: T? = null,
    val errors: ResponseBody? = null,
    val message: String? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(
        message: String,
        data: T? = null,
        errors: ResponseBody?
    ) : Resource<T>(data, errors, message)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}