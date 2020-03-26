package com.java.imagewithtextstoreinroom

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FormListAdapter(activity: Activity, notes: List<FormEntity>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private lateinit var activity: Activity
    private lateinit var forms: List<FormEntity>

    init {
        this.activity = activity
        this.forms = notes
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormAdapterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.form_list_child, parent, false)
        return FormAdapterViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val myViewHolder = holder as FormAdapterViewHolder
        val formItem = forms[position]
        myViewHolder.mName.setText(formItem.fname)
        myViewHolder.mAddress.setText(formItem.faddress)
        myViewHolder.mPhno.setText(formItem.fphno)
    }

    override fun getItemCount(): Int {
        return forms.size
    }

    fun getNoteList(): List<FormEntity> {
        return forms
    }

    inner class FormAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mName: TextView
        val mAddress: TextView
        val mPhno: TextView
        val mImage : ImageView

        init {
            mName = itemView.findViewById(R.id.ch_name)
            mAddress = itemView.findViewById(R.id.ch_address)
            mPhno = itemView.findViewById(R.id.ch_ph)
            mImage = itemView.findViewById(R.id.image)
        }
    }

}