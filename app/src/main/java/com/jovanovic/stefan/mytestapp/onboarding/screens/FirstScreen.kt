package com.jovanovic.stefan.mytestapp.onboarding.screens

import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.viewpager2.widget.ViewPager2
import com.jovanovic.stefan.mytestapp.R
import kotlinx.android.synthetic.main.fragment_first_screen.view.*
import java.io.IOException

class FirstScreen : Fragment() {
    private lateinit var btnPlay : Button
    private lateinit var btnPause : Button
    var mediaPlayer : MediaPlayer?=null;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_first_screen, container, false)

        val viewPager =  activity?.findViewById<ViewPager2>(R.id.viewPager)
        btnPlay = view.findViewById(R.id.btnPlay)
        btnPause = view.findViewById(R.id.btnPause)

        view.next2.setOnClickListener {
            viewPager?.currentItem = 2
        }

        btnPlay.setOnClickListener {
            playAudio()
        }

        btnPause.setOnClickListener{
            pauseAudio()
        }


        return view
    }


    private fun playAudio() {
//        val audioUrl="https://www.bensound.com/royalty-free-music/track/paper-plane-guitar-romantic"
        mediaPlayer = MediaPlayer()
        mediaPlayer!!.setAudioStreamType(AudioManager.STREAM_MUSIC)
        try {
            val uri = Uri.parse("android.resource://com.jovanovic.stefan.mytestapp/" + R.raw.myaudio)
            mediaPlayer!!.setDataSource(requireContext().applicationContext,uri)
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
    private fun pauseAudio() {
        if(mediaPlayer!!.isPlaying){
            mediaPlayer!!.stop()
            mediaPlayer!!.reset()
            mediaPlayer!!.release()
        }else{
            Toast.makeText(requireActivity(), "Audio has not played", Toast.LENGTH_SHORT).show()
        }
    }


}