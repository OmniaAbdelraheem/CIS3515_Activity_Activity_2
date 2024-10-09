package edu.temple.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
const val SIZE_KEY = "selectedTextSize"
class TextSizeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create array of integers that are multiples of 5
        // Verify correctness by examining array values.
        val textSizes = Array(20){(it + 1) * 5}

        Log.d("Array values", textSizes.contentToString())

        with (findViewById(R.id.textSizeSelectorRecyclerView) as RecyclerView) {

            // TODO Step 2: Pass selected value back to activity that launched TextSizeActivity
            adapter = TextSizeAdapter(textSizes){ fontSize ->
                setResult(RESULT_OK, Intent().putExtra("result", fontSize))
            finish()
                }
            layoutManager = LinearLayoutManager(this@TextSizeActivity)

        }

    }
}


/* Convert to RecyclerView.Adapter */
class TextSizeAdapter(private val textSizes: Array<Int>, private val callback: (Int) -> Unit) :
    RecyclerView.Adapter<TextSizeAdapter.TextSizeViewHolder>() {

    inner class TextSizeViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView) {
        init {
            // When a textView (number) is clicked, pass the number to the callback
            textView.setOnClickListener { callback(textSizes[adapterPosition]) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextSizeViewHolder {
        // Create a new TextView for each number and set padding
        return TextSizeViewHolder(TextView(parent.context).apply { setPadding(5, 20, 0, 20) })
    }

    override fun onBindViewHolder(holder: TextSizeViewHolder, position: Int) {
        // Bind the text size value to the TextView
        holder.textView.apply {
            text = textSizes[position].toString()  // Display the text size as a number
            textSize = textSizes[position].toFloat()  // Set the size of the text in the list
        }
    }

    override fun getItemCount(): Int {
        return textSizes.size  // The total number of items in the array
    }
}