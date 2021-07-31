package org.zig.yazb.language

import org.zig.yazb.language.psi.*
import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

class ZigFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (root !is ZigFile) return emptyArray()

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
        override fun visitItemDocComment(o: ZigItemDocComment) = fold(o)

        private fun fold(element: ZigItemDocComment) {
            val prevElement = PsiTreeUtil.skipWhitespacesBackward(element)
            if (prevElement == null || prevElement is ZigItemDocComment) return

            var nextElement = PsiTreeUtil.skipWhitespacesForward(element)
            if (nextElement !is ZigItemDocComment) return

            var lastElement = nextElement
            while(nextElement != null) {
                lastElement = nextElement
                nextElement = PsiTreeUtil.skipWhitespacesForward(element)
            }
        }

        private fun fold(element: ZigBlock) {
            descriptors += FoldingDescriptor(element.node, element.textRange)
        }
    }

    override fun getPlaceholderText(node: ASTNode) = when(node) {
        is ZigBlock -> "{...}"
        is ZigItemDocComment,
        is ZigContainerDocComment -> "// ..."
        else -> ""
    }

    override fun isCollapsedByDefault(node: ASTNode) = false
}
