package com.github.decoyrs.ziggij.language

import com.github.decoyrs.ziggij.language.lexer.ZigLexerAdapter
import com.github.decoyrs.ziggij.language.psi.ZigTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object ZigSyntaxHighlighter: SyntaxHighlighterBase() {
    val KEYWORDS = arrayOf(
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
    val KEYWORD = TextAttributesKey.createTextAttributesKey("ZIG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

    val OPERATORS = arrayOf(
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
//        ZigTypes.LETTERC,
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
    val OPERATOR = TextAttributesKey.createTextAttributesKey("ZIG_OPERATOR", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

    val PARENS = arrayOf(
        ZigTypes.LPAREN,
        ZigTypes.RPAREN
    )
    val PAREN = TextAttributesKey.createTextAttributesKey("ZIG_PAREN", DefaultLanguageHighlighterColors.PARENTHESES)

    val BRACKETS = arrayOf(
        ZigTypes.LBRACKET,
        ZigTypes.RBRACKET
    )
    val BRACKET = TextAttributesKey.createTextAttributesKey("ZIG_BRACKET", DefaultLanguageHighlighterColors.BRACKETS)

    val BRACES = arrayOf(
        ZigTypes.LBRACE,
        ZigTypes.RBRACE
    )
    val BRACE = TextAttributesKey.createTextAttributesKey("ZIG_BRACE", DefaultLanguageHighlighterColors.BRACES)

    override fun getHighlightingLexer() = ZigLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when(tokenType) {
        in BRACES -> arrayOf(BRACE)
        in BRACKETS -> arrayOf(BRACKET)
        in KEYWORDS -> arrayOf(KEYWORD)
        in OPERATORS -> arrayOf(OPERATOR)
        in PARENS -> arrayOf(PAREN)

        else -> emptyArray()
    }
}