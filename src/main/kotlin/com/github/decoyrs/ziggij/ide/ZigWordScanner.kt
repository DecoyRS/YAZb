package com.github.decoyrs.ziggij.ide

import com.github.decoyrs.ziggij.language.lexer.ZigLexerAdapter
import com.github.decoyrs.ziggij.language.psi.ZigTypes
import com.github.decoyrs.ziggij.language.psi.tokenSetOf
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet

class ZigWordScanner : DefaultWordsScanner(
    ZigLexerAdapter(),
    TokenSet.create(ZigTypes.IDENTIFIER),
    TokenSet.orSet(tokenSetOf(ZigTypes.DOC_COMMENT, ZigTypes.CONTAINER_DOC_COMMENT, ZigTypes.LINE_COMMENT)),
    TokenSet.orSet(tokenSetOf(ZigTypes.LINE_STRING, ZigTypes.STRING_LITERAL_SINGLE))) {
    init {
        // This actually means that it's possible to do language injections into Zig string literals
        setMayHaveFileRefsInLiterals(true)
    }
}
