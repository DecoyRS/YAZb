package org.zig.yazb.language.lexer

import org.zig.yazb.ZigLexer
import com.intellij.lexer.FlexAdapter

class ZigLexerAdapter : FlexAdapter(ZigLexer())
