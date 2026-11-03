package es.daniel.mimimi

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast

class MimimiActivity : Activity() {

    companion object {
        /** Si es true y el campo es editable, además de copiar se reemplaza la selección. */
        const val REPLACE_IN_EDITABLE = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val original = intent?.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT)
        if (original.isNullOrEmpty()) {
            finish()
            return
        }
        val readOnly = intent.getBooleanExtra(Intent.EXTRA_PROCESS_TEXT_READONLY, false)
        val result = Mimimi.transform(original)

        val clipboard = getSystemService(ClipboardManager::class.java)
        clipboard.setPrimaryClip(ClipData.newPlainText("mimimi", result))

        // En Android 13+ el sistema ya muestra su propia confirmación de copiado
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2) {
            val preview = if (result.length > 60) result.take(60) + "…" else result
            Toast.makeText(applicationContext, "Copiado: $preview", Toast.LENGTH_SHORT).show()
        }

        if (REPLACE_IN_EDITABLE && !readOnly) {
            setResult(RESULT_OK, Intent().putExtra(Intent.EXTRA_PROCESS_TEXT, result))
        }

        finish()
    }
}
