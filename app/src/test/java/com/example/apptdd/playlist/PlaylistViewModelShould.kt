package com.example.apptdd.playlist

import com.example.apptdd.utils.BaseUnitTest
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import getValueForTest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import java.lang.RuntimeException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class PlaylistViewModelShould:BaseUnitTest() {

    private val repository: PlaylistRepository = mock()
    private val playlists = mock<List<Playlist>>()
    private val expected = Result.success(playlists)
    private val exception = RuntimeException("Something went wrong")


    @Test
    fun getPlaylistFromRepository() = runBlockingTest{

        val viewModel = mockSuccessfulCase()

        viewModel.playlists.getValueForTest()

        verify(repository, times(1)).getPlaylists()
    }

    @Test
    fun emitsPlaylistFromRepository()= runBlockingTest{

        val viewModel = mockSuccessfulCase()

        assertEquals(expected, viewModel.playlists.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceiveError(){
        runBlocking {
            whenever(repository.getPlaylists()).thenReturn(
                flow{
                    emit(Result.failure<List<Playlist>>(exception))
                }
            )
        }

        val viewModel = PlaylistViewModel(repository)

        assertEquals(exception, viewModel.playlists.getValueForTest()!!.exceptionOrNull())
    }

    private fun mockSuccessfulCase(): PlaylistViewModel {
        runBlocking {

            whenever(repository.getPlaylists()).thenReturn(
                flow {
                    emit(expected)
                }
            )
        }

        val viewModel = PlaylistViewModel(repository)
        return viewModel
    }
}