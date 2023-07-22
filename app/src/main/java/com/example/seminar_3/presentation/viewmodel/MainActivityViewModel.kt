package com.example.seminar_3.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.seminar_3.data.WordsRepositoryImpl
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivityViewModel : ViewModel() {
    private val repository = WordsRepositoryImpl()

    suspend fun search(query: String) = suspendCoroutine {
        thread {
            val result = repository.getAllWords()
                .filter { word -> word.contains(query) }
            it.resume(result)
        }
    }
}