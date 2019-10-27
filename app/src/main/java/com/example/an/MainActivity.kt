package com.example.an

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var toBackAnimation: AnimatorSet
    private lateinit var toFrontAnimation: AnimatorSet
    private lateinit var cardFrontLayout: View
    private lateinit var cardBackLayout: View
    private var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cardFrontLayout = findViewById(R.id.card_front)
        cardBackLayout = findViewById(R.id.card_back)

        toBackAnimation = AnimatorInflater.loadAnimator(this, R.animator.front_animation) as AnimatorSet
        toFrontAnimation = AnimatorInflater.loadAnimator(this, R.animator.back_animation) as AnimatorSet

        findViewById<View>(R.id.card).setOnClickListener {
            it.isEnabled = false
            Handler().postDelayed({ it.isEnabled = true }, 1000L)
            isFront = if (isFront) {
                startAnimationToBack()
                false
            } else {
                startAnimationToFront()
                true
            }
        }
        fixRotationWidth()
    }

    private fun fixRotationWidth() {
        val scale = resources.displayMetrics.density * 6000
        cardFrontLayout.cameraDistance = scale
        cardBackLayout.cameraDistance = scale
    }

    private fun startAnimationToBack() {
        toBackAnimation.setTarget(cardFrontLayout)
        toFrontAnimation.setTarget(cardBackLayout)
        toBackAnimation.start()
        toFrontAnimation.start()
    }

    private fun startAnimationToFront() {
        toBackAnimation.setTarget(cardBackLayout)
        toFrontAnimation.setTarget(cardFrontLayout)
        toBackAnimation.start()
        toFrontAnimation.start()
    }

}
