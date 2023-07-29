package com.example.github.features.search.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github.features.search.domain.GitInteractor
import com.example.github.features.search.domain.model.Git
import com.example.github.utils.SingleLiveEvent
import com.example.github.utils.ViewState
import com.example.github.utils.asLiveData
import com.example.github.utils.asViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val gitInteractor: GitInteractor
): ViewModel() {

    private val _gitUser = MutableLiveData<ViewState<Git>>()
    val gitUser get() = _gitUser.asLiveData()

    private val _gitRepository = MutableLiveData<ViewState<Git>>()
    val gitRepository get() = _gitRepository.asLiveData()

    private val _loading = MutableLiveData(false)
    val loading get() = _loading.asLiveData()

    val getSavedPosition = SingleLiveEvent<Int>()

    fun getData(name: String){
        getUser(name)
        getRepository(name)
    }

    private fun getUser(name: String){
        _loading.value = true
        viewModelScope.launch {
            _gitUser.value = gitInteractor.getUser(name).asViewState()
            _loading.value = false
        }
    }

    private fun getRepository(name: String){
        viewModelScope.launch {
            _gitRepository.value = gitInteractor.getRepository(name).asViewState()
        }
    }

    fun getSavedInstanceState(
        savedPosition: Int,
    ) {
        getSavedPosition.call(savedPosition)
    }
}