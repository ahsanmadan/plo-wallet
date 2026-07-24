# GitHub Workflow - Plo

Dokumen ini menjadi alur kerja resmi untuk commit, bug fix, fitur baru, review, dan push di project Plo.

## 1. Branch Strategy

Gunakan `main` sebagai branch stabil. Jangan langsung kerja besar di `main` kecuali perubahan sangat kecil dan diminta eksplisit.

Format branch:

- `feature/nama-fitur` untuk fitur baru.
- `fix/nama-bug` untuk bug fix.
- `chore/nama-pekerjaan` untuk cleanup, docs, dependency, atau task non-user-facing.
- `refactor/nama-area` untuk perapian struktur tanpa perubahan behavior.
- `release/versi` untuk persiapan rilis.

Contoh:

```bash
git checkout -b feature/settings-legal-pages
git checkout -b fix/settings-scroll-position
git checkout -b chore/plo-branding-cleanup
```

## 2. Commit Rules

Commit harus kecil, jelas, dan satu tujuan. Jangan campur fitur baru, bug fix, dan cleanup besar dalam satu commit kecuali memang satu paket pekerjaan.

Format commit:

```text
type(scope): short summary
```

Type yang dipakai:

- `feat`: fitur baru.
- `fix`: perbaikan bug.
- `docs`: dokumentasi.
- `style`: formatting atau visual polish tanpa logic besar.
- `refactor`: perubahan struktur tanpa perubahan behavior.
- `test`: test baru atau perbaikan test.
- `chore`: maintenance, config, dependency, cleanup.

Contoh:

```text
feat(settings): add local privacy policy screen
fix(settings): preserve scroll position after back navigation
docs: add GitHub workflow guide
chore(branding): remove Ivy promotional cards
```

## 3. Workflows By Task Type

### Bug Fix

1. Reproduce bug dari app/repo nyata.
2. Buat branch `fix/...`.
3. Cari root cause, bukan hanya gejala UI.
4. Patch minimal sesuai area bug.
5. Jalankan build/test yang relevan.
6. Commit dengan `fix(scope): ...`.
7. Push branch dan buat Pull Request.

Checklist bug fix:

- [ ] Bug berhasil direproduksi atau alasan tidak bisa direproduksi dicatat.
- [ ] Fix tidak mengubah behavior lain tanpa sengaja.
- [ ] Build debug sukses.
- [ ] Manual QA sesuai bug sudah dilakukan.

### New Feature

1. Pastikan fitur disetujui dan scope jelas.
2. Buat branch `feature/...`.
3. Baca `AGENTS.md` dan `DESIGN.md`.
4. Implementasi bertahap: state/domain dulu, UI, navigation, lalu polish.
5. Jangan ubah package, Firebase, Room schema, atau migration kecuali memang disetujui.
6. Tambah preview/test jika relevan.
7. Build dan manual QA.
8. Commit dengan `feat(scope): ...`.
9. Push branch dan buat Pull Request.

Checklist fitur:

- [ ] Scope fitur tidak melebar.
- [ ] UI mengikuti `DESIGN.md`.
- [ ] Empty/loading/error state dipikirkan.
- [ ] Core flow lama tidak rusak.
- [ ] Build debug sukses.

### UI Polish

1. Ambil screenshot atau referensi masalah.
2. Pastikan ini masalah layout/visual, bukan logic.
3. Buat branch `style/...` atau `fix/...`.
4. Patch spacing, typography, hierarchy, atau overflow secara targeted.
5. Build, lalu validasi di device/screenshot bila memungkinkan.

Checklist UI:

- [ ] Tidak ada text overflow.
- [ ] Tidak ada overlap.
- [ ] Dark mode tetap rapi.
- [ ] Komponen konsisten dengan style existing.

### Documentation / Cleanup

1. Buat branch `docs/...` atau `chore/...`.
2. Update hanya dokumen/config/cleanup yang relevan.
3. Jangan hapus kode yang masih dipakai tanpa verifikasi search/build.
4. Commit dengan `docs:` atau `chore:`.

## 4. Pull Request Rules

Judul PR:

```text
[type] Short user-facing summary
```

Contoh:

```text
[fix] Preserve Settings scroll after opening legal pages
[feat] Add local Plo contributors screen
```

Isi PR wajib memuat:

```markdown
## Summary
- What changed
- Why it changed

## Test Plan
- [ ] `.\gradlew.bat :app:assembleDebug --no-daemon --stacktrace`
- [ ] Manual QA on device, if UI/user flow changed

## Notes
- Package/Firebase/Room schema unchanged, if relevant
```

## 5. Push Rules

Sebelum push:

```bash
git status --short --branch
git diff --check
```

Untuk Android build:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk-17'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
.\gradlew.bat :app:assembleDebug --no-daemon --stacktrace
```

Push branch:

```bash
git push -u origin feature/nama-fitur
```

Push ke `main` hanya boleh jika user memang meminta push langsung atau perubahan sudah disetujui untuk langsung masuk main.

## 6. Release Flow

1. Pastikan `main` clean dan build sukses.
2. Buat branch `release/yyyy.mm.dd` atau `release/vX.Y.Z`.
3. Update version name/code jika memang bagian dari rilis.
4. Build APK/AAB release sesuai kebutuhan.
5. Buat tag:

```bash
git tag -a vX.Y.Z -m "Release vX.Y.Z"
git push origin vX.Y.Z
```

6. Buat GitHub Release dengan changelog singkat.

## 7. Plo Project Guardrails

- App label dan user-facing copy memakai `Plo`.
- Package tetap `com.ivy.wallet` dan debug tetap `com.ivy.wallet.debug`.
- Firebase config tidak diubah tanpa akses dan persetujuan.
- Room database, entity, migration, import/export schema tidak diubah untuk rebranding.
- Logo/icon belum diganti kecuali desain final sudah tersedia.
- Install ke HP hanya dilakukan saat user eksplisit meminta `install`.
