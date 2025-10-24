# Responsi-Pemrograman-Mobile

Simon Dimas Pramudya
H1D023104
Shift D
Shift Asal G

---

Video Demo Aplikasi

![gif1](https://github.com/Grizzlishe78/Responsi-Pemrograman-Mobile/blob/main/app/assets/docs/Clip.gif)

---

Pertama mulai dari pemanggilan API atau pembacaan file JSON lokal (team.json yang diambil dari kode team di postman) oleh MainActivity, yang menggunakan Gson untuk mengubah data mentah dalam format JSON menjadi objek Kotlin (TeamResponse) berisi informasi tim, pelatih (Coach), dan daftar pemain (SquadMember). Data objek ini kemudian dikirim ke halaman lain seperti CoachActivity, SquadActivity, atau HistoryActivity melalui Intent dalam bentuk JSON string. Di halaman SquadActivity, daftar pemain ditampilkan menggunakan RecyclerView dengan bantuan PlayerAdapter, yang mengatur tampilan setiap pemain. Ketika salah satu pemain diklik, data pemain tersebut dikirim ke BottomSheetDialogFragment (fragment detail pemain), yang kemudian menampilkan informasi rinci seperti nama, posisi, kewarganegaraan, dan tanggal lahir dalam tampilan yang muncul dari bawah layar. Seluruh proses ini membuat data dari JSON bisa diubah menjadi tampilan interaktif dan terstruktur di layar pengguna.
