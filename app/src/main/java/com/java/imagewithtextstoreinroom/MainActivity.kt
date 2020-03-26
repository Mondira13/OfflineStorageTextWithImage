package com.java.imagewithtextstoreinroom

import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            addNewForm();
        }

        detailsButton.setOnClickListener {
            goToNextPage()
        }

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
                FormDatabaseClient(applicationContext).getInstance(getApplicationContext()).getFormAppDatabase().formDao().insert(formEntity);
                return true
            }


            override fun onPostExecute(aVoid: Boolean) {
                super.onPostExecute(aVoid)
                Toast.makeText(applicationContext, "Form Added successfully", Toast.LENGTH_SHORT).show();
                name.setText("")
                address.setText("")
                phno.setText("")
            }
        }

        val save = SaveNote()
        save.execute()

    }

}
