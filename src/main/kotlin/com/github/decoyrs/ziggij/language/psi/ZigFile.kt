package com.github.decoyrs.ziggij.language.psi

import com.github.decoyrs.ziggij.ZigFileType
import com.github.decoyrs.ziggij.ZigLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage.INSTANCE) {
    override fun getFileType() = ZigFileType.INSTANCE
    override fun toString() = "Zig File"
}