package es.daniel.mimimi

import android.app.Activity
import android.app.StatusBarManager
import android.content.ComponentName
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

/**
 * Pantalla de inicio. Abrirla una vez saca la app del "estado detenido" de Android,
 * necesario para que el botón de ajustes rápidos aparezca en la lista.
 */
class SetupActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pad = (24 * resources.displayMetrics.density).toInt()
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(pad, pad, pad, pad)
        }
        root.addView(TextView(this).apply {
            textSize = 16f
            text = """
                Mimimi está listo.

                • Selecciona texto en cualquier app → ⋮ → Mimimi.
                • En WhatsApp, Claude y apps sin selección de texto: copia el mensaje y pulsa el botón «Mimimi portapapeles» de los ajustes rápidos.

                Añade el botón con el botón de abajo o desde la cortinilla → ✏️ Editar.
            """.trimIndent()
        })
        root.addView(Button(this).apply {
            text = "Añadir botón a ajustes rápidos"
            setOnClickListener { addTile() }
        })
        root.addView(Button(this).apply {
            text = "Probar con el portapapeles"
            setOnClickListener {
                startActivity(android.content.Intent(this@SetupActivity, MimimiActivity::class.java)
                    .setAction(MimimiActivity.ACTION_CLIPBOARD))
            }
        })
        setContentView(root)
    }

    private fun addTile() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            getSystemService(StatusBarManager::class.java).requestAddTileService(
                ComponentName(this, MimimiTileService::class.java),
                getString(R.string.tile_label),
                Icon.createWithResource(this, android.R.drawable.ic_menu_edit),
                mainExecutor,
            ) { }
        } else {
            Toast.makeText(this, "Despliega los ajustes rápidos, pulsa ✏️ Editar y arrastra «Mimimi portapapeles»", Toast.LENGTH_LONG).show()
        }
    }
}
