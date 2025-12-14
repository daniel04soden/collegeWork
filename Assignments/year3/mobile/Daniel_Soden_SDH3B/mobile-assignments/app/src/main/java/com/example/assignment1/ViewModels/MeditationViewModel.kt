package com.example.assignment1.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Data.Repository.MeditationRepository
import com.example.assignment1.Models.MeditationVideo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class MeditationViewModel(
    private val userViewModel: UserViewModel,
    private val repository: MeditationRepository
) : ViewModel() {

    private val _randomMeditationVideo = MutableStateFlow<MeditationVideo?>(null)
    val randomMeditationVideo: StateFlow<MeditationVideo?> = _randomMeditationVideo.asStateFlow()

    init {
        viewModelScope.launch {
            _randomMeditationVideo.value = repository.getRandomMeditation().firstOrNull()
        }
    }

    fun onMeditationComplete() {
        viewModelScope.launch {
            val currentUser = userViewModel.currentUser.value
            if (currentUser != null) {
                val today = LocalDate.now()
                val lastMeditationDate = currentUser.lastMeditationEntryDate?.let { LocalDate.parse(it) }

                var newStreak = currentUser.meditationStreak ?: 0

                if (lastMeditationDate == null || lastMeditationDate.isBefore(today.minusDays(1))) {
                    newStreak = 1
                } else if (lastMeditationDate.isEqual(today.minusDays(1))) {
                    newStreak++
                }
                val updatedUser = currentUser.copy(
                    meditationStreak = newStreak,
                    lastMeditationEntryDate = today.toString()
                )
                userViewModel.updateUser(updatedUser)
            }
        }
    }

    val meditationUiState: StateFlow<MeditationUiState> =
        repository.getAllMeditations().map { meditations ->
            MeditationUiState(meditations)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MeditationUiState()
        )
}

data class MeditationUiState(
    val meditations: List<MeditationVideo> = emptyList()
)
