package org.zig.yazb.ide

import org.zig.yazb.language.psi.ZigNamedElement
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class ZigFindUsagesProvider : FindUsagesProvider {
    override fun getWordsScanner() = ZigWordScanner()
    override fun canFindUsagesFor(element: PsiElement) = element is ZigNamedElement
    override fun getHelpId(psiElement: PsiElement) = HelpID.FIND_OTHER_USAGES
    override fun getType(element: PsiElement) = ""
    override fun getDescriptiveName(element: PsiElement) = (element as? ZigNamedElement)?.name.orEmpty()
    override fun getNodeText(element: PsiElement, useFullName: Boolean) = ""
}
