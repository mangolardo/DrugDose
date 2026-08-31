package com.example.drugdose.data

import android.content.Context
import androidx.room3.ColumnTypeConverters
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.SQLiteConnection
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

@Database(entities = [Profile::class, Medicine::class], version = 24)
@ColumnTypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao() : AppDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDataBase(context: Context,scope: CoroutineScope) : AppDatabase{
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "DrugDoseDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabasePrepopulateCallback(context,scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}

class DatabasePrepopulateCallback(
    private val context: Context,
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override suspend fun onOpen(connection: SQLiteConnection) {
        super.onOpen(connection)
        scope.launch(Dispatchers.IO) {
            prepopulateDatabase()
        }
    }

    private suspend fun prepopulateDatabase() {
        try {
            val db = AppDatabase.getDataBase(context, scope)
            val dao = db.appDao()

            if (dao.getMedsCount() == 0) {
                // 1. Leggi il file JSON dalla cartella assets
                val jsonString = context.assets.open("medsJson.json")
                    .bufferedReader()
                    .use { it.readText() }

                val jsonFormatter = Json { ignoreUnknownKeys = true }

                // 2. Deserializza DIRETTAMENTE nella lista di Medicine
                val entities: List<Medicine> = jsonFormatter.decodeFromString(jsonString)

                // 3. Inserisci direttamente nel database!
                dao.insertAll(entities)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}