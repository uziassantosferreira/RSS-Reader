package com.uzias.rssreader.core.util

fun fileFromResource(resource: String, javaClazz: Class<Any>) : String =
        javaClazz.classLoader.getResourceAsStream(resource).bufferedReader().use { it.readText() }

