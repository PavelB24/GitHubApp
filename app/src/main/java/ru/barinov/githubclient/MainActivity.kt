package ru.barinov.githubclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.barinov.githubclient.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportFragmentManager.beginTransaction().replace(binding.mainContainerForFragments.id, HomeFragment()).commit()
    }

}