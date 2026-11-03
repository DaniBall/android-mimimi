# Guía de usuario de Mimimi

Mimimi añade la opción **"Mimimi"** al menú que aparece al seleccionar texto en Android. Convierte todas las vocales en "i" (respetando mayúsculas y tildes) y copia el resultado al portapapeles.

> Ejemplo: `Hola, ¿qué tal estás?` → `Hili, ¿quí til istís?`

## 1. Descargar el APK desde GitHub

1. Desde el móvil, abre: **https://github.com/DaniBall/android-mimimi/releases/latest**
2. En **Assets**, pulsa **`mimimi.apk`** para descargarlo.

El APK se compila automáticamente con GitHub Actions en cada push y se publica en esa Release.

## 2. Instalar en Android

1. Abre el archivo `mimimi.apk` descargado (desde la notificación o la app *Archivos* → *Descargas*).
2. Si Android lo bloquea, pulsa **Ajustes** y activa **"Permitir de esta fuente"** para tu navegador/gestor de archivos (Ajustes → Aplicaciones → Acceso especial → Instalar apps desconocidas).
3. Vuelve atrás y pulsa **Instalar**.
4. Si Google Play Protect avisa de una "app desconocida", pulsa **Más detalles → Instalar de todas formas** (la app no está en Play Store, por eso avisa).

Requiere Android 6.0 o superior.

> ⚠️ La app **no tiene icono** en el cajón de aplicaciones: es normal. Solo aparece en el menú de selección de texto.

## 3. Cómo usarla

1. En cualquier app (WhatsApp, Chrome, Notas…), **mantén pulsado** sobre un texto para seleccionarlo.
2. En el menú flotante (Copiar, Compartir…) pulsa **⋮** (más opciones) si no ves la opción directamente.
3. Pulsa **Mimimi**.
4. El texto transformado queda **copiado en el portapapeles**. Pégalo donde quieras.

Reglas de transformación:
- `a e i o u` → `i`  ·  `A E I O U` → `I`
- Vocales con tilde aguda (`á é í ó ú`) → `í` / `Í`
- Otras vocales acentuadas (`ü`, `à`…) → `i` / `I`
- El resto de caracteres no cambian.

## 4. Actualizar y desinstalar

- **Actualizar:** descarga el nuevo `mimimi.apk` de la Release y vuelve a instalarlo encima.
- **Desinstalar:** Ajustes → Aplicaciones → **Mimimi** → Desinstalar.

## Problemas frecuentes

| Problema | Solución |
|---|---|
| No aparece "Mimimi" en el menú | Pulsa ⋮ en el menú de selección; algunas apps (p. ej. ciertos teclados o apps bancarias) no admiten acciones de texto. |
| "App no instalada" | Desinstala la versión anterior y vuelve a instalar. |
| No encuentro la app | No tiene icono; búscala en Ajustes → Aplicaciones. |
