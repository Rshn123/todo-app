package com.example.loginpage.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.loginpage.DataViewModel
import com.example.loginpage.Main2Activity
import com.example.loginpage.R
import com.example.loginpage.fragment.important.ImportantFragment
import com.example.loginpage.fragment.myday.AddToDayFragmnt
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var dataViewModel: DataViewModel
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.main_activity_drawer_layout)

        val navigationView: NavigationView = findViewById(R.id.navigation)
        navigationView.setNavigationItemSelectedListener(this)
        loadFragment(Main2Activity())
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_item_one -> {
                loadFragment(AddToDayFragmnt())
            }
            R.id.home -> {
                loadFragment(Main2Activity())
            }

            R.id.nav_item_two ->{
                loadFragment(ImportantFragment())
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun loadFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

    fun updateImportant(id: String) {
        dataViewModel = DataViewModel(this)
        val trueOrFalse = !dataViewModel.getTableRow(id).isImportant
        dataViewModel.updateImportant(id, trueOrFalse)
    }
}
