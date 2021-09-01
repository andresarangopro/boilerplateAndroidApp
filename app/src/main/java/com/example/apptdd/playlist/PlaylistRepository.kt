package com.example.apptdd.playlist

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlaylistRepository @Inject constructor(
    private val service:PlaylistService
) {

    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        return service.fetchPlaylists()
    }
}