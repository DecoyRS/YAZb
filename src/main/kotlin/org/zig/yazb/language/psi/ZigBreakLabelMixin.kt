package org.zig.yazb.language.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ZigBreakLabelMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigBreakLabel {
    override fun getReference() = ZigBreakLabelReference(this)
}
