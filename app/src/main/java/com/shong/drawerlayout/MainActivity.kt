package com.shong.drawerlayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.snackbar.Snackbar
import com.shong.drawerlayout.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onBackPressed() {
        when {
            binding.drawerLayout.isDrawerOpen(binding.leftMenuItem.leftMenuLayout) -> binding.drawerLayout.closeDrawer(binding.leftMenuItem.leftMenuLayout)
            binding.drawerLayout.isDrawerOpen(binding.rightMenuItem.rightMenuLayout) -> binding.drawerLayout.closeDrawer(binding.rightMenuItem.rightMenuLayout)
            else -> backDoubleCount()
        }
    }

    private var backDoubleCounting = 0
    private fun backDoubleCount(){
        backDoubleCounting++
        CoroutineScope(Dispatchers.Main).launch {
            delay(3000L)
            backDoubleCounting = 0
        }

        if(backDoubleCounting > 1){
            super.onBackPressed()
        }else{
            Snackbar.make(binding.root,"[알림] '뒤로'버튼을 한번더 누르면 종료됩니다.", Snackbar.LENGTH_SHORT).show()
        }
    }
}