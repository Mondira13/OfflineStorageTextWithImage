package com.java.imagewithtextstoreinroom

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_show_details.*

class ShowDetailsActivity : AppCompatActivity() {

    lateinit var note: FormEntity
    lateinit var adapter: FormListAdapter
    lateinit var noteEntity: List<FormEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_details)

        recycler.setLayoutManager(LinearLayoutManager(this));
        getForms();

    }

    private fun getForms() {

        class GetNotes : AsyncTask<Void, Void, List<FormEntity>>() {

            override fun doInBackground(vararg params: Void?): List<FormEntity> {
                noteEntity = FormDatabaseClient(applicationContext).getInstance(applicationContext).getFormAppDatabase().formDao().getAllForms()
                return noteEntity
            }

            protected override fun onPostExecute(notes: List<FormEntity>) {
                super.onPostExecute(notes)
                adapter = FormListAdapter(this@ShowDetailsActivity, notes)
                recycler.setAdapter(adapter)
            }
        }

        val getNotesss = GetNotes()
        getNotesss.execute()

    }

}
