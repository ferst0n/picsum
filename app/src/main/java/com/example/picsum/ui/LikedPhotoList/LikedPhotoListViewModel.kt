package com.example.picsum.ui.LikedPhotoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.picsum.data.model.Image
import com.example.picsum.data.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LikedPhotoListViewModel @Inject constructor(private val repository: ImageRepository):
    ViewModel() {


    val flowLocalData = repository.getLocalData()

    fun deleteImage(image: Image) = viewModelScope.launch {
        repository.deleteImage(image)
    }

}