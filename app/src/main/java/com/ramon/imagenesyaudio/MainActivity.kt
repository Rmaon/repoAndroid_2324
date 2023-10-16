package com.ramon.imagenesyaudio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ramon.imagenesyaudio.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ib1.setOnClickListener{
            binding.ivCerveza.setImageResource(R.drawable.ic_vino)
        }

        binding.ib2.setOnClickListener{
            binding.ivCerveza.setImageResource(R.drawable.ic_pareja)
        }

    }
}