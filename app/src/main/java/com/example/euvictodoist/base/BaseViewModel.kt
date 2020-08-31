package com.example.euvictodoist.base

import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val _loading = ObservableInt(View.GONE)


}