package com.github.decoyrs.ziggij.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceService
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry

abstract class ZigStringLiteralMixin : ASTWrapperPsiElement, ZigStringLiteral {
    constructor(node: ASTNode) : super(node)
    override fun getReferences(): Array<PsiReference> = PsiReferenceService.getService().getContributedReferences(this)
}