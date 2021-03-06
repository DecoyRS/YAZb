package org.zig.yazb.language.core

import org.zig.yazb.language.psi.ZigTypes
import com.intellij.patterns.ElementPattern
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.psi.PsiElement

inline fun <reified I : PsiElement> psiElement(): PsiElementPattern.Capture<I> {
    return psiElement(I::class.java)
}

inline infix fun <reified I : PsiElement> ElementPattern<out I>.or(pattern: ElementPattern<out I>):
    PsiElementPattern.Capture<I> {
    return psiElement<I>().andOr(this, pattern)
}

object ZigPsiPattern {

    private val importBuiltin: PsiElementPattern.Capture<PsiElement> =
        psiElement(ZigTypes.BUILTIN_INVOKE).withFirstChild(psiElement(ZigTypes.BUILTIN_SYMBOL).withText("@import"))

    private const val depthOfStringInImport = 4
    val importString: PsiElementPattern.Capture<PsiElement> =
        psiElement(ZigTypes.STRING_LITERAL).withSuperParent(depthOfStringInImport, importBuiltin)
}
