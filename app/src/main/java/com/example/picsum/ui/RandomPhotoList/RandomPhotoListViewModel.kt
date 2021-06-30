package com.example.picsum.ui.RandomPhotoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.picsum.data.model.Image
import com.example.picsum.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomPhotoListViewModel @Inject constructor(private val repository: ImageRepository):ViewModel() {



    val flowRemoteData = repository.getRemoteData()

    fun insertImage(image: Image) = viewModelScope.launch {
        repository.insertImage(image)
    }
}