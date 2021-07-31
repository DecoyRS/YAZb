package org.zig.yazb.language

import org.zig.yazb.ZigLanguage
import org.zig.yazb.language.lexer.ZigLexerAdapter
import org.zig.yazb.language.parser.ZigParser
import org.zig.yazb.language.psi.ZigFile
import org.zig.yazb.language.psi.ZigTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet

class ZigParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?) = ZigLexerAdapter()
    override fun createParser(project: Project?) = ZigParser()
    override fun getFileNodeType() = IFileElementType(ZigLanguage)
    override fun getCommentTokens() = TokenSet.create(ZigTypes.LINE_COMMENT)
    override fun getStringLiteralElements() = TokenSet.create(ZigTypes.STRING_LITERAL_SINGLE)
    override fun createElement(node: ASTNode?): PsiElement = ZigTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider) = ZigFile(viewProvider)
}
