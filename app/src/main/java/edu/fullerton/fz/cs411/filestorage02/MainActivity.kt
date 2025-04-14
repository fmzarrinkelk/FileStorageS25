package edu.fullerton.fz.cs411.filestorage02

import android.Manifest
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat
import java.io.File

const val LOG_TAG = "MyApplication"
class MainActivity : AppCompatActivity() {

    private val textInputModel = UserTextInputModel(this)

    private lateinit var saveButton: Button
    private lateinit var textView: TextView
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.editText)
        saveButton = findViewById(R.id.button)
        image = findViewById(R.id.imageView)

        saveButton.setOnClickListener { textInputModel.saveText(textView.text.toString()) }

        textView.text = textInputModel.loadText()

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
            0
        )

        loadImage()

    }

    private fun loadImage() {
        Log.v(LOG_TAG, "Load Image Start")
        val storageRoot = Environment.getExternalStorageDirectory()
        val downloadDir = File(storageRoot, "Download")
        val file = downloadDir.listFiles()?.get(0)!!
        if (file.exists()) {
            Log.v(LOG_TAG, "found a file $file")
            val bytes = file.readBytes()
            val theBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
            this.image.setImageBitmap(theBitmap)
            Log.v(LOG_TAG, "File is ${bytes.size} bytes")
        }
        Log.v(LOG_TAG, "Load Image finished")
    }
}