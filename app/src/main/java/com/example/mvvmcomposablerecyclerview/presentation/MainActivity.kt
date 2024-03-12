package com.example.mvvmcomposablerecyclerview.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmcomposablerecyclerview.BaseApplication
import com.example.mvvmcomposablerecyclerview.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var app: BaseApplication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}