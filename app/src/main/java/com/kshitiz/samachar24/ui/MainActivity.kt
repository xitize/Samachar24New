package com.kshitiz.samachar24.ui

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.kshitiz.samachar24.R
import com.kshitiz.samachar24.databinding.ActivityMainBinding
import com.kshitiz.samachar24.ui.screen.HomeFrag
import com.kshitiz.samachar24.ui.screen.ProfileFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        setContentView(binding.root)
        enableEdgeToEdge()
        binding.navigation.setOnItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.navigation_nepali_feeds -> HomeFrag()
                R.id.navigation_settings -> ProfileFrag()
                else -> HomeFrag() // Default case
            }
            openFragment(selectedFragment)
            true
        }

        // Set the default selected item. This will trigger the listener and load the initial fragment.
        if (savedInstanceState == null) {
            binding.navigation.selectedItemId = R.id.navigation_nepali_feeds
        }
    }


    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container_fragment, fragment)
            .commit()
    }
}
