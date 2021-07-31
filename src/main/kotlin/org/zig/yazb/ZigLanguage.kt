package org.zig.yazb

import com.intellij.lang.Language

object ZigLanguage : Language(ZIG_NAME, "text/$ZIG_EXTENSION") {
    override fun isCaseSensitive() = true
    override fun getDisplayName() = ZIG_NAME
}
