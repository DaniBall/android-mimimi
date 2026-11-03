package es.daniel.mimimi

import java.text.Normalizer

/**
 * Convierte todas las vocales en "i": con tilde aguda -> í/Í, cualquier otra -> i/I.
 */
object Mimimi {

    fun transform(input: CharSequence): String {
        val nfc = Normalizer.normalize(input, Normalizer.Form.NFC)
        val out = StringBuilder(nfc.length)
        for (c in nfc) out.append(mapChar(c))
        return out.toString()
    }

    private fun mapChar(c: Char): Char {
        if (Character.isSurrogate(c)) return c
        val decomposed = Normalizer.normalize(c.toString(), Normalizer.Form.NFD)
        val base = decomposed[0]
        if (base.lowercaseChar() !in "aeiou") return c
        val hasAcute = decomposed.indexOf('́') >= 1
        val upper = base.isUpperCase()
        return when {
            hasAcute && upper -> 'Í'
            hasAcute -> 'í'
            upper -> 'I'
            else -> 'i'
        }
    }
}
