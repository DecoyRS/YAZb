package com.github.decoyrs.ziggij.language

import com.github.decoyrs.ziggij.ZigLanguage
import com.github.decoyrs.ziggij.language.lexer.ZigLexerAdapter
import com.github.decoyrs.ziggij.language.parser.ZigParser
import com.github.decoyrs.ziggij.language.psi.ZigFile
import com.github.decoyrs.ziggij.language.psi.ZigTypes
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
    override fun getFileNodeType() = IFileElementType(ZigLanguage.INSTANCE)
    override fun getCommentTokens() = TokenSet.create(ZigTypes.LINE_COMMENT)
    override fun getStringLiteralElements() = TokenSet.create(ZigTypes.STRING_LITERAL_SINGLE)
    override fun createElement(node: ASTNode?): PsiElement = ZigTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider) = ZigFile(viewProvider)
}
