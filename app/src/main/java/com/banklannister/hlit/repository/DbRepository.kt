package com.banklannister.hlit.repository

import com.banklannister.hlit.db.NoteDao
import com.banklannister.hlit.db.NoteEntity
import javax.inject.Inject

class DbRepository @Inject constructor(
    private val dao: NoteDao
) {
    fun saveNote(note: NoteEntity) = dao.insertNote(note)

    fun updateNote(note: NoteEntity) = dao.updateNote(note)

    fun deleteNote(note: NoteEntity) = dao.deleteNote(note)

    fun getNote(id: Int) = dao.getNotes(id)

    fun getAllNotes() = dao.getAllNotes()


}