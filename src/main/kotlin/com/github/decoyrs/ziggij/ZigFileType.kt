@file:Suppress("HardCodedStringLiteral")

package com.github.decoyrs.ziggij

import Icons.ZigIcons
import com.intellij.openapi.fileTypes.LanguageFileType

class ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
    companion object {
        val INSTANCE = ZigFileType()
    }

    override fun getName() = "Zig File"
    override fun getDescription() = "Zig language file"
    override fun getDefaultExtension() = ZIG_EXTENSION
    override fun getIcon() = ZigIcons.FILE_ICON
}
