package com.rajnish.mobileapps.lcoworkoutapp

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.GONE
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        setTransparentStatusBar()
        setupNavigation()
        setupViewModel()

        setNotificationUpdate()
    }

    private fun setNotificationUpdate() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
        }
        if(calendar.before(Calendar.getInstance())){
            calendar.add(Calendar.DATE, 1)
        }

        val intent = Intent(this, DailyBoradcastReciever::class.java)
        val pendingIntent = PendingIntent.getBroadcast(this,
            0, intent, 0)

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,pendingIntent
        )
    }

    private fun setupViewModel() {
        val mViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        mViewModel?.toobarTitleObserver?.observe(this, Observer {
            textViewToolbarTitle.text = it
        })

    }


    private fun setupNavigation() {
        val appBarConfig = AppBarConfiguration(
            setOf(R.id.dashboardFragment),
            drawer_layout
        )
        val navController = findNavController(R.id.nav_host_fragment_container)
        toolbar.overflowIcon = getDrawable(R.drawable.ic_menu)
        toolbar.navigationIcon?.setTint(Color.parseColor("#130e51"))
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfig)
        NavigationUI.setupWithNavController(navigation_view, navController)
        navigation_view.setNavigationItemSelectedListener(this)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    toolbar.visibility = GONE
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
                }
                R.id.dashboardFragment -> {
                    drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)

                    toolbar.visibility = View.VISIBLE
                    toolbar.setNavigationIcon(R.drawable.ic_menu)
                    textViewToolbarTitle.setText("LCO Gym")
                }
            }
        }
    }

    override fun onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        super.onBackPressed()
    }

    private fun setTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            statusBarColor = Color.TRANSPARENT        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                drawer_layout.openDrawer(GravityCompat.START)
            R.id.aboutApp -> {
                drawer_layout.closeDrawer(GravityCompat.START)
                showAboutDialog()
            }

        }
        return item.onNavDestinationSelected(findNavController(R.id.nav_host_fragment_container)) || super.onOptionsItemSelected(item)
    }

    private fun showAboutDialog() {
        val builder = AlertDialog.Builder(this)
        val view = LayoutInflater.from(this)
            .inflate(R.layout.dialog_about_layout, null)
        builder.setView(view)

        val instagramBtn = view.findViewById<ImageButton>(R.id.instagram_btn)
        instagramBtn.setOnClickListener {
            openLink(
                Uri.parse("https://www.instagram.com/__rajnish__23/"),
                "com.instagram.android"
            )
        }

        val twitterBtn = view.findViewById<ImageButton>(R.id.twitter_btn)
        twitterBtn.setOnClickListener {
            openLink(
                Uri.parse("https://twitter.com/Rajnishsuryavan"),
                "com.twitter.android"
            )
        }

        val githubBtn = view.findViewById<ImageButton>(R.id.github_btn)
        githubBtn.setOnClickListener {
            openLink(
                Uri.parse("https://github.com/Rajnish23"),
                "com.github.android"
            )
        }
        val youtubeBtn = view.findViewById<TextView>(R.id.yoututbe_link_tv)
        youtubeBtn.setOnClickListener {
            openLink(Uri.parse("https://www.youtube.com/watch?v=VFrKjhcTAzE&t=673s"),
                "com.google.android.youtube")
        }


        val dialog = builder.create()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun openLink(uri: Uri, appPackage: String) {
        val youtubeIntent = Intent(Intent.ACTION_VIEW)
        youtubeIntent.data = uri
        youtubeIntent.setPackage(appPackage)
        try {
            startActivity(youtubeIntent)
        }
        catch (ex : ActivityNotFoundException){
            startActivity(
                Intent(Intent.ACTION_VIEW,
                uri
            ))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(findNavController(R.id.nav_host_fragment_container), drawer_layout)
    }
}
