package com.example.fartapp.ui.main

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.fartapp.R
import com.example.fartapp.ui.SharedRepository

class MainFragment : Fragment() {

    private lateinit var mainView : View
    private lateinit var viewModel: MainViewModel
    private lateinit var fartLabel :TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = inflater.inflate(R.layout.main_fragment, container, false)
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        val play_button = mainView.findViewById<ImageButton>(R.id.play_fart_sound)
        play_button.setOnClickListener {
            playFartSound(context!!)
        }

        fartLabel = mainView.findViewById(R.id.fart_name);

        val settings = mainView.findViewById<ImageButton>(R.id.bar_settings)
        settings.setOnClickListener{
          view?.findNavController()?.navigate(R.id.settingsFragment)
        }

        setupObservers()
    }


    private fun setupObservers(){
        viewModel.getFartSoundId().observe(this, Observer {
            fartLabel.text = SharedRepository.instance.fartNames.value!![SharedRepository.fartList.indexOf(it)]
        })
    }

    var mediaPlayer : MediaPlayer? = null
    private fun playFartSound(context: Context){
        mediaPlayer?.let {
            if(it.isPlaying){
                it.stop()
            }
        }
        mediaPlayer = MediaPlayer.create(context, viewModel.getFartSoundId().value!!)
        mediaPlayer?.start()
    }


}
