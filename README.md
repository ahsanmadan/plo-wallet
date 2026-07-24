# Plo

**Plo** is a personal finance Android app for tracking daily transactions, accounts, budgets, reports, loans, and planned payments.

The goal of Plo is simple: make personal money tracking feel fast, private, and clear without unnecessary distractions.

## Features

- **Transaction Tracking**: Record income and expenses quickly.
- **Accounts**: Manage cash, bank, and other personal finance accounts.
- **Budgets**: Set spending limits and monitor remaining budget.
- **Reports**: Review income, expenses, and financial patterns.
- **Loans**: Track money owed and money lent.
- **Planned Payments**: Keep upcoming or overdue payments visible.
- **Multi-currency Support**: Work with multiple currencies.
- **Dark Mode**: Use a clean interface designed for daily use.

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM and Clean Architecture
- **Database**: Room Database
- **Dependency Injection**: Hilt
- **Build System**: Gradle

## Local Development

1. Install Android Studio.
2. Clone this repository:

```bash
git clone https://github.com/ahsanmadan/plo-wallet.git
```

3. Open the project in Android Studio.
4. Wait for Gradle sync to finish.
5. Run the `app` configuration on an emulator or Android device.

For command-line builds on Windows, use JDK 17:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-17'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\gradlew.bat :app:assembleDebug --no-daemon --stacktrace
```

## Project Docs

- [Agent Instructions](AGENTS.md)
- [Design System](DESIGN.md)
- [GitHub Workflow](GITHUB_WORKFLOW.md)
- [License Notice](NOTICE.md)

## License

This project is distributed under the GPL-3.0 license. See [LICENSE](LICENSE) for the full license text and [NOTICE.md](NOTICE.md) for the project notice.

If you distribute Plo as an APK or another binary build, keep the source code available under GPL-3.0 and preserve the same license terms for recipients.
