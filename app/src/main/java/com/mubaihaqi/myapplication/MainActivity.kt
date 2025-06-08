package com.mubaihaqi.myapplication

import ListArticleAdapter
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Toast
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

    private fun showSelectedArticle(art: Articles) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra("EXTRA_NAME", art.name)
        intent.putExtra("EXTRA_DESC", art.description)
        intent.putExtra("EXTRA_PHOTO", art.photo)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashScreenView ->
            val fadeOut = ObjectAnimator.ofFloat(
                splashScreenView.view, View.ALPHA, 1f, 0f
            )
            fadeOut.duration = 1000
            fadeOut.interpolator = AccelerateDecelerateInterpolator()

            fadeOut.doOnEnd { splashScreenView.remove() }
            fadeOut.start()
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvArticles = findViewById(R.id.rv_articles)
        rvArticles.setHasFixedSize(true)

        list.addAll(getListArticles())
        showRecyclerList()
    }

    private fun getListArticles(): ArrayList<Articles> {
        val dataName = resources.getStringArray(R.array.liyue_titles)
        val dataDescription = resources.getStringArray(R.array.liyue_articles)
        val dataPhoto = resources.obtainTypedArray(R.array.liyue_photos)
        val listArticle = ArrayList<Articles>()
        for (i in dataName.indices) {
            val article = Articles(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArticle.add(article)
        }
        return listArticle
    }

    private fun showRecyclerList() {
        rvArticles.layoutManager = LinearLayoutManager(this)
        val listArticleAdapter = ListArticleAdapter(list)
        rvArticles.adapter = listArticleAdapter
        listArticleAdapter.setOnItemClickCallback(object : ListArticleAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Articles) {
                showSelectedArticle(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about_page-> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}