package com.example.assignmentproject.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignmentproject.data.db.dao.NewsDao
import com.example.assignmentproject.data.db.typeconverter.SourceConverter
import com.example.assignmentproject.data.remote.model.ArticlesItem
import timber.log.Timber

@Database(entities = [ArticlesItem::class],version = 1)
@TypeConverters(
    SourceConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        const val DB_NAME = "sample-db"
        private val lock = Any()
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                Timber.d("getInstance")
                if (INSTANCE == null) {
                    INSTANCE = createDB(context)
                }
                return INSTANCE as AppDatabase
            }
        }

        private fun createDB(context: Context): AppDatabase {
            Timber.d("createDB")
            val database: Builder<AppDatabase> =
                Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            return database
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Timber.d("onCreate")
                    }
                })
                .build()
        }
    }
}