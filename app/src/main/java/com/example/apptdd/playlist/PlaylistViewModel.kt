package com.example.apptdd.playlist

import androidx.lifecycle.*
import javax.inject.Inject

class PlaylistViewModel(
    private val repository: PlaylistRepository
) :ViewModel() {

    val playlists = liveData<Result<List<Playlist>>>{

        emitSource(repository.getPlaylists().asLiveData())
    }

}
