package vcmsa.ci.playlistapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DisplayActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)


        val songtitles = intent.getStringArrayListExtra("Song Title") ?: arrayListOf()
        val artistsname = intent.getStringArrayListExtra("Artist Name") ?: arrayListOf()
        val ratings = intent.getIntegerArrayListExtra("Rating") ?: arrayListOf()
        val comment = intent.getStringArrayListExtra("Comments") ?: arrayListOf()

        val displayTextView = findViewById<TextView>(R.id.displayPlaylist)
        val returnButton = findViewById<Button>(R.id.returnButton)

        val displayList = StringBuilder()


        for (i in songtitles.indices) {
            if ( ratings[i] >= 2) {
                displayList.append("Item: ${songtitles[i]}\n")
                displayList.append("Category: ${artistsname[i]}\n")
                displayList.append("Quantity: ${ratings[i]}\n")
                displayList.append("Comment: ${comment[i]}\n\n")
            }
        }

        if (displayList.isEmpty()) {
            displayTextView.text = "No items with quantity 2 or more."
        } else {
            displayTextView.text = displayList.toString()
        }

        returnButton.setOnClickListener {
            finish()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
