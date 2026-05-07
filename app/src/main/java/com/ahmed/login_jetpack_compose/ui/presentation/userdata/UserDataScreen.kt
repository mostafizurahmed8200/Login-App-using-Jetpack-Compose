package com.ahmed.login_jetpack_compose.ui.presentation.userdata

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahmed.login_jetpack_compose.domain.model.UserDataModel
import com.ahmed.login_jetpack_compose.ui.theme.Spacer


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun UserDataScreen(
    viewModel: UserDataViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("User Data") })
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        )
        {
            listItems(viewModel, uiState)
            // ---- List Header ----
            if (uiState.userDataList.isNotEmpty()) {
                item {
                    Text(
                        text = "Added Users (${uiState.userDataList.size})",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

            // ---- User Cards ----
            items(
                items = uiState.userDataList,
                key = { it.id }   // Stable keys for efficient recomposition
            ) { userData ->
                UserDataCard(userData = userData)
            }
        }
    }

}


private fun LazyListScope.listItems(viewModel: UserDataViewModel, uiState: UserDataUiState) {
    val genderOptions = listOf("Male", "Female", "Others")

    item {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Add User Details",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                OutlinedTextField(
                    value = uiState.name,
                    onValueChange = { viewModel.onNameChange(it) },
                    label = { Text("Name") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()

                )
                OutlinedTextField(
                    value = uiState.occupation,
                    onValueChange = { viewModel.onOccupationChange(it) },
                    singleLine = true,
                    label = { Text("Occupation") },
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = uiState.age,
                    onValueChange = { viewModel.onAgeChange(it) },
                    label = { Text("Age") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    )
                )
                Text(
                    text = "Gender", fontSize = 14.sp, fontWeight = FontWeight.Medium
                )
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    genderOptions.forEach { option ->
                        FilterChip(selected = uiState.gender == option, onClick = {
                            viewModel.onGenderChange(option)
                        }, label = { Text(option) })
                    }
                }

                if (uiState.errorMessage != null) {
                    Text(
                        text = uiState.errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontSize = 12.sp
                    )
                }

                //Loading check---
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    Button(
                        onClick = { viewModel.onAddUserData() },
                        modifier = Modifier.fillMaxWidth(),
                        contentPadding = PaddingValues(vertical = 12.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null)
                        Spacer(8.dp)
                        Text("Add", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}

@Composable
private fun UserDataCard(userData: UserDataModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = userData.name, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                UserDataField(label = "Age", value = userData.age.toString())
                UserDataField(label = "Gender", value = userData.gender)
            }
            UserDataField(label = "Occupation", value = userData.occupation)
        }
    }
}

@Composable
private fun UserDataField(label: String, value: String) {
    Row {
        Text(
            text = "$label: ",
            fontSize = 13.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(text = value, fontSize = 13.sp, fontWeight = FontWeight.Medium)
    }
}










