package com.example.kinofanplus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kinofanplus.model.ListRepository
import com.example.kinofanplus.model.ListRepositoryImpl
import com.example.kinofanplus.model.LocalRepository
import com.example.kinofanplus.model.LocalRepositoryImpl
import com.example.kinofanplus.view.App
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilmListVM(
    private val repository: ListRepository = ListRepositoryImpl(),
    private val localRepository: LocalRepository = LocalRepositoryImpl(App.getMyMovieDao()),
    private val listLiveData: MutableLiveData<AppStateGetMovieList> = MutableLiveData(),
    private val listLiveDataFavorite: MutableLiveData<AppStateGetLikesMovieList> = MutableLiveData()
) : ViewModel() {

    val liveData: LiveData<AppStateGetMovieList> = listLiveData
    val liveFavoriteData: LiveData<AppStateGetLikesMovieList> = listLiveDataFavorite


    fun getMovieFromServerSource() {
        listLiveData.value = AppStateGetMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            listLiveData.postValue(AppStateGetMovieList.Success(repository.getMovieFromServer()))
        }
    }

    fun getDataFromLocalSource() {
        listLiveDataFavorite.value = AppStateGetLikesMovieList.Loading

        CoroutineScope(Dispatchers.IO).launch {
            listLiveDataFavorite.postValue(AppStateGetLikesMovieList.Success(localRepository.allFavoritesMovieBD()))
        }
    }


}
