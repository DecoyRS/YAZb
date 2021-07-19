package com.github.decoyrs.ziggij.language.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.util.PsiTreeUtil.treeWalkUp
import com.intellij.util.PairProcessor

class ZigBreakLabelReference(private val zigBreakLabel: ZigBreakLabel) : PsiPolyVariantReferenceBase<ZigElement>(zigBreakLabel.symbol), ZigReference {
    override fun getElement() = zigBreakLabel.symbol
    override fun getCanonicalText():String = zigBreakLabel.symbol.text
    override fun getRangeInElement() = TextRange(0, zigBreakLabel.symbol.textLength)

    override fun isSoft() = false

    override fun resolve() = multiResolve(false).firstOrNull()?.element as? ZigElement

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val file = element.containingFile ?: return emptyArray()
        if(element.isValid.not()) return emptyArray()

        val result = arrayListOf<ResolveResult>()
        treeWalkUp(element, file) { scope, _ ->
            if(scope !is ZigElement) {
                return@treeWalkUp false
            }
            when(scope) {
                is ZigBlockExpr -> if(
                    scope.blockLabel != null
                ) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigPrimaryExpr -> if(
                    scope.blockLabel != null
                ) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigLabeledStatement -> if(
                    scope.blockLabel != null
                ) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigLabeledTypeExpr -> if(
                    scope.blockLabel != null
                ) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
            }
            true
        }
        return result.toTypedArray()
    }

    private class Processor : PairProcessor<ZigElement, ZigElement> {
        override fun process(scope: ZigElement, prevParent: ZigElement?): Boolean {
            TODO("Not yet implemented")
        }

    }

}
