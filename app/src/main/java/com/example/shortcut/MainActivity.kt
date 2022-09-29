package com.example.shortcut

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addShortcut()
    }

    fun homeClicked(view: View) {
        startActivity(Intent(this, HomeActivity::class.java))
    }
    fun messageClicked(view: View) {
        startActivity(Intent(this, MessageActivity::class.java))
    }
    fun profileClicked(view: View) {
        startActivity(Intent(this, ProfileActivity::class.java))
    }
    fun searchClicked(view: View) {
        startActivity(Intent(this, SearchActivity::class.java))
    }

    fun addShortcut() {

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        val intent = Intent(this, SearchActivity::class.java)
        intent.action = Intent.ACTION_VIEW


        val sampleShortcut = ShortcutInfo.Builder(applicationContext,"Search")
            .setShortLabel(getString(R.string.search_label))
            .setIcon(Icon.createWithResource(applicationContext,R.drawable.icon_search))
            .setIntent(intent)
            .build()


        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute {
            shortcutManager.dynamicShortcuts = listOf(sampleShortcut)
            handler.post { }
        }

    }
}