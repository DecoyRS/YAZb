package org.zig.yazb.language.psi

import org.zig.yazb.ZigLanguage
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

class ZigTokenType(name: String) : IElementType(name, ZigLanguage) {
    @Suppress("HardCodedStringLiteral")
    override fun toString() = "ZigTokenType." + super.toString()
}

fun tokenSetOf(vararg tokens: IElementType) = TokenSet.create(*tokens)
