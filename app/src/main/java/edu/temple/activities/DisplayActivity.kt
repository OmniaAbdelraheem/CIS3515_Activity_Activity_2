package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
const val RESULT_KRY = "sizeSelected"


class DisplayActivity : AppCompatActivity() {

    //TODO Step 1: Launch TextSizeActivity when button is clicked
    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        it?.data?.run {
            lyricsDisplayTextView.textSize = getIntExtra("result", 22).toFloat()
        }
    }

    //TODO Step 3: Use returned value for lyricsDisplayTextView text size

    private lateinit var lyricsDisplayTextView: TextView
    private lateinit var textSizeSelectorButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        lyricsDisplayTextView = findViewById(R.id.lyricsDisplayTextView)
        textSizeSelectorButton = findViewById(R.id.textSizeSelectorButton)

        textSizeSelectorButton.setOnClickListener {
            launcher.launch(Intent(this, TextSizeActivity::class.java))
        }
    }
}
