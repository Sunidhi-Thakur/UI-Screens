package com.example.screens

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.screens.databinding.ActivitySlidesBinding
import me.relex.circleindicator.CircleIndicator3


class Slides : AppCompatActivity() {

    private lateinit var binding: ActivitySlidesBinding
    private var titleList = mutableListOf<Int>()
    private var descList = mutableListOf<Int>()
    private var imagesList = mutableListOf<Int>()
    private var flag = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySlidesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postToList()

        binding.viewPager2.adapter = ViewPagerAdapter(titleList, descList, imagesList)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL


        val indicator: CircleIndicator3 = binding.indicator
        indicator.setViewPager(binding.viewPager2)



        binding.viewPager2.registerOnPageChangeCallback(object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if(position == 2) {
                    binding.nextButton.setText(R.string.start)
                    binding.nextButton.setOnClickListener {
                        val intent = Intent(this@Slides, Login::class.java)
                        startActivity(intent)
                        finish()

                    }
                }
                    else{
                        binding.nextButton.setText(R.string.next)
                        binding.nextButton.setOnClickListener {
                        binding.viewPager2.apply {
                            flag++
                            beginFakeDrag()
                            fakeDragBy(-10f)
                            endFakeDrag()
                        }

                    }
                }
            }

        })


//               binding.nextButton.setOnClickListener {
//                   binding.viewPager2.apply {
//                       flag++
//                       beginFakeDrag()
//                       fakeDragBy(-10f)
//                       endFakeDrag()
//
//                   if(flag == 3) {
//                       binding.nextButton.setText(R.string.start)
//                       binding.nextButton.setOnClickListener {
//                           val intent = Intent(this@Slides, Registration::class.java)
//                           startActivity(intent)
//                           finish()
//                       }
//                   }
//                   }
//               }

            }

    private fun postToList() {
        addToList(R.string.slide1_heading, R.string.slide1_decs, R.drawable.slider1)
        addToList(R.string.slide2_heading, R.string.slide2_decs, R.drawable.slider2)
        addToList(R.string.slide3_heading, R.string.slide3_decs, R.drawable.slider3)
    }

    private fun addToList(title: Int, description: Int, image: Int){
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)
    }


}