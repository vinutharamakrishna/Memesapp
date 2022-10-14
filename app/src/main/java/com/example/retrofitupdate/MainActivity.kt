package com.example.retrofitupdate

import android.content.Intent
import android.graphics.Color.blue
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.retrofitupdate.adapters.ViewPagerAdapter
import com.example.retrofitupdate.viewmodel.MemesViewModel
import com.example.retrofitupdate.viewmodel.MemesViewModelFactory
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var memesviewModel: MemesViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val apiInterface=apiUtility.getInstance().create(ApiInterface::class.java)
//
//        val memesRepository=MemesRepository(apiInterface)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val repository = (application as MyApplication).memesRepository

        memesviewModel = ViewModelProvider(
            this,
            MemesViewModelFactory(repository)
        ).get(MemesViewModel::class.java)

        memesviewModel.memes.observe(this) {
            val adapter = ViewPagerAdapter(this, it.data.memes)
            viewPager.adapter = adapter
//           Log.d("vinutha", "onCreate:${it.toString()}")
            //      Toast.makeText(this,"size:${it.data.memes.size}",Toast.LENGTH_LONG).show()
            //  it.data.memes.iterator().forEach { Meme ->
            //      Log.d("","name: ${Meme.name }\nimage : ${Meme.url}")
            //}

            val share = findViewById<Button>(R.id.btnshare)
            share.setOnClickListener {
//                share.backgroundTintList=getColorStateList(android.R.color.holo_green_light)
                val t1 = share.text.toString()
                val shareIntent = Intent()
                shareIntent.action = Intent.ACTION_SEND
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_TEXT, t1)
                startActivity(Intent.createChooser(shareIntent, "Share via"))
            }


            val next = findViewById<Button>(R.id.btnnext)
            next.setOnClickListener {
                viewPager.currentItem++
//                next.backgroundTintList=getColorStateList(android.R.color.holo_blue_light)
            }


            val prev = findViewById<Button>(R.id.btnprevious)
            prev.setOnClickListener {

//                    prev.backgroundTintList=getColorStateList(android.R.color.holo_blue_light)



                if (viewPager.currentItem == 0) {
//                    prev.backgroundTintList=getColorStateList(android.R.color.holo_orange_dark)
                    Toast.makeText(this, "This is the first one", Toast.LENGTH_SHORT).show()


                } else {
                    viewPager.currentItem--
                }
            }

            val del=findViewById<Button>(R.id.btndelete)
            del.setOnClickListener {
//                del.backgroundTintList=getColorStateList(android.R.color.holo_red_dark)
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setMessage("Are you sure you want to Delete?")
                    .setCancelable(false)
                    .setPositiveButton("Yes") { dialog, id ->
                        Log.d("MAIN", viewPager.currentItem.toString())
                        memesviewModel.memes.observe(this){
                            memesviewModel.deleteMeme(it.data.memes[viewPager.currentItem])
                        }
                        Toast.makeText(applicationContext, "DELETED", Toast.LENGTH_SHORT).show()

                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Dismiss the dialog
                        dialog.dismiss()
                    }
                val alert = builder.create()
                alert.show()
            }

            }


        }

    }