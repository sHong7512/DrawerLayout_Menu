package com.shong.drawerlayout

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)

        val sideMenuLayout = findViewById<DrawerContent>(R.id.sideMenuLayout)
        val sideMenuImageButton = findViewById<ImageButton>(R.id.sideMenu)
        val sideMenuBackLayout = findViewById<LinearLayout>(R.id.sideMenuBackLayout)

        val searchMenuLayout = findViewById<DrawerContent>(R.id.searchMenuLayout)
        val searchMenuImageButton = findViewById<ImageButton>(R.id.searchMenu)
        val searchMenuBackLayout = findViewById<LinearLayout>(R.id.searchMenuBackLayout)

        sideMenuImageButton.setOnClickListener {
            drawerLayout.openDrawer(sideMenuLayout)
        }
        sideMenuBackLayout.setOnClickListener {
            drawerLayout.closeDrawer(sideMenuLayout)
        }
        searchMenuImageButton.setOnClickListener {
            drawerLayout.openDrawer(searchMenuLayout)
        }
        searchMenuBackLayout.setOnClickListener {
            drawerLayout.closeDrawer(searchMenuLayout)
        }

        drawerLayout.addDrawerListener(object : DrawerContent(this) {
            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                when (drawerView) {
                    searchMenuLayout -> {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, sideMenuLayout)
                    }
                    sideMenuLayout ->{
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, searchMenuLayout)
                    }
                }
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                when (drawerView) {
                    searchMenuLayout -> {
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, sideMenuLayout)
                    }
                    sideMenuLayout ->{
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED, searchMenuLayout)
                    }
                }
            }

        })

    }
}