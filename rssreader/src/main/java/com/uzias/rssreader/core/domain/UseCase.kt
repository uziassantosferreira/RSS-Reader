package com.uzias.rssreader.core.domain

interface UseCase<T> {
    fun run(): T
}