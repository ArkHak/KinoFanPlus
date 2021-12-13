package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.model.ListRepository
import com.example.kinofanplus.model.ListRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListVM(
    private val repository: ListRepository = ListRepositoryImpl(),
    private val listLiveData: MutableLiveData<AppStateGetMovieList> = MutableLiveData()
) : ViewModel() {

    val liveData: LiveData<AppStateGetMovieList> = listLiveData


    fun getMovieFromServerSource() {
        listLiveData.value = AppStateGetMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            listLiveData.postValue(AppStateGetMovieList.Success(repository.getMovieFromServer()))
        }
    }

    fun getDataFromLocalSource() {
        listLiveData.value = AppStateGetMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            listLiveData.postValue(AppStateGetMovieList.Success(repository.getMovieFromLocalSource()))
        }
    }


}
