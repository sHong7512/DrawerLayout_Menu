package com.shong.drawerlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.shong.drawerlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainItem.leftMenuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(binding.leftMenuItem.leftMenuLayout)
        }
        binding.leftMenuItem.leftMenuBackLayout.setOnClickListener {
            binding.drawerLayout.closeDrawer(binding.leftMenuItem.leftMenuLayout)
        }
        binding.mainItem.rightMenuButton.setOnClickListener {
            binding.drawerLayout.openDrawer(binding.rightMenuItem.rightMenuLayout)
        }
        binding.rightMenuItem.rightMenuBackLayout.setOnClickListener {
            binding.drawerLayout.closeDrawer(binding.rightMenuItem.rightMenuLayout)
        }

        binding.drawerLayout.addDrawerListener(object : DrawerContent(this) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                when (drawerView) {
                    binding.rightMenuItem.rightMenuLayout -> {
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.leftMenuItem.leftMenuLayout)
                    }
                    binding.leftMenuItem.leftMenuLayout ->{
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, binding.rightMenuItem.rightMenuLayout)
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                when (drawerView) {
                    binding.rightMenuItem.rightMenuLayout -> {
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, binding.leftMenuItem.leftMenuLayout)
                    }
                    binding.leftMenuItem.leftMenuLayout ->{
                        binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, binding.rightMenuItem.rightMenuLayout)
                    }
                }
            }

        })

    }
}