package com.aurumswallpapers.app.aurumadapter

import android.content.pm.PackageManager
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.io.File
import java.io.FileOutputStream
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Environment
import android.view.LayoutInflater
import com.aurumswallpapers.app.MainActivity
import com.aurumswallpapers.app.aurumdata.AurumData
import com.aurumswallpapers.app.databinding.AurumsWallpaperItemBinding

import java.io.OutputStream
import java.lang.Exception
import java.util.*

class AurumsAdapter(val aurumListItem :List<AurumData>, val activity: AppCompatActivity) : RecyclerView.Adapter<AurumsAdapter.ProfItemHolder>() {
    class ProfItemHolder(val binding : AurumsWallpaperItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun setProfLaItem(aurumItem : AurumData, activity : AppCompatActivity) = with(binding){
            Picasso.get().load(aurumItem.aurumImgItem).centerCrop().resize(1280,720)
                .into(mainAurumPhoto,object : Callback {
                    override fun onSuccess() {
                        pbAurumBar.visibility = View.INVISIBLE
                    }

                    override fun onError(e: Exception?) {

                    }

                } )

            saveAurumb.setOnClickListener {
                if(ContextCompat.checkSelfPermission(activity,android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(activity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),100)
                } else {
                    val externalStoreState = Environment.getExternalStorageState()
                    if(externalStoreState.equals(Environment.MEDIA_MOUNTED)){
                        try{
                            val storeDirectory = Environment.getExternalStorageDirectory().absolutePath
                            val file = File(storeDirectory,"${UUID.randomUUID()}.jpg")
                            val stream : OutputStream = FileOutputStream(file)
                            val drawable  = ContextCompat.getDrawable(activity.applicationContext,aurumItem.aurumImgItem)
                            val bitmap = (drawable as BitmapDrawable).bitmap
                            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream)
                            stream.flush()
                            stream.close()

                            val snackBar = Snackbar.make((activity as MainActivity).findViewById(android.R.id.content),"Image is saving...",Snackbar.LENGTH_LONG)
                            snackBar.show()
                        }catch(e : Exception){
                            Toast.makeText(activity.applicationContext, "Image is saving...",Toast.LENGTH_LONG).show()
                        }


                    }
                }

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfItemHolder {
        val binding = AurumsWallpaperItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProfItemHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfItemHolder, position: Int) {
        holder.setProfLaItem(aurumListItem[position],activity)

    }

    override fun getItemCount() = aurumListItem.size


}