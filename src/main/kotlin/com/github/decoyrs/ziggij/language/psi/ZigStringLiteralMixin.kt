package com.github.decoyrs.ziggij.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceService

abstract class ZigStringLiteralMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigStringLiteral {
    override fun getReferences(): Array<PsiReference> = PsiReferenceService.getService().getContributedReferences(this)
}
