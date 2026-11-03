package es.daniel.mimimi

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast

/**
 * Puntos de entrada:
 *  - PROCESS_TEXT: opción "Mimimi" del menú de selección de texto.
 *  - SEND: "Compartir" → Mimimi desde cualquier app (WhatsApp, Claude…).
 *  - ACTION_CLIPBOARD: transforma el contenido actual del portapapeles (botón de ajustes rápidos).
 */
class MimimiActivity : Activity() {

    companion object {
        /** Si es true y el campo es editable, además de copiar se reemplaza la selección. */
        const val REPLACE_IN_EDITABLE = false
        const val ACTION_CLIPBOARD = "es.daniel.mimimi.CLIPBOARD"
    }

    private val clipboard by lazy { getSystemService(ClipboardManager::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (intent?.action) {
            Intent.ACTION_PROCESS_TEXT -> {
                val text = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
                val result = process(text) ?: return finish()
                val readOnly = intent.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)
                if (REPLACE_IN_EDITABLE && !readOnly) {
                    setResult(RESULT_OK, Intent().putExtra(Intent.EXTRA_PROCESS_TEXT, result))
                }
                finish()
            }
            Intent.ACTION_SEND -> {
                process(intent.getCharSequenceExtra(Intent.EXTRA_TEXT))
                finish()
            }
            ACTION_CLIPBOARD -> Unit // se procesa al obtener el foco (Android 10+ lo exige para leer el portapapeles)
            else -> finish()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus || intent?.action != ACTION_CLIPBOARD || isFinishing) return
        val text = clipboard.primaryClip?.takeIf { it.itemCount > 0 }?.getItemAt(0)?.coerceToText(this)
        if (process(text) == null) {
            Toast.makeText(applicationContext, "El portapapeles está vacío", Toast.LENGTH_SHORT).show()
        }
        finish()
    }

    /** Transforma, copia al portapapeles y avisa. Devuelve null si no hay texto. */
    private fun process(text: CharSequence?): String? {
        if (text.isNullOrEmpty()) return null
        val result = Mimimi.transform(text)
        clipboard.setPrimaryClip(ClipData.newPlainText("mimimi", result))
        // En Android 13+ el sistema ya muestra su propia confirmación de copiado
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            val preview = if (result.length > 60) result.take(60) + "…" else result
            Toast.makeText(applicationContext, "Copiado: $preview", Toast.LENGTH_SHORT).show()
        }
        return result
    }
}
