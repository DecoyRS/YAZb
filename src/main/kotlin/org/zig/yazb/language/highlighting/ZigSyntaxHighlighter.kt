@file:Suppress("MaximumLineLength")

package org.zig.yazb.language.highlighting

import org.zig.yazb.language.lexer.ZigLexerAdapter
import org.zig.yazb.language.psi.ZigTypes
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object ZigSyntaxHighlighter : SyntaxHighlighterBase() {
    val BRACES = setOf(
        ZigTypes.LBRACE,
        ZigTypes.RBRACE
    )

    val BRACKETS = setOf(
        ZigTypes.LBRACKET,
        ZigTypes.RBRACKET
    )

    val KEYWORDS = setOf(
        ZigTypes.KEYWORD_ALIGN,
        ZigTypes.KEYWORD_ALLOWZERO,
        ZigTypes.KEYWORD_AND,
        ZigTypes.KEYWORD_ANYFRAME,
        ZigTypes.KEYWORD_ANYTYPE,
        ZigTypes.KEYWORD_ASM,
        ZigTypes.KEYWORD_ASYNC,
        ZigTypes.KEYWORD_AWAIT,
        ZigTypes.KEYWORD_BREAK,
        ZigTypes.KEYWORD_CATCH,
        ZigTypes.KEYWORD_COMPTIME,
        ZigTypes.KEYWORD_CONST,
        ZigTypes.KEYWORD_CONTINUE,
        ZigTypes.KEYWORD_DEFER,
        ZigTypes.KEYWORD_ELSE,
        ZigTypes.KEYWORD_ENUM,
        ZigTypes.KEYWORD_ERRDEFER,
        ZigTypes.KEYWORD_ERROR,
        ZigTypes.KEYWORD_EXPORT,
        ZigTypes.KEYWORD_EXTERN,
        ZigTypes.KEYWORD_FALSE,
        ZigTypes.KEYWORD_FN,
        ZigTypes.KEYWORD_FOR,
        ZigTypes.KEYWORD_IF,
        ZigTypes.KEYWORD_INLINE,
        ZigTypes.KEYWORD_LINKSECTION,
        ZigTypes.KEYWORD_NOALIAS,
        ZigTypes.KEYWORD_NOSUSPEND,
        ZigTypes.KEYWORD_NULL,
        ZigTypes.KEYWORD_OPAQUE,
        ZigTypes.KEYWORD_OR,
        ZigTypes.KEYWORD_ORELSE,
        ZigTypes.KEYWORD_PACKED,
        ZigTypes.KEYWORD_PUB,
        ZigTypes.KEYWORD_RESUME,
        ZigTypes.KEYWORD_RETURN,
        ZigTypes.KEYWORD_STRUCT,
        ZigTypes.KEYWORD_SUSPEND,
        ZigTypes.KEYWORD_SWITCH,
        ZigTypes.KEYWORD_TEST,
        ZigTypes.KEYWORD_THREADLOCAL,
        ZigTypes.KEYWORD_TRUE,
        ZigTypes.KEYWORD_TRY,
        ZigTypes.KEYWORD_UNDEFINED,
        ZigTypes.KEYWORD_UNION,
        ZigTypes.KEYWORD_UNREACHABLE,
        ZigTypes.KEYWORD_USINGNAMESPACE,
        ZigTypes.KEYWORD_VAR,
        ZigTypes.KEYWORD_VOLATILE,
        ZigTypes.KEYWORD_WHILE
    )

    val OPERATORS = setOf(
        ZigTypes.AMPERSAND,
        ZigTypes.AMPERSANDEQUAL,
        ZigTypes.ASTERISK,
        ZigTypes.ASTERISK2,
        ZigTypes.ASTERISKEQUAL,
        ZigTypes.ASTERISKPERCENT,
        ZigTypes.ASTERISKPERCENTEQUAL,
        ZigTypes.CARET,
        ZigTypes.CARETEQUAL,
        ZigTypes.COLON,
        ZigTypes.COMMA,
        ZigTypes.DOT,
        ZigTypes.DOT2,
        ZigTypes.DOT3,
        ZigTypes.DOTASTERISK,
        ZigTypes.DOTQUESTIONMARK,
        ZigTypes.EQUAL,
        ZigTypes.EQUALEQUAL,
        ZigTypes.EQUALRARROW,
        ZigTypes.EXCLAMATIONMARK,
        ZigTypes.EXCLAMATIONMARKEQUAL,
        ZigTypes.LARROW,
        ZigTypes.LARROW2,
        ZigTypes.LARROW2EQUAL,
        ZigTypes.LARROWEQUAL,
        ZigTypes.MINUS,
        ZigTypes.MINUSEQUAL,
        ZigTypes.MINUSPERCENT,
        ZigTypes.MINUSPERCENTEQUAL,
        ZigTypes.MINUSRARROW,
        ZigTypes.PERCENT,
        ZigTypes.PERCENTEQUAL,
        ZigTypes.PIPE,
        ZigTypes.PIPE2,
        ZigTypes.PIPEEQUAL,
        ZigTypes.PLUS,
        ZigTypes.PLUS2,
        ZigTypes.PLUSEQUAL,
        ZigTypes.PLUSPERCENT,
        ZigTypes.PLUSPERCENTEQUAL,
        ZigTypes.QUESTIONMARK,
        ZigTypes.RARROW,
        ZigTypes.RARROW2,
        ZigTypes.RARROW2EQUAL,
        ZigTypes.RARROWEQUAL,
        ZigTypes.SEMICOLON,
        ZigTypes.SLASH,
        ZigTypes.SLASHEQUAL,
        ZigTypes.TILDE
    )

    val PARENS = setOf(
        ZigTypes.LPAREN,
        ZigTypes.RPAREN
    )

    override fun getHighlightingLexer() = ZigLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
        ZigTypes.BUILTINIDENTIFIER -> arrayOf(ZigColors.BUILTIN_FUNCTION_CALL.textAttributesKey)
        ZigTypes.INTEGER_LITERAL, ZigTypes.FLOAT_LITERAL -> arrayOf(ZigColors.NUMBER.textAttributesKey)
        ZigTypes.LINE_COMMENT -> arrayOf(ZigColors.COMMENT.textAttributesKey)
        ZigTypes.DOC_COMMENT_LINE, ZigTypes.CONTAINER_DOC_COMMENT_LINE -> arrayOf(ZigColors.DOC_COMMENT.textAttributesKey)
        ZigTypes.SEMICOLON -> arrayOf(ZigColors.SEMICOLON.textAttributesKey)
        ZigTypes.STRING_LITERAL_SINGLE, ZigTypes.LINE_STRING -> arrayOf(ZigColors.STRING.textAttributesKey)
        in BRACES -> arrayOf(ZigColors.BRACES.textAttributesKey)
        in BRACKETS -> arrayOf(ZigColors.BRACKETS.textAttributesKey)
        in KEYWORDS -> arrayOf(ZigColors.KEYWORD.textAttributesKey)
        in OPERATORS -> arrayOf(ZigColors.OPERATORS.textAttributesKey)
        in PARENS -> arrayOf(ZigColors.PARENTHESES.textAttributesKey)

        else -> emptyArray()
    }
}
