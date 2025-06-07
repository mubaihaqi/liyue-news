package com.mubaihaqi.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ArticleActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)

        val name = intent.getStringExtra("EXTRA_NAME")
        val desc = intent.getStringExtra("EXTRA_DESC")
        val photo = intent.getIntExtra("EXTRA_PHOTO", -1)

        val tvName = findViewById<TextView>(R.id.articles_title)
        val tvDesc = findViewById<TextView>(R.id.articles_isi)
        val ivPhoto = findViewById<ImageView>(R.id.articles_images_preview)

        tvName.text = name
        tvDesc.text = desc
        ivPhoto.setImageResource(photo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = name

        val btnShare: Button = findViewById(R.id.share)
        btnShare.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.share -> {
                val title = findViewById<TextView>(R.id.articles_title).text.toString()
                val content = findViewById<TextView>(R.id.articles_isi).text.toString()

                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Baca artikel menarik!\n\n$title\n\n$content")
                    type = "text/plain"
                }

                startActivity(Intent.createChooser(shareIntent, "Bagikan melalui"))
            }
        }
    }

}