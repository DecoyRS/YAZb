package org.zig.yazb.language.psi

import org.zig.yazb.ZigFileType
import org.zig.yazb.ZigLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage) {
    override fun getFileType() = ZigFileType
    override fun toString() = "Zig File"
}
