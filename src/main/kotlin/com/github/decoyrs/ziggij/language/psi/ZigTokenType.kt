package com.github.decoyrs.ziggij.language.psi

import com.github.decoyrs.ziggij.ZigLanguage
import com.intellij.psi.tree.IElementType

class ZigTokenType(name:String) : IElementType(name, ZigLanguage.INSTANCE) {
    override fun toString() = "ZigTokenType." + super.toString()
}