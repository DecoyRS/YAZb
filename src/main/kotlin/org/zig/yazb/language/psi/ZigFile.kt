package org.zig.yazb.language.psi

import Icons.ZigIcons
import org.zig.yazb.ZigFileType
import org.zig.yazb.ZigLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.FileViewProvider

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage) {
    override fun getFileType() = ZigFileType
    override fun toString() = "Zig File"
    override fun getPresentation() = object : ItemPresentation {
        override fun getPresentableText() = name
        override fun getIcon(unused: Boolean) = ZigIcons.FILE_ICON
        override fun getLocationString() = parent?.virtualFile?.presentableUrl
    }
}
