package tz.co.yesayasoftware.yesayasoftware.exceptions

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Inject

class ValidationError
    @Inject constructor(
    private val retrofit: Retrofit
){
    fun convertErrors(response: ResponseBody): ApiError? {
        val converter : Converter<ResponseBody, ApiError> =
            retrofit.responseBodyConverter(ApiError::class.java, arrayOfNulls<Annotation>(0))

        var apiError: ApiError? = null

        try {
            apiError = converter.convert(response)
        } catch (e: ApiErrorException) {
            e.printStackTrace()
        }

        return apiError
    }
}