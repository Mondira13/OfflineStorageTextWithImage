package com.java.imagewithtextstoreinroom

import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.java.imagewithtextstoreinroom.camerarelated.CameraProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var cameraProvider: CameraProvider? = null
    private var mBitmapImage: Bitmap? = null
    private var mFilepath: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onClickListener()

    }

    private fun onClickListener() {
        button.setOnClickListener {
            addNewForm();
        }

        detailsButton.setOnClickListener {
            goToNextPage()
        }

        takeImage.setOnClickListener(View.OnClickListener { handleCameraIconClick() })
        takeImage.setOnFocusChangeListener(OnFocusChangeListener { view, b ->
            if (b) {
                handleCameraIconClick()
            }
        })

    }

    private fun handleCameraIconClick() {
        cameraProvider?.captureImage("takePic")
    }

    override fun onStart() {
        super.onStart()
        cameraProvider = CameraProvider.getInstance(this)
        cameraProvider?.setupProviderForImage("myPic", true,
            false,
            object : CameraProvider.ImagePickerListener {
             override fun onImagePicked(
                    bitmapImage: Bitmap,
                    filepath: String,
                    id: String?
                ) {
                    onNewImagePicked(bitmapImage, filepath)
                }
            })
    }

    override fun onStop() {
        super.onStop()
        cameraProvider?.releaseProviderData("myPic")
    }

    private fun onNewImagePicked(bitmapImage: Bitmap, filepath: String) {
        mBitmapImage = bitmapImage
        mFilepath = filepath
        takeImage.setText(mFilepath?.lastIndexOf("/")?.plus(1)?.let { mFilepath?.substring(it) })
    }








    private fun goToNextPage() {
        val intent = Intent(this, ShowDetailsActivity::class.java)
        startActivity(intent)
    }

    private fun addNewForm() {
        val formName = name.text.toString()
        val formAddress = address.text.toString()
        val formPhone = phno.text.toString()

        val formEntity = FormEntity()
        if (formName.isNotEmpty() && formAddress.isNotEmpty() && formPhone.isNotEmpty()) {
            formEntity.fname = formName
            formEntity.faddress = formAddress
            formEntity.fphno = formPhone
        }

        class SaveNote : AsyncTask<FormEntity, Void, Boolean>() {

            override fun doInBackground(vararg params: FormEntity?): Boolean {
                FormDatabaseClient(applicationContext).getInstance(getApplicationContext())
                    .getFormAppDatabase().formDao().insert(formEntity);
                return true
            }


            override fun onPostExecute(aVoid: Boolean) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Form Added successfully", Toast.LENGTH_SHORT)
                    .show();
                name.setText("")
                address.setText("")
                phno.setText("")
            }
        }

        val save = SaveNote()
        save.execute()

    }

}
