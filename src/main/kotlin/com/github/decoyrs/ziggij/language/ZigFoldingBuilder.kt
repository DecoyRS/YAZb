package com.github.decoyrs.ziggij.language

import com.github.decoyrs.ziggij.language.psi.ZigBlock
import com.github.decoyrs.ziggij.language.psi.ZigFile
import com.github.decoyrs.ziggij.language.psi.ZigVisitor
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class ZigFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if(root !is ZigFile) return emptyArray()

        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        val visitor = FoldingVisitor(descriptors)

        PsiTreeUtil.processElements(root) {
            it.accept(visitor); true
        }
        return descriptors.toTypedArray()
    }

    class FoldingVisitor(
        private val descriptors: MutableList<FoldingDescriptor>
    ) : ZigVisitor() {
        override fun visitBlock(o: ZigBlock) = fold(o)

        private fun fold(element: PsiElement) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }

    override fun getPlaceholderText(node: ASTNode) = "{...}"

    override fun isCollapsedByDefault(node: ASTNode) = false
}