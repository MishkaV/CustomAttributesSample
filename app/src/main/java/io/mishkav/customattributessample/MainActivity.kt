package io.mishkav.customattributessample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private var isEating = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pacmanView = findViewById<io.mishkav.customattributessample.customView.PacManView>(R.id.pacman)


        val button = findViewById<Button>(R.id.button)

        button.setOnClickListener {
            isEating = !isEating
            pacmanView.setIsEating(isEating)
        }
    }
}