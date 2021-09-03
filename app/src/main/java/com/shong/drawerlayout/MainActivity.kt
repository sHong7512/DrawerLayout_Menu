package com.shong.drawerlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<ImageButton>(R.id.sideMenu).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(findViewById(R.id.sideMenuLayout))
        }
        findViewById<LinearLayout>(R.id.sideMenuBackLayout).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(findViewById(R.id.sideMenuLayout))
        }
        findViewById<ImageButton>(R.id.searchMenu).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).openDrawer(findViewById(R.id.searchMenuLayout))
        }
        findViewById<LinearLayout>(R.id.searchMenuBackLayout).setOnClickListener {
            findViewById<DrawerLayout>(R.id.drawerLayout).closeDrawer(findViewById(R.id.searchMenuLayout))
        }

    }
}