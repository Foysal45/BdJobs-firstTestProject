package com.example.testapplication.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.testapplication.R
import com.example.testapplication.databinding.ActivityMainBinding

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

  //  private lateinit var appBarConfiguration: AppBarConfiguration

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     /*   val actionbar=supportActionBar
        //actionbar?.hide()
        actionbar?. setHomeAsUpIndicator(null)
        actionbar?. setDisplayShowHomeEnabled(false)// remove the icon
        actionbar?. setHomeButtonEnabled(false)// disable the button
        actionbar?.setDisplayHomeAsUpEnabled(true) // remove the left caret*/

        navController = findNavController(R.id.navHostFragment)
        navController = findNavController(R.id.navHostFragment)
        /*appBarConfiguration = AppBarConfiguration(setOf(R.id.navHostFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)*/
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return navController.navigateUp()
       // return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        when {
            navController.currentDestination?.id != navController.graph.startDestination -> {
                super.onBackPressed()
            }
            else -> {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed()
                    return
                }
                doubleBackToExitPressedOnce = true
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()
                Handler(Looper.getMainLooper()).postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000L)
            }
        }
    }

    override fun onNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}