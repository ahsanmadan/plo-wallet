# AGENTS.md - Project Context & Rules

## 1. Project Overview & Tech Stack
- **Project Name:** Plo App
- **Description:** Aplikasi Android pencatat keuangan pribadi untuk memantau transaksi, akun, anggaran, laporan, dan pengaturan finansial harian. Project ini merupakan fork dari Ivy Wallet yang sedang di-branding ulang dan disesuaikan menjadi Plo.
- **Target Audience:** Pengguna Android yang ingin mengelola keuangan pribadi secara lokal, sederhana, cepat, dan privat.

### Tech Stack Approved:
- **Language:** Kotlin (Latest Stable yang kompatibel dengan project)
- **UI Framework:** Jetpack Compose (Compose UI)
- **Architecture:** MVVM (Model-View-ViewModel) dengan Clean Architecture (Data, Domain, Presentation)
- **Local DB:** Room Database
- **Network:** Ktor Client untuk network layer yang sudah ada; Retrofit boleh dipakai hanya jika benar-benar dibutuhkan dan disetujui.
- **DI Framework:** Hilt

---

## 2. Build, Test, and Run Commands
AI Agent must use these commands when executing terminal actions or writing automation scripts:
- **Clean Project:** `./gradlew clean`
- **Build Project:** `./gradlew :app:assembleDebug`
- **Run Unit Tests:** `./gradlew testDebugUnitTest`
- **Run Instrumented Tests:** `./gradlew connectedDebugAndroidTest`
- **Check Code Style:** `./gradlew detekt`
- **Install Debug APK to Device:** Build first, then uninstall `com.ivy.wallet.debug`, then install `app/build/outputs/apk/debug/app-debug.apk`.

Windows / local machine notes:
- Use JDK 17 for Gradle commands: set `JAVA_HOME=C:\Program Files\Java\jdk-17`.
- Use ADB from `C:\Users\ahsan\AppData\Local\Android\Sdk\platform-tools\adb.exe` if `adb` is not in PATH.

---

## 3. Code Style Guidelines and Rules
Every time you generate or refactor code, you must strictly follow these structural and architectural rules:
- **Read Local Docs First:** Before UI work, read local `DESIGN.md` and follow its visual identity, tokens, component rules, and constraints.
- **State Management:** Prefer `MutableStateFlow` in ViewModels and collect them as lifecycle-aware state in Compose screens where the module supports it.
- **Component Splitting:** Keep Composable functions small and modular. Break down complex layouts into smaller sub-composables inside the same file or a `components` sub-folder.
- **Previews:** Every new Composable screen and reusable component should include a `@Preview` function. Include Light and Dark previews when practical and consistent with existing module patterns.
- **Dependency Injection:** Inject repositories and domain dependencies into ViewModels using Hilt `@Inject` constructor injection. Never instantiate dependencies manually inside ViewModels.
- **Error Handling:** Wrap network and database operations in established project result/error patterns. Handle UI exceptions gracefully using Snackbar or visual error states that match `DESIGN.md`.
- **Scope Control:** Keep changes minimal and targeted. Do not rename package/applicationId because Firebase config is tied to `com.ivy.wallet` and `com.ivy.wallet.debug`.
- **Branding Rule:** User-facing app branding should say `Plo` or the currently requested product name. Avoid introducing new Ivy Wallet promotional cards, links, or copy unless explicitly requested.
- **Device Install Workflow:** Whenever the user asks to install to the phone, uninstall the existing debug package first, then install the newly built APK.

---

## 4. Development Roadmap / Tasks
This is the master checklist for the AI. Mark `[x]` for completed tasks.

### Phase 1: Foundation & Setup
- [x] Task 1: Setup baseline project structure with Hilt and Room DB config.
- [ ] Task 2: Implement Base App Theme utilizing design tokens from local `DESIGN.md`.
- [x] Task 3: Create initial navigation graph using Compose Navigation / existing project navigation.

### Phase 2: Core Features
- [ ] Task 4: Build Plo Dashboard Screen refinement.
- [ ] Task 5: Build Transaction Creation / Editing flow refinement.
- [ ] Task 6: Integrate and verify local database storage for persistence.

### Phase 3: Polish & Deployment
- [ ] Task 7: Apply full Linear-inspired animations and micro-interactions.
- [ ] Task 8: Run full UI validation using screenshot/device checks where applicable.
- [ ] Task 9: Trigger `yeet` skill to publish final release branch to GitHub when explicitly requested.

---

## 5. GitHub Management Workflow
- Follow the project workflow in `GITHUB_WORKFLOW.md` for branch naming, commits, Pull Requests, releases, and push rules.
- Default branch is `main`; prefer feature/fix branches for non-trivial work.
- Use concise conventional commits such as `feat(settings): add legal screens` or `fix(home): preserve scroll position`.
- Before push, run `git status --short --branch`, `git diff --check`, and the relevant Gradle build/test command.
- Push directly to `main` only when the user explicitly asks for it or the change is already approved for direct publish.
