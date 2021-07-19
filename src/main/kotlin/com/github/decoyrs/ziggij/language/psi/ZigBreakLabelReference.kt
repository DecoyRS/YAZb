package com.github.decoyrs.ziggij.language.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult
import com.intellij.psi.util.PsiTreeUtil.treeWalkUp

class ZigBreakLabelReference(private val zigBreakLabel: ZigBreakLabel) :
    PsiPolyVariantReferenceBase<ZigElement>(zigBreakLabel.symbol), ZigReference {
    override fun getElement() = zigBreakLabel.symbol
    override fun getCanonicalText(): String = zigBreakLabel.symbol.text
    override fun getRangeInElement() = TextRange(0, zigBreakLabel.symbol.textLength)

    override fun isSoft() = false

    override fun resolve() = multiResolve(false).firstOrNull()?.element as? ZigElement

    override fun isReferenceTo(element: PsiElement): Boolean {
        return element is ZigBlockLabel && element.symbolDecl.text == zigBreakLabel.symbol.text && element.symbolDecl === resolve()
    }

    override fun getVariants(): Array<LookupElement> {
        val file = element.containingFile ?: return emptyArray()
        if (element.isValid.not()) return emptyArray()

        val result = arrayListOf<LookupElement>()
        treeWalkUp(element, file) { scope, _ ->
            if (scope !is ZigElement) {
                return@treeWalkUp false
            }
            when (scope) {
                is ZigBlockExpr -> scope.blockLabel?.let {
                    val lookupElement = LookupElementBuilder.create(it)
                    result.add(lookupElement)
                }
                is ZigPrimaryExpr -> scope.blockLabel?.let {
                    val lookupElement = LookupElementBuilder.create(it)
                    result.add(lookupElement)
                }
                is ZigLabeledStatement -> scope.blockLabel?.let {
                    val lookupElement = LookupElementBuilder.create(it)
                    result.add(lookupElement)
                }
                is ZigLabeledTypeExpr -> scope.blockLabel?.let {
                    val lookupElement = LookupElementBuilder.create(it)
                    result.add(lookupElement)
                }
            }
            true
        }
        return result.toTypedArray()
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val file = element.containingFile ?: return emptyArray()
        if (element.isValid.not()) return emptyArray()

        val result = arrayListOf<ResolveResult>()
        treeWalkUp(element, file) { scope, _ ->
            if (scope !is ZigElement) {
                return@treeWalkUp false
            }
            when (scope) {
                is ZigBlockExpr -> if (scope.blockLabel?.symbolDecl?.text == zigBreakLabel.symbol.text) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigPrimaryExpr -> if (scope.blockLabel?.symbolDecl?.text == zigBreakLabel.symbol.text) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigLabeledStatement -> if (scope.blockLabel?.symbolDecl?.text == zigBreakLabel.symbol.text) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
                is ZigLabeledTypeExpr -> if (scope.blockLabel?.symbolDecl?.text == zigBreakLabel.symbol.text) {
                    result.add(PsiElementResolveResult(scope.blockLabel!!.symbolDecl))
                }
            }
            true
        }
        return result.toTypedArray()
    }
}
