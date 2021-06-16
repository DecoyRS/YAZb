package com.github.decoyrs.ziggij.language.psi.impl

import com.github.decoyrs.ziggij.ZigLanguage
import com.github.decoyrs.ziggij.language.psi.ZigNamedElement
import com.github.decoyrs.ziggij.language.psi.ZigSymbol
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.util.PsiTreeUtil.findChildOfType

fun fromText(string: String, project: Project): PsiElement = PsiFileFactory
    .getInstance(project)
    .createFileFromText(ZigLanguage.INSTANCE, string)
    .firstChild
    .let { (it as? PsiErrorElement)?.firstChild ?: it }

abstract class ZigNamedElementImpl(node: ASTNode): ASTWrapperPsiElement(node), ZigNamedElement {
    override fun getNameIdentifier() = findChildOfType(this, ZigSymbol::class.java)
    override fun setName(name: String) = also { nameIdentifier?.replace(fromText(name, project)) }
    override fun getName() = nameIdentifier?.text

}