package com.example.apptdd.playlist

import kotlinx.coroutines.flow.Flow

interface PlaylistAPI{
    suspend fun fetchAllPlaylists():List<Playlist> {
        TODO("")
    }

}
