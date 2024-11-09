package com.cameron.photogallery

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {

    private val storagePermissionCode = 101
    private lateinit var photoRecyclerView: RecyclerView
    private val imageUris = mutableListOf<Uri>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set up RecyclerView grid.
        photoRecyclerView = findViewById(R.id.photoRecyclerView)
        photoRecyclerView.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
        photoRecyclerView.adapter = PhotoAdapter(this, imageUris)

        checkAndRequestPermissions()
    }

    private fun checkAndRequestPermissions() {
        //Choose the correct permission based off version.
        val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arrayOf(Manifest.permission.READ_MEDIA_IMAGES)
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        //Request permissions if not granted.
        if (permissions.any { ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED }) {
            ActivityCompat.requestPermissions(this, permissions, storagePermissionCode)
        } else {
            loadImagesFromStorage()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == storagePermissionCode) {
            if (grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                loadImagesFromStorage()
            } else {
                Toast.makeText(this, "Permission denied. Please enable storage permissions from settings.", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadImagesFromStorage() {
        imageUris.clear() // Clear the list to avoid duplicates
        val uri: Uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media._ID)
        val cursor: Cursor? = contentResolver.query(uri, projection, null, null, null)

        cursor?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                //Build the URI for each image.
                val contentUri = Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id.toString())
                imageUris.add(contentUri)
            }
        }

        if (imageUris.isEmpty()) {
            Toast.makeText(this, "No images found on device", Toast.LENGTH_SHORT).show()
        }

        //Update the RecyclerView with the new list of images.
        photoRecyclerView.post {
            photoRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    class PhotoAdapter(private val context: Context, private val imageUris: List<Uri>) :
        RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
            //Inflate the item layout for each photo.
            val view = LayoutInflater.from(context).inflate(R.layout.item_photo, parent, false)
            return PhotoViewHolder(view)
        }

        override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
            val imageUri = imageUris[position]
            //Load the image into ImageView.
            Glide.with(context).load(imageUri).into(holder.imageView)

            //Open full-screen image when clicked.
            holder.imageView.setOnClickListener {
                val intent = Intent(context, FullScreenImageActivity::class.java).apply {
                    putExtra(FullScreenImageActivity.IMAGE_URI_KEY, imageUri.toString())
                }
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int = imageUris.size

        class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.imageView)
        }
    }
}

class FullScreenImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen_image)

        val imageView: ImageView = findViewById(R.id.fullScreenImageView)
        val imageUriString = intent.getStringExtra(IMAGE_URI_KEY)
        val imageUri = Uri.parse(imageUriString)

        //Display the full-screen image.
        Glide.with(this).load(imageUri).into(imageView)
    }

    companion object {
        const val IMAGE_URI_KEY = "image_uri_key"
    }
}