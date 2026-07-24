# Contributing to Plo

Thanks for helping improve Plo. This project uses a simple, professional GitHub workflow so every change is easy to review, test, and ship.

## Before You Start

Read these files first:

- [AGENTS.md](AGENTS.md)
- [DESIGN.md](DESIGN.md)
- [GITHUB_WORKFLOW.md](GITHUB_WORKFLOW.md)

Important project boundaries:

- Keep the user-facing brand as **Plo**.
- Do not rename the Android package, Firebase identifiers, Room database, migrations, or import/export schema unless the change is explicitly approved.
- Keep changes small and focused.
- Build and test the affected flow before opening or merging a pull request.

## Branch Naming

Use a branch name that describes the work:

```bash
git checkout -b feature/short-feature-name
git checkout -b fix/short-bug-name
git checkout -b chore/short-maintenance-task
git checkout -b docs/short-doc-task
```

## Commit Messages

Use concise conventional commits:

```text
feat(settings): add privacy screen
fix(home): preserve scroll position
docs: update project workflow
chore(branding): clean public repository docs
```

## Development Flow

1. Create a focused branch.
2. Make the smallest useful change.
3. Run `git diff --check`.
4. Run the relevant Gradle command.
5. Manually verify changed UI flows on a device when needed.
6. Commit with a clear message.
7. Push the branch.
8. Open a Pull Request with a summary and test plan.

## Build Command

On Windows, use JDK 17:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-17'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\gradlew.bat :app:assembleDebug --no-daemon --stacktrace
```

## Pull Request Checklist

- [ ] The change is scoped and easy to review.
- [ ] User-facing copy says Plo.
- [ ] Package/Firebase/database identifiers are unchanged unless explicitly approved.
- [ ] `git diff --check` passes.
- [ ] Debug build passes when code changes are included.
- [ ] Manual QA is listed for UI or behavior changes.

## Questions

For planning, design, or architecture decisions, document the decision in the Pull Request or update the relevant local project docs.
