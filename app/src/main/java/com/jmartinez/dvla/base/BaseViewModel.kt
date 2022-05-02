package com.jmartinez.dvla.base

import androidx.lifecycle.ViewModel
import com.jmartinez.dvla.repository.BaseRepository

abstract class BaseViewModel(
    private val repository : BaseRepository

): ViewModel() {

}