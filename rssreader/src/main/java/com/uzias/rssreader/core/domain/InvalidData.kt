package com.uzias.rssreader.core.domain

import java.util.*


enum class InvalidData {

    UNINITIALIZED,
    INVALID;

    fun getString() = ARG_INVALID_STRING
    fun getDouble() = ARG_INVALID_DOUBLE
    fun getLong() = ARG_INVALID_LONG
    fun getInt() = ARG_INVALID_INT
    fun getFloat() = ARG_INVALID_FLOAT
    fun getDate() = ARG_iNVALID_DATE

    companion object {
        private val ARG_INVALID_STRING = ""
        private val ARG_INVALID_DOUBLE = -33.toDouble()
        private val ARG_INVALID_LONG = -33L
        private val ARG_INVALID_FLOAT = -33F
        private val ARG_INVALID_INT = -33
        private val ARG_iNVALID_DATE = Date()

        fun isInvalid(data: Any): Boolean {
            when (data) {
                is String -> return data == ARG_INVALID_STRING
                is Double -> return data == ARG_INVALID_DOUBLE
                is Long -> return data == ARG_INVALID_LONG
                is Float -> return data == ARG_INVALID_FLOAT
                is Int -> return data == ARG_INVALID_INT
                is Date -> return data == ARG_iNVALID_DATE
                else -> return false
            }
        }
    }

}