package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.model.Repository
import com.example.kinofanplus.model.RepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListVM(
    private val liveDataToObserve: MutableLiveData<AppStateGetMovieList> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {

    val liveData: LiveData<AppStateGetMovieList> = liveDataToObserve

    //    fun getMovieFromLocalSource() = getDataFromLocalSource()
    fun getMovieFromServer() = getMovieFromServerSource()

    private fun getDataFromLocalSource() {
        liveDataToObserve.value = AppStateGetMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            liveDataToObserve.postValue(AppStateGetMovieList.Success(repository.getMovieFromServer()))
        }
    }

    private fun getMovieFromServerSource() {
        liveDataToObserve.value = AppStateGetMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            liveDataToObserve.postValue(AppStateGetMovieList.Success(repository.getMovieFromServer()))
        }
    }
}