package com.example.apptdd.playlist

import kotlinx.coroutines.flow.Flow

class PlaylistRepository(
    private val service:PlaylistService
) {
    suspend fun getPlaylists(): Flow<Result<List<Playlist>>> {
        service.fetchPlaylists()
        return service.fetchPlaylists()
    }
}