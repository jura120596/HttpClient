Нужно добавить строку в файл Build.gradle (cм в модуле 5.3)
    `useLibrary 'org.apache.http.legacy'`
Выполнить сверху слева: File->Sync Project With Gradle Files

И добавить в AndroidManifest
    `<uses-permission android:name="android.permission.INTERNET"/>`