package com.example.assignment1.Views

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.assignment1.ViewModels.EntryViewModel

@Composable
fun ListOfEntries(viewModel: EntryViewModel = viewModel() ){
    val listOfEntries = viewModel.entries

    LazyColumn {
        items(listOfEntries){

        }
    }

}