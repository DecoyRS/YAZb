package com.github.decoyrs.ziggij

import com.intellij.lang.Language


class ZigLanguage : Language(ZIG_NAME, "text/$ZIG_EXTENSION") {
    companion object {
        val INSTANCE = ZigLanguage()
    }
}