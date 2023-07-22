package com.example.seminar_3.presentation.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.seminar_3.databinding.ActivityMainBinding
import com.example.seminar_3.presentation.viewmodel.MainActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }
    private val scope = CoroutineScope(Dispatchers.IO)
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                searchJob?.cancel()
                searchJob = scope.launch {
                    val result = viewModel.search(binding.search.text.toString())
                    withContext(Dispatchers.Main) {
                        if (result.isEmpty()) {
                            Toast.makeText(
                                this@MainActivity,
                                "Ничего не найдено",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            binding.result.text = result.toString()
                        }
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    override fun onDestroy() {
        scope.cancel()
        super.onDestroy()
    }
}