package com.example.apptdd.playlist

import com.example.apptdd.R

data class Playlist(
    val id:String,
    val name:String,
    val category:String,
    val image:Int= R.mipmap.playlist
) {
}