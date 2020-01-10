package com.example.fartapp.ui.settings

import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import com.example.fartapp.R
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.fartapp.ui.SharedRepository.Companion.fartList


class SettingsFragment : Fragment() {
    private lateinit var mainView : View
    private lateinit var viewModel: SettingsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainView = inflater.inflate(R.layout.settings_fragment, container, false)
        return mainView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        // TODO: Use the ViewModel

        val back_button = mainView.findViewById<AppCompatImageButton>(R.id.bar_back)
        back_button.setOnClickListener{
            view?.findNavController()?.navigateUp()
        }



        setupObservers()
    }

    private fun setupObservers(){
        viewModel.getFartNames().observe(this, Observer {
            val adapter = ArrayAdapter(
                context!!,
                R.layout.dropdown_menu_popup_item,
                it
            )

            val editTextFilledExposedDropdown = mainView.findViewById<AutoCompleteTextView>(R.id.filled_exposed_dropdown)
            editTextFilledExposedDropdown.setAdapter(adapter)

            editTextFilledExposedDropdown.hint = (it[fartList.indexOf(viewModel.getFartSoundId())])

            editTextFilledExposedDropdown.setOnItemClickListener { parent, view, position, id ->
                viewModel.setFartId(position)
                playFartSound(context!!, viewModel.getFartSoundId(position))
            }

        })
    }

    var mediaPlayer : MediaPlayer? = null
    fun playFartSound(context: Context, id: Int){
        mediaPlayer?.let {
            if(it.isPlaying){
                it.stop()
            }
        }
        mediaPlayer = MediaPlayer.create(context, id)
        mediaPlayer?.start()
    }
}
