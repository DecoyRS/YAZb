@file:Suppress("style.UtilityClassWithPublicConstructor")

package org.zig.yazb.language.psi.impl

import org.zig.yazb.language.psi.ZigStringLiteral
import org.zig.yazb.language.psi.ZigTypes

class ZigPsiImplUtil private constructor() {
    companion object {
        fun getStringValue(element: ZigStringLiteral): Array<String> {
            val stringLiteral = element.node.findChildByType(ZigTypes.STRING_LITERAL_SINGLE)
            if (stringLiteral != null) {
                return arrayOf(stringLiteral.text.drop(1).dropLast(1))
            }
            val lineString = element.node.findChildByType(ZigTypes.LINE_STRING)
            if (lineString != null) {
                return arrayOf(lineString.text.split('\n').joinToString("\n") { it.trim().drop(2) })
            }

            return emptyArray()
        }
    }
}
