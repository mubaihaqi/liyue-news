package com.mubaihaqi.myapplication

import ListArticleAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvArticles: RecyclerView
    private val list = ArrayList<Articles>()

    private fun showSelectedHero(hero: Articles) {
        Toast.makeText(this, "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
                showSelectedHero(data)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_profile -> {
                Toast.makeText(this, "Profil diklik", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}