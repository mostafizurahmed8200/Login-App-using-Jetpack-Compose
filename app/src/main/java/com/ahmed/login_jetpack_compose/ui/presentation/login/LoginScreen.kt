package com.ahmed.login_jetpack_compose.ui.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ahmed.login_jetpack_compose.R
import com.ahmed.login_jetpack_compose.ui.theme.Dimen
import com.ahmed.login_jetpack_compose.ui.theme.Spacer
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onNavigateToUserData: (String) -> Unit,
    onNavigateToForgetPassword: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(
        Unit
    ) {
        viewModel.events.collectLatest { event ->
            when (event) {
                is LoginEvent.NavigateToUserData -> onNavigateToUserData(event.userId)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Dimen.PaddingMedium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.welcome),
            fontSize = Dimen.FontSizeBig,
            fontWeight = FontWeight.Bold
        )
        Spacer(8.dp)
        Text(
            text = stringResource(R.string.create_account),
            fontSize = Dimen.FontSizeSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Dimen.SpacerSize)
        OutlinedTextField(
            value = uiState.email,
            onValueChange = viewModel::onEmailChange,
            label = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(16.dp)

        OutlinedTextField(
            value = uiState.password,
            onValueChange = viewModel::onPasswordChange,
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            trailingIcon = {
                IconButton(onClick = viewModel::onTogglePasswordVisibility) {
                    Icon(
                        imageVector = if (uiState.isPasswordVisible)
                            Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = "Toggle password visibility"
                    )
                }
            },
            visualTransformation = if (uiState.isPasswordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(4.dp)
        if (uiState.errorMessage != null) {
            Text(
                text = uiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                fontSize = Dimen.FontSizeSmall,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(
                        start = 4.dp
                    )
            )
        }

        TextButton(
            onClick = onNavigateToForgetPassword,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(stringResource(R.string.forget_password))
        }
        Spacer(8.dp)

        if (uiState.isLoading) {
            CircularProgressIndicator(color = Color.Red)
        } else {
            Button(
                onClick = viewModel::onLoginClick,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = Dimen.PaddingSmall)
            ) {
                Text(stringResource(R.string.login), fontSize = 16.sp)
            }
        }
        Spacer(12.dp)
        OutlinedButton(
            onClick = viewModel::onRegisterClick,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 14.dp)
        ) {
            Text(stringResource(R.string.register), fontSize = 16.sp)
        }

    }


}

