# Mimimi

App Android sin interfaz: añade "Mimimi" al menú de selección de texto, convierte las vocales en "i" y copia el resultado al portapapeles.

## Descargar el APK

**[⬇️ Descargar mimimi.apk](https://github.com/DaniBall/android-mimimi/releases/latest/download/mimimi.apk)** — se compila automáticamente con GitHub Actions y se publica en [Releases](https://github.com/DaniBall/android-mimimi/releases/latest).

📖 Guía de instalación y uso: [GUIA.md](GUIA.md)

## Compilar e instalar

Requisitos: JDK 17+ y Android SDK (`ANDROID_HOME` o `local.properties` con `sdk.dir=...`).

```bash
./gradlew assembleRelease
# APK: app/build/outputs/apk/release/app-release.apk
./gradlew installRelease   # o: adb install -r app/build/outputs/apk/release/app-release.apk
```

En Windows usa `gradlew.bat`. La app no tiene icono: se desinstala desde Ajustes → Aplicaciones.

## Probar sin seleccionar texto

```bash
adb shell am start -a android.intent.action.PROCESS_TEXT -t text/plain \
  --es android.intent.extra.PROCESS_TEXT "Hola, qué tal" \
  -n es.daniel.mimimi/.MimimiActivity
```

Para que además reemplace el texto en campos editables, pon `REPLACE_IN_EDITABLE = true` en `MimimiActivity.kt`.

## Licencia

MIT. Ver [LICENSE](LICENSE).
