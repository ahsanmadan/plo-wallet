# Plo

Plo is a privacy-first Android money tracker for people who want their finances to feel clear, local, and fast.

It is built for everyday personal finance: logging transactions, checking budgets, reviewing reports, and keeping financial clutter low. The goal is simple: fewer taps, less noise, better awareness of where your money goes.

## Why Plo

- **Private by default**: built around local-first usage instead of cloud-heavy friction
- **Fast to use**: optimized for daily transaction logging, not spreadsheet cosplay
- **Actually useful**: accounts, budgets, reports, loans, and planned payments in one app
- **Modern Android stack**: Kotlin, Jetpack Compose, Room, Hilt

## Core features

- **Transaction tracking**: record income and expenses quickly
- **Accounts**: manage cash, bank, and other personal finance accounts
- **Budgets**: set spending limits and monitor what is left
- **Reports**: review income, expenses, and financial patterns
- **Loans**: track money owed and money lent
- **Planned payments**: keep upcoming or overdue payments visible
- **Multi-currency support**: work with multiple currencies
- **Dark mode**: use a clean interface designed for daily use

## Tech stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Database**: Room
- **Dependency injection**: Hilt
- **Build system**: Gradle

## Local development

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

## Project docs

- [Agent Instructions](AGENTS.md)
- [Design System](DESIGN.md)
- [GitHub Workflow](GITHUB_WORKFLOW.md)
- [License Notice](NOTICE.md)

## License

This project is distributed under the GPL-3.0 license. See [LICENSE](LICENSE) for the full license text and [NOTICE.md](NOTICE.md) for the project notice.

If you distribute Plo as an APK or another binary build, keep the source code available under GPL-3.0 and preserve the same license terms for recipients.
