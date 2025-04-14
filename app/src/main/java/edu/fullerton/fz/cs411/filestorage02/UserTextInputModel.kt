package edu.fullerton.fz.cs411.filestorage02

import android.content.Context
import android.util.Log
import java.io.File

const val TEXT_FILE_NAME = "userInput.txt"

class UserTextInputModel(_context: Context) {
    private var context = _context

    private fun makeFile(): File {
        return File(context.filesDir, TEXT_FILE_NAME)
    }
    fun saveText(s: String) {
        val file = this.makeFile()
        Log.v(LOG_TAG, "Saving to $file")
        file.delete()
        file.writeText(s)
    }
    fun loadText(): String {
        val file = this.makeFile()
        var s = ""
        if (file.exists()) {
            s = file.readText()
        }
        return s
    }
}