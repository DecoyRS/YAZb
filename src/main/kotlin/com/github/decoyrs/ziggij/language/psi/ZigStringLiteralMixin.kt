package com.github.decoyrs.ziggij.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceService

abstract class ZigStringLiteralMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigStringLiteral {
    override fun getReferences(): Array<PsiReference> = PsiReferenceService.getService().getContributedReferences(this)
    fun getRawText():String {
        if(stringLiteralSingle != null) {
            return stringLiteralSingle!!.text.trim('\"')
        }
        if (stringLiteralMultiline != null) {
            return stringLiteralMultiline!!.children.map { it.text.substringAfter("\\\\") }.joinToString { "" }
        }
        return ""
    }
}
