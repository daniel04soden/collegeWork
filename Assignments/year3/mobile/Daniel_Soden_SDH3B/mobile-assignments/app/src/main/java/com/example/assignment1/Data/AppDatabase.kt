package com.example.assignment1.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.assignment1.Converter.Converters
import com.example.assignment1.Models.Entry
import com.example.assignment1.Models.Food
import com.example.assignment1.Models.MeditationVideo
import com.example.assignment1.Models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.mindrot.jbcrypt.BCrypt
import java.time.LocalDate
import java.time.LocalDateTime

@Database(entities = [Entry::class, User::class, Food::class, MeditationVideo::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun entryDao(): EntryDao
    abstract fun foodDao(): FoodDao

    abstract fun meditationDao(): MeditationDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .addCallback(AppDatabaseCallback(context))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val context: Context
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    prepopulateUsers(database.userDao())
                    preloadFoodData(context, database.foodDao())
                    prepopulateEntries(database.entryDao())
                    prePopulateVideos(meditationDao = database.meditationDao())
                }
            }
        }

        suspend fun preloadFoodData(context: Context, foodDao: FoodDao) {
            val importer = DataImporter(context, foodDao)
            importer.importDataFromJson()
        }

        suspend fun prePopulateVideos(meditationDao: MeditationDao) {
            meditationDao.deleteAll()
            val videos = listOf(
                MeditationVideo(
                    title = "Manifestation",
                    youtubeUrl = "https://youtu.be/flPKuREpT0I?si=jr-BaupYtnggAcgU",
                    description = """"
                        In my experience, this amazing practice is one of the fastest  and most 
                        simple ways to manifest!  And so I hope you're ready... 💞! 
                        """.trimMargin()),

                MeditationVideo(
                    title = "Telepathy",
                    youtubeUrl = "https://youtu.be/9bQN1fWljr8?si=kqDKckP9qAD5ArlS",
                    description = """
                        It may be that you just wish to send your SP or your loved one some love and 
                        let them know you are thinking of them.  
                    """.trimIndent()),

                MeditationVideo(
                    title = "Twin Flames",
                    youtubeUrl = "https://youtu.be/OB7zghGHDQM?si=U2y_hlvjhtUxFY6K",
                    description = """
                    💞 Your Twin Flame
                    💞 Your Specific Person
                    💞 Your Soulmate.
                    ....through telepathic communication. 
                    """.trimIndent()),

            )
            meditationDao.insertAll(videos)
        }

        suspend fun prepopulateUsers(userDao: UserDao) {
            userDao.deleteAllUsers()
            val yesterday = LocalDate.now().minusDays(1).toString()
            val users = listOf(
                User(
                    fullName = "John Doe",
                    age = 30,
                    gender = "Male",
                    email = "john.doe@example.com",
                    password = BCrypt.hashpw("password123", BCrypt.gensalt()),
                    weight = 80.0,
                    height = 180.0,
                    loseWeight = "Y",
                    caloriesPerDay = 2500.0,
                    calorieStreak = 42,
                    meditationStreak = 100,
                    journalStreak = 75,
                    lastFoodEntryDate = yesterday,
                    lastJournalEntryDate = yesterday,
                    lastMeditationEntryDate = yesterday
                ),
                User(
                    fullName = "Jane Smith",
                    age = 25,
                    gender = "Female",
                    email = "jane.smith@example.com",
                    password = BCrypt.hashpw("password456", BCrypt.gensalt()),
                    weight = 60.0,
                    height = 165.0,
                    loseWeight = "N",
                    caloriesPerDay = 2000.0,
                    calorieStreak = 80,
                    meditationStreak = 20,
                    journalStreak = 90,
                    lastFoodEntryDate = yesterday,
                    lastJournalEntryDate = yesterday,
                    lastMeditationEntryDate = yesterday
                )
            )
            userDao.insertAllUsers(*users.toTypedArray())
        }
        suspend fun prepopulateEntries(entryDao: EntryDao) {
            entryDao.insertEntry(Entry(userId = 1, name = "John Doe",
                text = "This is a test entry 1",
                rating = 5,
                date = LocalDateTime.now().minusDays(1)
                , calories = 2400.0,
            ))
            entryDao.insertEntry(Entry(
                userId = 1, name = "John Doe",
                text = "This is a test entry 2", rating = 8,
                date = LocalDateTime.now().minusDays(2),
                calories = 2400.0,
            )
            )
            entryDao.insertEntry(Entry(
                userId = 2,
                name = "Jane Smith",
                text = "This is a test entry 3",
                rating = 3,
                date = LocalDateTime.now().minusDays(3),
                calories = 222.22,
            ),
            )
        }
    }
}
