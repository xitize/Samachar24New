package com.kshitiz.samachar24.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.kshitiz.samachar24.R
import com.kshitiz.samachar24.databinding.ActivityMainBinding
import com.kshitiz.samachar24.ui.screen.HomeFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.mainContainer) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(0, insets.top, 0, 0)
            windowInsets
        }
        val navController =
            (supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment).navController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.setOnItemReselectedListener { item ->
            if (item.itemId == R.id.nav_feeds) {
                val homeFragment =
                    navHostFragment.childFragmentManager.fragments.firstOrNull() as? HomeFrag
                homeFragment?.binding?.recyclerView?.scrollToPosition(0)
            }
        }
    }
}
