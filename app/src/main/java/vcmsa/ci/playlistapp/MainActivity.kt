package vcmsa.ci.playlistapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {


    val songtitle = ArrayList<String>()
    val artistname = ArrayList<String>()
    val rating = ArrayList<Int>()
    val userComments = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addButton = findViewById<Button>(R.id.addButton)
        val viewListButton = findViewById<Button>(R.id.viewListButton)
        val exitButton = findViewById<Button>(R.id.exitButton)
        val feedbackText = findViewById<TextView>(R.id.feedbackTextView)

        addButton.setOnClickListener {
            val songtitles  = findViewById<EditText>(R.id.songtitle).text.toString()
            val artistsname = findViewById<EditText>(R.id.ArtistName).text.toString()
            val ratingsinput = findViewById<EditText>(R.id.rating).text.toString()
            val comment = findViewById<EditText>(R.id.userComments).text.toString()

            if (songtitles.isEmpty() || artistsname.isEmpty() || ratingsinput.isEmpty() || comment.isEmpty()) {
                feedbackText.text = "Please fill in all required fields"
            } else {
                val ratings = ratingsinput.toIntOrNull()
                if (ratings == null || ratings < 1) {
                    feedbackText.text = "Enter a valid quantity (1 or more)"
                } else {
                    songtitle.add(songtitles)
                    artistname.add(artistsname)
                    rating.add(ratings)
                    userComments.add(comment)

                    feedbackText.text = "Item added to PlayList"


                    findViewById<EditText>(R.id.songtitle).text.clear()
                    findViewById<EditText>(R.id.ArtistName).text.clear()
                    findViewById<EditText>(R.id.rating).text.clear()
                    findViewById<EditText>(R.id.userComments).text.clear()
                }
            }
        }


        viewListButton.setOnClickListener {
            val intent = Intent(this, DisplayActivity::class.java)
            intent.putStringArrayListExtra("Song Title", songtitle)
            intent.putStringArrayListExtra("Artist Name", artistname)
            intent.putIntegerArrayListExtra("Rating", rating)
            intent.putStringArrayListExtra("Comments", userComments)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}




