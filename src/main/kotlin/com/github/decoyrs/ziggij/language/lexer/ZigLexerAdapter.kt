package com.github.decoyrs.ziggij.language.lexer

import com.github.decoyrs.ziggij.ZigLexer
import com.intellij.lexer.FlexAdapter

class ZigLexerAdapter : FlexAdapter(ZigLexer())
