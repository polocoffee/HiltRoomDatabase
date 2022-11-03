package com.banklannister.hlit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.banklannister.hlit.databinding.ItemNoteBinding
import com.banklannister.hlit.db.NoteEntity
import com.banklannister.hlit.ui.UpdateNoteActivity
import javax.inject.Inject

class NoteAdapter @Inject constructor(

) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var binding: ItemNoteBinding
    private lateinit var context: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = ItemNoteBinding.inflate(inflater, parent, false)
        context = parent.context
        return NoteViewHolder()
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size


    inner class NoteViewHolder : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteEntity) {
            binding.apply {
                tvTitle.text = item.noteTitle
                tvDesc.text = item.noteDesc
                root.setOnClickListener {
                    val intent = Intent(context, UpdateNoteActivity::class.java)
                    intent.putExtra("NOTE_ID", item.noteId)
                    context.startActivity(intent)
                }
            }
        }
    }

    private val differCallBack = object : DiffUtil.ItemCallback<NoteEntity>() {

        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.noteId == newItem.noteId
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

    }

     val differ = AsyncListDiffer(this, differCallBack)
}

