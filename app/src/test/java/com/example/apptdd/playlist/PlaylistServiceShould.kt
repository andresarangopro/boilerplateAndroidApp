package com.example.apptdd.playlist

import com.example.apptdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import java.lang.RuntimeException

class PlaylistServiceShould:BaseUnitTest() {

    private val playlistAPI:PlaylistAPI = mock()
    private lateinit var service:PlaylistService
    private val playlist = mock<List<Playlist>>()
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun fetchPlaylistFromAPI() = runBlockingTest {

        service = PlaylistService(playlistAPI)

        service.fetchPlaylists().first()

        verify(playlistAPI, times(1)).fetchAllPlaylists()
    }

    @Test
    fun convertValuesToFlowResultAndEmitsThem()= runBlockingTest{
        mockSuccessfulCase()

        assertEquals(Result.success(playlist), service.fetchPlaylists().first())
    }

    private suspend fun mockSuccessfulCase() {
        whenever(playlistAPI.fetchAllPlaylists()).thenReturn(playlist)

        service = PlaylistService(playlistAPI)
    }

    @Test
    fun emitErrorResultWhenNetworkFails()= runBlockingTest {
        mockFailureCase()

        assertEquals("Something went wrong",
            service.fetchPlaylists().first().exceptionOrNull()?.message)
    }

    private suspend fun mockFailureCase() {
        whenever(playlistAPI.fetchAllPlaylists()).thenThrow(RuntimeException("Damn backend developer"))

        service = PlaylistService(playlistAPI)
    }

}