package org.zig.yazb.ide

import org.zig.yazb.language.lexer.ZigLexerAdapter
import org.zig.yazb.language.psi.ZigTypes
import org.zig.yazb.language.psi.tokenSetOf
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.psi.tree.TokenSet

class ZigWordScanner : DefaultWordsScanner(
    ZigLexerAdapter(),
    TokenSet.create(ZigTypes.IDENTIFIER),
    TokenSet.orSet(tokenSetOf(ZigTypes.DOC_COMMENT_LINE, ZigTypes.CONTAINER_DOC_COMMENT_LINE, ZigTypes.LINE_COMMENT)),
    TokenSet.orSet(tokenSetOf(ZigTypes.LINE_STRING, ZigTypes.STRING_LITERAL_SINGLE))) {
    init {
        // This actually means that it's possible to do language injections into Zig string literals
        setMayHaveFileRefsInLiterals(true)
    }
}
