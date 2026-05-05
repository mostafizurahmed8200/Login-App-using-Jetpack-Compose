Tech Stuck- **Room DB + Dagger Hilt + Jetpack Compose**
### Project Architecture: MVVM | Language: Kotlin | Pattern: Clean Architecture

---

## 📁 Final Project Structure

```
app/src/main/java/com/ahmed/login_jetpack_compose/
├── data/
│   ├── local/
│   │   ├── dao/
│   │   │   ├── LoginDao.kt
│   │   │   └── UserDataDao.kt
│   │   ├── entity/
│   │   │   ├── LoginEntity.kt
│   │   │   └── UserDataEntity.kt
│   │   └── AppDatabase.kt
│   ├── mapper/
│   │   └── Mappers.kt
│   └── repository/
│       ├── LoginRepositoryImpl.kt
│       └── UserDataRepositoryImpl.kt
├── di/
│   ├── DatabaseModule.kt
│   └── RepositoryModule.kt
├── domain/
│   ├── model/
│   │   ├── LoginModel.kt
│   │   └── UserDataModel.kt
│   ├── repository/
│   │   ├── LoginRepository.kt
│   │   └── UserDataRepository.kt
│   └── usecase/
│       ├── RegisterUseCase.kt
│       ├── LoginUseCase.kt
│       ├── ForgotPasswordUseCase.kt
│       ├── AddUserDataUseCase.kt
│       └── GetUserDataUseCase.kt
├── navigation/
│   ├── Screen.kt
│   └── AppNavGraph.kt
├── presentation/
│   ├── login/
│   │   ├── LoginScreen.kt
│   │   ├── LoginViewModel.kt
│   │   └── LoginUiState.kt
│   ├── forgotpassword/
│   │   ├── ForgotPasswordScreen.kt
│   │   ├── ForgotPasswordViewModel.kt
│   │   └── ForgotPasswordUiState.kt
│   └── userdata/
│       ├── UserDataScreen.kt
│       ├── UserDataViewModel.kt
│       └── UserDataUiState.kt
├── util/
│   └── ValidationUtils.kt
├── MyApplication.kt
└── MainActivity.kt

app/src/test/java/com/ahmed/login_jetpack_compose/
├── domain/usecase/
│   ├── RegisterUseCaseTest.kt
│   ├── LoginUseCaseTest.kt
│   ├── ForgotPasswordUseCaseTest.kt
│   └── AddUserDataUseCaseTest.kt
└── presentation/
    └── login/
        └── LoginViewModelTest.kt
```

---
