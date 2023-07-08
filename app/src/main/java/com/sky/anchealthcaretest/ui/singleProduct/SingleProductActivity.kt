package com.sky.anchealthcaretest.ui.singleProduct

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.Fade
import android.transition.Transition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sky.anchealthcaretest.R
import com.sky.anchealthcaretest.databinding.ActivitySingleProductBinding
import com.sky.anchealthcaretest.model.GetSingleProduct
import kotlin.properties.Delegates


class SingleProductActivity : AppCompatActivity() {

    private var isBig: Boolean = false
    lateinit var binding: ActivitySingleProductBinding
    lateinit var viewModel: SingleProductClassViewModel
    var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel = ViewModelProvider(this)[SingleProductClassViewModel::class.java]

        binding = ActivitySingleProductBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        clicklisteners()


        id = intent.getIntExtra("id", 0)

        viewModel.callSingleProduct(id).observe(this) {
            populateData(it)
        }
    }

    private fun clicklisteners() {

        binding.mainBack.setOnClickListener {
            finish()
        }

        binding.menuMore.setOnClickListener {
            Toast.makeText(this,"Menu Clicked",Toast.LENGTH_SHORT).show()
        }

        binding.order.setOnClickListener {
            Toast.makeText(this,"Order Clicked",Toast.LENGTH_SHORT).show()
        }

        binding.share.setOnClickListener {
            Toast.makeText(this,"Shared Clicked",Toast.LENGTH_SHORT).show()
        }

        binding.collapeIncrease.setOnClickListener {
            if (!isBig) {
                binding.collapeIncrease.setImageResource(R.drawable.ic_collapsed)
                val va = ValueAnimator.ofInt(700, 1000)
                va.duration = 400
                va.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    binding.belowLayout.layoutParams.height = value
                    binding.belowLayout.requestLayout()
                }
                va.start()
//                binding.containerReviews.visibility = View.VISIBLE
                isBig = true
                val transition: Transition = Fade()
                transition.duration = 200
                TransitionManager.beginDelayedTransition(binding.root, transition)
                binding.containerReviews.visibility = View.VISIBLE
            } else {
                binding.collapeIncrease.setImageResource(R.drawable.ic_expand)
                val va = ValueAnimator.ofInt(1000, 700)
                va.duration = 400
                va.addUpdateListener { animation ->
                    val value = animation.animatedValue as Int
                    binding.belowLayout.layoutParams.height = value
                    binding.belowLayout.requestLayout()
                }
                va.start()
                isBig = false
//                binding.containerReviews.visibility = View.GONE
                val transition: Transition = Fade()
                transition.duration = 200
                TransitionManager.beginDelayedTransition(binding.root, transition)
                binding.containerReviews.visibility = View.GONE
            }
            }

    }

    @SuppressLint("SetTextI18n")
    private fun populateData(getSingleProduct: GetSingleProduct) {
        try {


            binding.price.text = "${getSingleProduct.getPrice()} AED"
            binding.details.text = getSingleProduct.getTitle()

            binding.description.text = getSingleProduct.getDescription()


            binding.totalReviews.text = getSingleProduct.getRating()!!.rate.toString()
            binding.rating.rating = getSingleProduct.getRating()!!.rate!!
            binding.review.text = "Reviews (${getSingleProduct.getRating()!!.count})"

            runOnUiThread {
                Glide.with(this).load(getSingleProduct.getImage()).into(binding.image)
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        viewModel.callSingleProduct(id).removeObservers(this)
        super.onDestroy()
    }
}