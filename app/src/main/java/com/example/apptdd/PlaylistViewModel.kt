package com.example.apptdd

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlaylistViewModel :ViewModel() {

    val playlists = MutableLiveData<List<Playlist>>()
}
