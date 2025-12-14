## Functional Requirements Implementation

### Android health data

- As a means of integrating some form of api call, given theres a large amount of health statistics already involved I decided to give the users the ability to call the steps that their phone is already tracking for them. The following code calls steps in a sync method and saves it to the current user and will be logged when creating a journal entry:

```kotlin
implementation("androidx.health.connect:connect-client:1.1.0")


LaunchedEffect(Unit) {
	val granted = healthConnectClient.permissionController.getGrantedPermissions()
	if (granted.containsAll(permissions)) {
		readSteps(healthConnectClient) { stepCount ->
			steps = stepCount
		}
	}
}

suspend fun readSteps(healthConnectClient: HealthConnectClient, onStepsRead: (Long) -> Unit) {
	try {
		val end = LocalDateTime.now()
		val start = end.toLocalDate().atStartOfDay()
		val request = AggregateRequest(
			metrics = setOf(StepsRecord.COUNT_TOTAL),
			timeRangeFilter = TimeRangeFilter.between(start, end)
		)        val result = healthConnectClient.aggregate(request)
		onStepsRead(result[StepsRecord.COUNT_TOTAL] ?: 0L)
	} catch (e: Exception) {
		Log.d("HealthConnect", "Error reading steps", e)
	}}


	val permissionLauncher = rememberLauncherForActivityResult(
	contract = ActivityResultContracts.RequestMultiplePermissions(),
) { permissionsMap ->
	if (permissionsMap.values.all { it }) {
		coroutineScope.launch {
			readSteps(healthConnectClient) { stepCount ->
				steps = stepCount
				currentUser?.steps = steps.toDouble()
			}
		}    }
}
```

- In the home-screen view above we see that once a user clicks the sync button, once they have logged a certain amount of step sthroughout the day a coroutine will launch and they will be able to reload their total steps to be accurate compared to the current count of 0

### Calorie tacking

- Given a major part of this project was tracking and logging calories, expecting users to create an entire dataset/group of food items to track would be quite ridicoulous. As a result I took it upon myself to look into databases,APIs and datasets on food items. As seen below in the end i decided to go for a dataset (see https://www.kaggle.com/datasets/openfoodfacts/world-food-facts/data) as it made the most sense to me given the way I would also be having users have the option of creating their own food items,having these items and pre-loaded data have the same attributes made the most logical sense
- To achieve this using pandas as we have used it a lot in data analytics. I parsed this tsv as a csv file and converted this data into a json file only selecting the columns that were most important to me as the dataset also included 164 other columns.
- See full zipped folder for entire script (certain boilerplate omitted in below screenshot)

```python
	data = pd.read_csv(path, sep="\t", low_memory=False)
	selected_columns = [
		"product_name",
		"energy_100g",
		"fat_100g",
		"carbohydrates_100g",
		"sugars_100g",
		"fiber_100g",
		"proteins_100g",
	]
	subset = data.loc[idx : idx + 15000, selected_columns] # limit to 15,000 rows
	subset = subset.rename(
		columns={
			"product_name": "productName",
			"energy_100g": "energy100G",
			"fat_100g": "fatOneHundredG",
			"carbohydrates_100g": "carboHyOneHundredG",
			"sugars_100g": "sugarOneHundredG",
			"fiber_100g": "fiberOneHundredG",
			"proteins_100g": "proteinOneHundredG",
		}
	)
	json_output = subset.to_json(orient="records", indent=4)
```

- After getting this json file, one may think that holding and reading an entire json file into the database would be quite resource intensive however this json file upon running the command below:

```sh
du -hs res.json

3.0M    res.json
```

- We see it only occupies 3mb of space.

- Finally I wrote a quick class in the android application to load this data into the application database on first build:

```kotlin
suspend fun importDataFromJson() {
	withContext(Dispatchers.IO) {
		Log.d("Import","Importing data from JSON")
		val count = foodDao.getFoodCount()
		Log.d("Import","Init food count: $count")
		if (count > 0) {
			println("Database already contains $count food items. Skipping JSON import.")
			return@withContext
		}

		try {
			Log.d("Import","Importing data from JSON")
			val inputStream = context.assets.open(jsonFile)
			val reader = InputStreamReader(inputStream)
			Log.d("Import","File opened")

			val listType = object : TypeToken<List<Food>>() {}.type
			val foodEntities: List<Food> = Gson().fromJson(reader, listType)

			reader.close()
			Log.d("Import","${foodEntities.size} items from JSON.")

			if (foodEntities.isNotEmpty()) {
				foodDao.insertAll(foodEntities)
				Log.d("Import","Successfully imported ${foodEntities.size} food items.")
			}
		} catch (e: Exception) {
			Log.d("Import","Import failed")
			e.printStackTrace()
			Log.d("Import","Error importing data: ${e.message}")
		}    }
}
```

- From here I implemented a simple system where in the constructor depending on a persons gender, height and weight they would be given a caloric intake recommendation per day. I initially was going to have that be a part of the streak but to combat issues like pressure from eating disorders I decided against it
- This calculation uses the **Harris-Benedict Equation** as a means of calculating the recommended calories per day

```kotlin
fun calculateCaloriesPerDay(weight: Double, height: Double, age: Int, gender: String, loseWeight: String): Double {
	var res: Double = if (gender.lowercase() == "female") {
		10 * weight + 6.25 * height - 5 * age - 161
	} else {
		10 * weight + 6.25 * height - 5 * age + 5
	}

	if (loseWeight == "Y") {
		res -= 200
	} else {
		res += 200
	}
	return res
}
```

- Then in the food view page, after the food is loaded in via a lazycolumn that depends on cached paging via the ModelScope as a means of preventing overloaded memory given we have 15,000 rows of food to load in, users can hit add to the desired food by either searching up or creating their own food objects and from there they are able to see their calories consumed increase. This count resets daily.

```kotlin
val itemsFlow: Flow<PagingData<Food>> = combine(
	_calorieFilter,
	_searchQuery,
	_customFoodFilter
) { calorieFilter, searchQuery, customFoodFilter ->
	Triple(calorieFilter, searchQuery, customFoodFilter)
}.flatMapLatest { (calorieFilter, searchQuery, customFoodFilter) ->
	if (!searchQuery.isNullOrBlank()) {
		foodRepo.searchFoodByName(searchQuery)
	} else if (calorieFilter != null) {
		foodRepo.getFoodByCalories(calorieFilter)
	} else if (customFoodFilter != null) {
		foodRepo.getFoodByCustom(customFoodFilter)
	}    else {
		foodRepo.getAllFood()
	}}.cachedIn(viewModelScope)



	fun addFoodToUser(food: Food) {
		viewModelScope.launch {
			userViewModel.currentUser.value?.let { currentUser ->
				val today = LocalDate.now()
				val lastFoodDate = currentUser.lastFoodEntryDate?.let { LocalDate.parse(it) }

				var newStreak = currentUser.calorieStreak ?: 0

				if (lastFoodDate == null || lastFoodDate.isBefore(today.minusDays(1))) {
					newStreak = 1
				} else if (lastFoodDate.isEqual(today.minusDays(1))) {
					newStreak++
				}

				val updatedUser = currentUser.copy(
					caloriesConsumedToday = currentUser.caloriesConsumedToday + food.energy100G,
					proteinConsumedToday = currentUser.proteinConsumedToday + food.proteinOneHundredG,
					lastFoodEntryDate = today.toString(),
					calorieStreak = newStreak
				)
				userViewModel.updateUser(updatedUser)
			}
		}    }

	fun addNewFood(food: Food) {
		viewModelScope.launch {
			foodRepo.insertFood(food)
		}
	}
}
```

### Meditation

- Meditation was a much simpler concept to implement as all I needed was a couple of pre-loaded youtube videos that are randomly called at daily and have the user watch one to completion which would then count to their streak for meditation
- I used the following API as a means of embedding youtube videos as the original android/google api has been deprecated for quite some time:
- https://github.com/PierfrancescoSoffritti/android-youtube-player

- To embed these videos I had to use their quickstart xml layout as a means of creating the components for video players

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
        android:id="@+id/youtube_player_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

- From here after creating my meditation video model,viewmodel and repository for these videos, the majority of the remaining work was in the view where I had to manage things like video state between the view and viewmodel in order to load the videos

```kotlin
    val meditationUiState: StateFlow<MeditationUiState> =
        repository.getAllMeditations().map { meditations ->
            MeditationUiState(meditations)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MeditationUiState()
        )}

data class MeditationUiState(
    val meditations: List<MeditationVideo> = emptyList()
)


// View

val uiState by meditationViewModel.meditationUiState.collectAsState()
val randomVideo by meditationViewModel.randomMeditationVideo.collectAsState()
```

- For a while between having the description title and video I was unable to get the dispay I wanted but after toying around with the max width and aspect ratio I got something clean like seen in the video

```kotlin
Column {
    if (videoId != null) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth() // Full width to include description too
                .aspectRatio(16f / 9f) // 16:9 aspect ratio so it fits in cards
                .clip(RoundedCornerShape(
                    topStart = 8.dp, topEnd = 8.dp
                )), // Clip top corners for visibility
            factory = { context ->
                YouTubePlayerView(context).apply { // Using youtube player from api
                    addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(videoId, 0f)
                        }                        override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                            if (state == PlayerConstants.PlayerState.ENDED) {
                                onMeditationComplete() // Once video completed, increase streak
                            }
                        }                    })                }
            }        )
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = meditation.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = meditation.description)
    }
```

- Overall I would've done this a lot better in terms of having a larger non-hardcoded bank of meditation videos but due to personal time constrains I was unable to deliver on that front

### Journal Entries

- Apart from clicking into entries for details and other small features included in the original assignment, my journal entries remained mostly the same apart from having the name be something you don't have to input yourself. Given journals are a daily occurrence and it would get quite hectic with multiple entries in one day I used the following code to limit users to one entry per day.

```kotlin
val currentUser = userRepository.getUserById(userId)
val todayDate = LocalDate.now()
val startOfDay = todayDate.atStartOfDay()
val endOfDay = todayDate.atTime(LocalTime.MAX)

val entriesFromToday = entryRepository.getEntriesForToday(userId, startOfDay, endOfDay)

if (entriesFromToday.isNotEmpty()) {
    println("Entry already done today")
    return false
}
```

### Jbcrypt

- Given my implementation of users, storing password as plain text would be ridiculously insecure so from previous experience in server side web dev I looked into if there was a Kotlin implementation and sure enough here we are

```kotlin
implementation(libs.jbcrypt)
```

- Then from here I simply implemented a hashing and checking function so that once users sign up or try to login all credentials are processed through one way encryption:

```kotlin
private fun hashPass(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun checkPassword(password: String, hashed: String): Boolean {
    return BCrypt.checkpw(password, hashed)
}
```

### Local data storage/Database work

- Initially my plan was to simply have a user install this application and all info ranging from their journal entries to their meditation logs to the food they have eaten. However when experimenting with room it made most sense for me to include users as a means of having specific entries be tracked by ids for certain users and having the last meditation or journal entry date be logged and stored too.

- As per our notes, I utilised room as the database as a means of user storage, entry storage, meditation entity storage and food storage. Although my food storage could've been done more optimally using an api, for convenience and offline usage this made more sense to me
- In terms of normalisation and data connections, the only real connection between our data here is between Users and entries as users have their own unique group of entries that are called via find by ids

#### Schemas

##### Entry

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### User

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### Food

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### MeditationVideos

```kotlin
@Entity(tableName = "meditation")
data class MeditationVideo(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val youtubeUrl: String,
	val description: String
)
```

## Functional Requirements Implementation

### Android health data

- As a means of integrating some form of api call, given theres a large amount of health statistics already involved I decided to give the users the ability to call the steps that their phone is already tracking for them. The following code calls steps in a sync method and saves it to the current user and will be logged when creating a journal entry:

```kotlin
implementation("androidx.health.connect:connect-client:1.1.0")


LaunchedEffect(Unit) {
	val granted = healthConnectClient.permissionController.getGrantedPermissions()
	if (granted.containsAll(permissions)) {
		readSteps(healthConnectClient) { stepCount ->
			steps = stepCount
		}
	}
}

suspend fun readSteps(healthConnectClient: HealthConnectClient, onStepsRead: (Long) -> Unit) {
	try {
		val end = LocalDateTime.now()
		val start = end.toLocalDate().atStartOfDay()
		val request = AggregateRequest(
			metrics = setOf(StepsRecord.COUNT_TOTAL),
			timeRangeFilter = TimeRangeFilter.between(start, end)
		)        val result = healthConnectClient.aggregate(request)
		onStepsRead(result[StepsRecord.COUNT_TOTAL] ?: 0L)
	} catch (e: Exception) {
		Log.d("HealthConnect", "Error reading steps", e)
	}}


	val permissionLauncher = rememberLauncherForActivityResult(
	contract = ActivityResultContracts.RequestMultiplePermissions(),
) { permissionsMap ->
	if (permissionsMap.values.all { it }) {
		coroutineScope.launch {
			readSteps(healthConnectClient) { stepCount ->
				steps = stepCount
				currentUser?.steps = steps.toDouble()
			}
		}    }
}
```

- In the home-screen view above we see that once a user clicks the sync button, once they have logged a certain amount of step sthroughout the day a coroutine will launch and they will be able to reload their total steps to be accurate compared to the current count of 0

### Calorie tacking

- Given a major part of this project was tracking and logging calories, expecting users to create an entire dataset/group of food items to track would be quite ridicoulous. As a result I took it upon myself to look into databases,APIs and datasets on food items. As seen below in the end i decided to go for a dataset (see https://www.kaggle.com/datasets/openfoodfacts/world-food-facts/data) as it made the most sense to me given the way I would also be having users have the option of creating their own food items,having these items and pre-loaded data have the same attributes made the most logical sense
- To achieve this using pandas as we have used it a lot in data analytics. I parsed this tsv as a csv file and converted this data into a json file only selecting the columns that were most important to me as the dataset also included 164 other columns.
- See full zipped folder for entire script (certain boilerplate omitted in below screenshot)

```python
	data = pd.read_csv(path, sep="\t", low_memory=False)
	selected_columns = [
		"product_name",
		"energy_100g",
		"fat_100g",
		"carbohydrates_100g",
		"sugars_100g",
		"fiber_100g",
		"proteins_100g",
	]
	subset = data.loc[idx : idx + 15000, selected_columns] # limit to 15,000 rows
	subset = subset.rename(
		columns={
			"product_name": "productName",
			"energy_100g": "energy100G",
			"fat_100g": "fatOneHundredG",
			"carbohydrates_100g": "carboHyOneHundredG",
			"sugars_100g": "sugarOneHundredG",
			"fiber_100g": "fiberOneHundredG",
			"proteins_100g": "proteinOneHundredG",
		}
	)
	json_output = subset.to_json(orient="records", indent=4)
```

- After getting this json file, one may think that holding and reading an entire json file into the database would be quite resource intensive however this json file upon running the command below:

```sh
du -hs res.json

3.0M    res.json
```

- We see it only occupies 3mb of space.

- Finally I wrote a quick class in the android application to load this data into the application database on first build:

```kotlin
suspend fun importDataFromJson() {
	withContext(Dispatchers.IO) {
		Log.d("Import","Importing data from JSON")
		val count = foodDao.getFoodCount()
		Log.d("Import","Init food count: $count")
		if (count > 0) {
			println("Database already contains $count food items. Skipping JSON import.")
			return@withContext
		}

		try {
			Log.d("Import","Importing data from JSON")
			val inputStream = context.assets.open(jsonFile)
			val reader = InputStreamReader(inputStream)
			Log.d("Import","File opened")

			val listType = object : TypeToken<List<Food>>() {}.type
			val foodEntities: List<Food> = Gson().fromJson(reader, listType)

			reader.close()
			Log.d("Import","${foodEntities.size} items from JSON.")

			if (foodEntities.isNotEmpty()) {
				foodDao.insertAll(foodEntities)
				Log.d("Import","Successfully imported ${foodEntities.size} food items.")
			}
		} catch (e: Exception) {
			Log.d("Import","Import failed")
			e.printStackTrace()
			Log.d("Import","Error importing data: ${e.message}")
		}    }
}
```

- From here I implemented a simple system where in the constructor depending on a persons gender, height and weight they would be given a caloric intake recommendation per day. I initially was going to have that be a part of the streak but to combat issues like pressure from eating disorders I decided against it
- This calculation uses the **Harris-Benedict Equation** as a means of calculating the recommended calories per day

```kotlin
fun calculateCaloriesPerDay(weight: Double, height: Double, age: Int, gender: String, loseWeight: String): Double {
	var res: Double = if (gender.lowercase() == "female") {
		10 * weight + 6.25 * height - 5 * age - 161
	} else {
		10 * weight + 6.25 * height - 5 * age + 5
	}

	if (loseWeight == "Y") {
		res -= 200
	} else {
		res += 200
	}
	return res
}
```

- Then in the food view page, after the food is loaded in via a lazycolumn that depends on cached paging via the ModelScope as a means of preventing overloaded memory given we have 15,000 rows of food to load in, users can hit add to the desired food by either searching up or creating their own food objects and from there they are able to see their calories consumed increase. This count resets daily.

```kotlin
val itemsFlow: Flow<PagingData<Food>> = combine(
	_calorieFilter,
	_searchQuery,
	_customFoodFilter
) { calorieFilter, searchQuery, customFoodFilter ->
	Triple(calorieFilter, searchQuery, customFoodFilter)
}.flatMapLatest { (calorieFilter, searchQuery, customFoodFilter) ->
	if (!searchQuery.isNullOrBlank()) {
		foodRepo.searchFoodByName(searchQuery)
	} else if (calorieFilter != null) {
		foodRepo.getFoodByCalories(calorieFilter)
	} else if (customFoodFilter != null) {
		foodRepo.getFoodByCustom(customFoodFilter)
	}    else {
		foodRepo.getAllFood()
	}}.cachedIn(viewModelScope)



	fun addFoodToUser(food: Food) {
		viewModelScope.launch {
			userViewModel.currentUser.value?.let { currentUser ->
				val today = LocalDate.now()
				val lastFoodDate = currentUser.lastFoodEntryDate?.let { LocalDate.parse(it) }

				var newStreak = currentUser.calorieStreak ?: 0

				if (lastFoodDate == null || lastFoodDate.isBefore(today.minusDays(1))) {
					newStreak = 1
				} else if (lastFoodDate.isEqual(today.minusDays(1))) {
					newStreak++
				}

				val updatedUser = currentUser.copy(
					caloriesConsumedToday = currentUser.caloriesConsumedToday + food.energy100G,
					proteinConsumedToday = currentUser.proteinConsumedToday + food.proteinOneHundredG,
					lastFoodEntryDate = today.toString(),
					calorieStreak = newStreak
				)
				userViewModel.updateUser(updatedUser)
			}
		}    }

	fun addNewFood(food: Food) {
		viewModelScope.launch {
			foodRepo.insertFood(food)
		}
	}
}
```

### Meditation

- Meditation was a much simpler concept to implement as all I needed was a couple of pre-loaded youtube videos that are randomly called at daily and have the user watch one to completion which would then count to their streak for meditation
- I used the following API as a means of embedding youtube videos as the original android/google api has been deprecated for quite some time:
- https://github.com/PierfrancescoSoffritti/android-youtube-player

- To embed these videos I had to use their quickstart xml layout as a means of creating the components for video players

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
        android:id="@+id/youtube_player_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

- From here after creating my meditation video model,viewmodel and repository for these videos, the majority of the remaining work was in the view where I had to manage things like video state between the view and viewmodel in order to load the videos

```kotlin
    val meditationUiState: StateFlow<MeditationUiState> =
        repository.getAllMeditations().map { meditations ->
            MeditationUiState(meditations)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MeditationUiState()
        )}

data class MeditationUiState(
    val meditations: List<MeditationVideo> = emptyList()
)


// View

val uiState by meditationViewModel.meditationUiState.collectAsState()
val randomVideo by meditationViewModel.randomMeditationVideo.collectAsState()
```

- For a while between having the description title and video I was unable to get the dispay I wanted but after toying around with the max width and aspect ratio I got something clean like seen in the video

```kotlin
Column {
    if (videoId != null) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth() // Full width to include description too
                .aspectRatio(16f / 9f) // 16:9 aspect ratio so it fits in cards
                .clip(RoundedCornerShape(
                    topStart = 8.dp, topEnd = 8.dp
                )), // Clip top corners for visibility
            factory = { context ->
                YouTubePlayerView(context).apply { // Using youtube player from api
                    addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(videoId, 0f)
                        }                        override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                            if (state == PlayerConstants.PlayerState.ENDED) {
                                onMeditationComplete() // Once video completed, increase streak
                            }
                        }                    })                }
            }        )
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = meditation.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = meditation.description)
    }
```

- Overall I would've done this a lot better in terms of having a larger non-hardcoded bank of meditation videos but due to personal time constrains I was unable to deliver on that front

### Journal Entries

- Apart from clicking into entries for details and other small features included in the original assignment, my journal entries remained mostly the same apart from having the name be something you don't have to input yourself. Given journals are a daily occurrence and it would get quite hectic with multiple entries in one day I used the following code to limit users to one entry per day.

```kotlin
val currentUser = userRepository.getUserById(userId)
val todayDate = LocalDate.now()
val startOfDay = todayDate.atStartOfDay()
val endOfDay = todayDate.atTime(LocalTime.MAX)

val entriesFromToday = entryRepository.getEntriesForToday(userId, startOfDay, endOfDay)

if (entriesFromToday.isNotEmpty()) {
    println("Entry already done today")
    return false
}
```

### Jbcrypt

- Given my implementation of users, storing password as plain text would be ridiculously insecure so from previous experience in server side web dev I looked into if there was a Kotlin implementation and sure enough here we are

```kotlin
implementation(libs.jbcrypt)
```

- Then from here I simply implemented a hashing and checking function so that once users sign up or try to login all credentials are processed through one way encryption:

```kotlin
private fun hashPass(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun checkPassword(password: String, hashed: String): Boolean {
    return BCrypt.checkpw(password, hashed)
}
```

### Local data storage/Database work

- Initially my plan was to simply have a user install this application and all info ranging from their journal entries to their meditation logs to the food they have eaten. However when experimenting with room it made most sense for me to include users as a means of having specific entries be tracked by ids for certain users and having the last meditation or journal entry date be logged and stored too.

- As per our notes, I utilised room as the database as a means of user storage, entry storage, meditation entity storage and food storage. Although my food storage could've been done more optimally using an api, for convenience and offline usage this made more sense to me
- In terms of normalisation and data connections, the only real connection between our data here is between Users and entries as users have their own unique group of entries that are called via find by ids

#### Schemas

##### Entry

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### User

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### Food

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### MeditationVideos

```kotlin
@Entity(tableName = "meditation")
data class MeditationVideo(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val youtubeUrl: String,
	val description: String
)
```

## Functional Requirements Implementation

### Android health data

- As a means of integrating some form of api call, given theres a large amount of health statistics already involved I decided to give the users the ability to call the steps that their phone is already tracking for them. The following code calls steps in a sync method and saves it to the current user and will be logged when creating a journal entry:

```kotlin
implementation("androidx.health.connect:connect-client:1.1.0")


LaunchedEffect(Unit) {
	val granted = healthConnectClient.permissionController.getGrantedPermissions()
	if (granted.containsAll(permissions)) {
		readSteps(healthConnectClient) { stepCount ->
			steps = stepCount
		}
	}
}

suspend fun readSteps(healthConnectClient: HealthConnectClient, onStepsRead: (Long) -> Unit) {
	try {
		val end = LocalDateTime.now()
		val start = end.toLocalDate().atStartOfDay()
		val request = AggregateRequest(
			metrics = setOf(StepsRecord.COUNT_TOTAL),
			timeRangeFilter = TimeRangeFilter.between(start, end)
		)        val result = healthConnectClient.aggregate(request)
		onStepsRead(result[StepsRecord.COUNT_TOTAL] ?: 0L)
	} catch (e: Exception) {
		Log.d("HealthConnect", "Error reading steps", e)
	}}


	val permissionLauncher = rememberLauncherForActivityResult(
	contract = ActivityResultContracts.RequestMultiplePermissions(),
) { permissionsMap ->
	if (permissionsMap.values.all { it }) {
		coroutineScope.launch {
			readSteps(healthConnectClient) { stepCount ->
				steps = stepCount
				currentUser?.steps = steps.toDouble()
			}
		}    }
}
```

- In the home-screen view above we see that once a user clicks the sync button, once they have logged a certain amount of step sthroughout the day a coroutine will launch and they will be able to reload their total steps to be accurate compared to the current count of 0

### Calorie tacking

- Given a major part of this project was tracking and logging calories, expecting users to create an entire dataset/group of food items to track would be quite ridicoulous. As a result I took it upon myself to look into databases,APIs and datasets on food items. As seen below in the end i decided to go for a dataset (see https://www.kaggle.com/datasets/openfoodfacts/world-food-facts/data) as it made the most sense to me given the way I would also be having users have the option of creating their own food items,having these items and pre-loaded data have the same attributes made the most logical sense
- To achieve this using pandas as we have used it a lot in data analytics. I parsed this tsv as a csv file and converted this data into a json file only selecting the columns that were most important to me as the dataset also included 164 other columns.
- See full zipped folder for entire script (certain boilerplate omitted in below screenshot)

```python
	data = pd.read_csv(path, sep="\t", low_memory=False)
	selected_columns = [
		"product_name",
		"energy_100g",
		"fat_100g",
		"carbohydrates_100g",
		"sugars_100g",
		"fiber_100g",
		"proteins_100g",
	]
	subset = data.loc[idx : idx + 15000, selected_columns] # limit to 15,000 rows
	subset = subset.rename(
		columns={
			"product_name": "productName",
			"energy_100g": "energy100G",
			"fat_100g": "fatOneHundredG",
			"carbohydrates_100g": "carboHyOneHundredG",
			"sugars_100g": "sugarOneHundredG",
			"fiber_100g": "fiberOneHundredG",
			"proteins_100g": "proteinOneHundredG",
		}
	)
	json_output = subset.to_json(orient="records", indent=4)
```

- After getting this json file, one may think that holding and reading an entire json file into the database would be quite resource intensive however this json file upon running the command below:

```sh
du -hs res.json

3.0M    res.json
```

- We see it only occupies 3mb of space.

- Finally I wrote a quick class in the android application to load this data into the application database on first build:

```kotlin
suspend fun importDataFromJson() {
	withContext(Dispatchers.IO) {
		Log.d("Import","Importing data from JSON")
		val count = foodDao.getFoodCount()
		Log.d("Import","Init food count: $count")
		if (count > 0) {
			println("Database already contains $count food items. Skipping JSON import.")
			return@withContext
		}

		try {
			Log.d("Import","Importing data from JSON")
			val inputStream = context.assets.open(jsonFile)
			val reader = InputStreamReader(inputStream)
			Log.d("Import","File opened")

			val listType = object : TypeToken<List<Food>>() {}.type
			val foodEntities: List<Food> = Gson().fromJson(reader, listType)

			reader.close()
			Log.d("Import","${foodEntities.size} items from JSON.")

			if (foodEntities.isNotEmpty()) {
				foodDao.insertAll(foodEntities)
				Log.d("Import","Successfully imported ${foodEntities.size} food items.")
			}
		} catch (e: Exception) {
			Log.d("Import","Import failed")
			e.printStackTrace()
			Log.d("Import","Error importing data: ${e.message}")
		}    }
}
```

- From here I implemented a simple system where in the constructor depending on a persons gender, height and weight they would be given a caloric intake recommendation per day. I initially was going to have that be a part of the streak but to combat issues like pressure from eating disorders I decided against it
- This calculation uses the **Harris-Benedict Equation** as a means of calculating the recommended calories per day

```kotlin
fun calculateCaloriesPerDay(weight: Double, height: Double, age: Int, gender: String, loseWeight: String): Double {
	var res: Double = if (gender.lowercase() == "female") {
		10 * weight + 6.25 * height - 5 * age - 161
	} else {
		10 * weight + 6.25 * height - 5 * age + 5
	}

	if (loseWeight == "Y") {
		res -= 200
	} else {
		res += 200
	}
	return res
}
```

- Then in the food view page, after the food is loaded in via a lazycolumn that depends on cached paging via the ModelScope as a means of preventing overloaded memory given we have 15,000 rows of food to load in, users can hit add to the desired food by either searching up or creating their own food objects and from there they are able to see their calories consumed increase. This count resets daily.

```kotlin
val itemsFlow: Flow<PagingData<Food>> = combine(
	_calorieFilter,
	_searchQuery,
	_customFoodFilter
) { calorieFilter, searchQuery, customFoodFilter ->
	Triple(calorieFilter, searchQuery, customFoodFilter)
}.flatMapLatest { (calorieFilter, searchQuery, customFoodFilter) ->
	if (!searchQuery.isNullOrBlank()) {
		foodRepo.searchFoodByName(searchQuery)
	} else if (calorieFilter != null) {
		foodRepo.getFoodByCalories(calorieFilter)
	} else if (customFoodFilter != null) {
		foodRepo.getFoodByCustom(customFoodFilter)
	}    else {
		foodRepo.getAllFood()
	}}.cachedIn(viewModelScope)



	fun addFoodToUser(food: Food) {
		viewModelScope.launch {
			userViewModel.currentUser.value?.let { currentUser ->
				val today = LocalDate.now()
				val lastFoodDate = currentUser.lastFoodEntryDate?.let { LocalDate.parse(it) }

				var newStreak = currentUser.calorieStreak ?: 0

				if (lastFoodDate == null || lastFoodDate.isBefore(today.minusDays(1))) {
					newStreak = 1
				} else if (lastFoodDate.isEqual(today.minusDays(1))) {
					newStreak++
				}

				val updatedUser = currentUser.copy(
					caloriesConsumedToday = currentUser.caloriesConsumedToday + food.energy100G,
					proteinConsumedToday = currentUser.proteinConsumedToday + food.proteinOneHundredG,
					lastFoodEntryDate = today.toString(),
					calorieStreak = newStreak
				)
				userViewModel.updateUser(updatedUser)
			}
		}    }

	fun addNewFood(food: Food) {
		viewModelScope.launch {
			foodRepo.insertFood(food)
		}
	}
}
```

### Meditation

- Meditation was a much simpler concept to implement as all I needed was a couple of pre-loaded youtube videos that are randomly called at daily and have the user watch one to completion which would then count to their streak for meditation
- I used the following API as a means of embedding youtube videos as the original android/google api has been deprecated for quite some time:
- https://github.com/PierfrancescoSoffritti/android-youtube-player

- To embed these videos I had to use their quickstart xml layout as a means of creating the components for video players

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >

    <com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
        android:id="@+id/youtube_player_view"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>
</LinearLayout>
```

- From here after creating my meditation video model,viewmodel and repository for these videos, the majority of the remaining work was in the view where I had to manage things like video state between the view and viewmodel in order to load the videos

```kotlin
    val meditationUiState: StateFlow<MeditationUiState> =
        repository.getAllMeditations().map { meditations ->
            MeditationUiState(meditations)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MeditationUiState()
        )}

data class MeditationUiState(
    val meditations: List<MeditationVideo> = emptyList()
)


// View

val uiState by meditationViewModel.meditationUiState.collectAsState()
val randomVideo by meditationViewModel.randomMeditationVideo.collectAsState()
```

- For a while between having the description title and video I was unable to get the dispay I wanted but after toying around with the max width and aspect ratio I got something clean like seen in the video

```kotlin
Column {
    if (videoId != null) {
        AndroidView(
            modifier = Modifier
                .fillMaxWidth() // Full width to include description too
                .aspectRatio(16f / 9f) // 16:9 aspect ratio so it fits in cards
                .clip(RoundedCornerShape(
                    topStart = 8.dp, topEnd = 8.dp
                )), // Clip top corners for visibility
            factory = { context ->
                YouTubePlayerView(context).apply { // Using youtube player from api
                    addYouTubePlayerListener(object :
                        AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.cueVideo(videoId, 0f)
                        }                        override fun onStateChange(youTubePlayer: YouTubePlayer, state: PlayerConstants.PlayerState) {
                            if (state == PlayerConstants.PlayerState.ENDED) {
                                onMeditationComplete() // Once video completed, increase streak
                            }
                        }                    })                }
            }        )
    }
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = meditation.title,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = meditation.description)
    }
```

- Overall I would've done this a lot better in terms of having a larger non-hardcoded bank of meditation videos but due to personal time constrains I was unable to deliver on that front

### Journal Entries

- Apart from clicking into entries for details and other small features included in the original assignment, my journal entries remained mostly the same apart from having the name be something you don't have to input yourself. Given journals are a daily occurrence and it would get quite hectic with multiple entries in one day I used the following code to limit users to one entry per day.

```kotlin
val currentUser = userRepository.getUserById(userId)
val todayDate = LocalDate.now()
val startOfDay = todayDate.atStartOfDay()
val endOfDay = todayDate.atTime(LocalTime.MAX)

val entriesFromToday = entryRepository.getEntriesForToday(userId, startOfDay, endOfDay)

if (entriesFromToday.isNotEmpty()) {
    println("Entry already done today")
    return false
}
```

### Jbcrypt

- Given my implementation of users, storing password as plain text would be ridiculously insecure so from previous experience in server side web dev I looked into if there was a Kotlin implementation and sure enough here we are

```kotlin
implementation(libs.jbcrypt)
```

- Then from here I simply implemented a hashing and checking function so that once users sign up or try to login all credentials are processed through one way encryption:

```kotlin
private fun hashPass(password: String): String {
    return BCrypt.hashpw(password, BCrypt.gensalt())
}

fun checkPassword(password: String, hashed: String): Boolean {
    return BCrypt.checkpw(password, hashed)
}
```

### Local data storage/Database work

- Initially my plan was to simply have a user install this application and all info ranging from their journal entries to their meditation logs to the food they have eaten. However when experimenting with room it made most sense for me to include users as a means of having specific entries be tracked by ids for certain users and having the last meditation or journal entry date be logged and stored too.

- As per our notes, I utilised room as the database as a means of user storage, entry storage, meditation entity storage and food storage. Although my food storage could've been done more optimally using an api, for convenience and offline usage this made more sense to me
- In terms of normalisation and data connections, the only real connection between our data here is between Users and entries as users have their own unique group of entries that are called via find by ids

#### Schemas

##### Entry

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### User

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### Food

```kotlin
data class Entry(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0, // Unique identifier for each entry
	val userId: Long, // Id attached to user
	val text: String? = null, // Text entered by the user
	val name: String?, // User who entered the text
	val date: LocalDateTime?, // Date of entry
	val rating: Int? = null,  // Rating out of ten for the day
	val steps: Int? = null, // Steps taken for the day
	val calories: Double?  // Calories burned for the day
)
```

##### MeditationVideos

```kotlin
@Entity(tableName = "meditation")
data class MeditationVideo(
	@PrimaryKey(autoGenerate = true)
	val id: Int = 0,
	val title: String,
	val youtubeUrl: String,
	val description: String
)
```
