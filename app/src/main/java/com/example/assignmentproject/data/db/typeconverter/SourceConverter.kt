package com.example.assignmentproject.data.db.typeconverter

import androidx.room.TypeConverter
import com.example.assignmentproject.data.remote.model.Source
import com.google.gson.Gson

class SourceConverter {
    @TypeConverter
    fun jsonToAdmin(source: String?): Source? {
        return Gson().fromJson(source, Source::class.java)
    }

    @TypeConverter
    fun adminToJson(source: Source?): String {
        return Gson().toJson(source)
    }
}