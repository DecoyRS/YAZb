package com.github.decoyrs.ziggij.language.psi

import com.github.decoyrs.ziggij.ZigLanguage
import com.intellij.psi.tree.IElementType

class ZigElementType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE)
