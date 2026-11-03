<div align="center">

# 🐭 Mimimi

**Convierte cualquier texto en "mimimi" desde el menú de selección de Android.**

`Hola, ¿qué tal estás?` → `Hili, ¿quí til istís?`

[![Build APK](https://github.com/DaniBall/android-mimimi/actions/workflows/build.yml/badge.svg)](https://github.com/DaniBall/android-mimimi/actions/workflows/build.yml)
![Android](https://img.shields.io/badge/Android-6.0%2B-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-2.1-7F52FF?logo=kotlin&logoColor=white)
[![License](https://img.shields.io/github/license/DaniBall/android-mimimi)](LICENSE)

### [⬇️ Descargar mimimi.apk](https://github.com/DaniBall/android-mimimi/releases/latest/download/mimimi.apk)

</div>

---

## ✨ Qué es

Mimimi es una app Android **sin interfaz y sin icono**. Se integra en el menú contextual que aparece al seleccionar texto en cualquier app (WhatsApp, Telegram, Chrome, Notas…). Al pulsar **Mimimi**, el texto seleccionado se transforma y se **copia al portapapeles** listo para pegar.

- 🪶 Ligerísima: sin permisos, sin internet, sin anuncios.
- 🔤 Respeta mayúsculas y tildes.
- 📋 Copia el resultado al portapapeles al instante.

## 📲 Instalación rápida

1. Abre en el móvil la [última Release](https://github.com/DaniBall/android-mimimi/releases/latest) y descarga `mimimi.apk`.
2. Ábrelo y, si te lo pide, permite **instalar apps desconocidas** para tu navegador.
3. Pulsa **Instalar**.

📖 Guía detallada paso a paso, uso y solución de problemas: **[GUIA.md](GUIA.md)**

## 🧑‍💻 Cómo se usa

1. Mantén pulsado un texto para seleccionarlo.
2. En el menú flotante pulsa **⋮** → **Mimimi**.
3. Pega el resultado donde quieras.

## 🔄 Reglas de transformación

| Entrada | Salida |
|---|---|
| `a e i o u` | `i` |
| `A E I O U` | `I` |
| `á é í ó ú` | `í` |
| `Á É Í Ó Ú` | `Í` |
| `ü à ê…` | `i` / `I` |
| Resto de caracteres | sin cambios |

## 🛠️ Compilar desde el código

Requisitos: **JDK 17+** y **Android SDK** (`ANDROID_HOME` o `local.properties` con `sdk.dir=...`).

```bash
./gradlew assembleRelease
```

El APK queda en `app/build/outputs/apk/release/app-release.apk`. Para instalarlo con el móvil conectado por USB:

```bash
adb install -r app/build/outputs/apk/release/app-release.apk
```

En Windows usa `gradlew.bat`.

### Probar sin seleccionar texto

```bash
adb shell am start -a android.intent.action.PROCESS_TEXT -t text/plain --es android.intent.extra.PROCESS_TEXT "Hola, qué tal" -n es.daniel.mimimi/.MimimiActivity
```

### Reemplazar el texto en campos editables

Pon `REPLACE_IN_EDITABLE = true` en [`MimimiActivity.kt`](app/src/main/java/es/daniel/mimimi/MimimiActivity.kt) para que, además de copiar, sustituya la selección en campos de texto editables.

## 🤖 Integración continua

Cada push compila el APK con [GitHub Actions](.github/workflows/build.yml) y lo publica automáticamente en la Release [`latest`](https://github.com/DaniBall/android-mimimi/releases/latest).

## 📁 Estructura

```
app/src/main/
├── AndroidManifest.xml          # Registra la acción PROCESS_TEXT (sin launcher)
├── java/es/daniel/mimimi/
│   ├── Mimimi.kt                # Lógica de transformación de vocales
│   └── MimimiActivity.kt        # Recibe el texto, lo transforma y lo copia
└── res/values/strings.xml
```

## 📄 Licencia

Distribuido bajo la licencia incluida en [LICENSE](LICENSE).
