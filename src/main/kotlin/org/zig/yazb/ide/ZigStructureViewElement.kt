package org.zig.yazb.ide

import com.intellij.ide.projectView.PresentationData
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.ide.util.treeView.smartTree.TreeElement
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.zig.yazb.language.psi.ZigContainerMembers
import org.zig.yazb.language.psi.ZigFile

class ZigStructureViewElement(val element : NavigatablePsiElement) : StructureViewTreeElement, SortableTreeElement {
    override fun getPresentation() = element.presentation ?: PresentationData()

    override fun getChildren(): Array<TreeElement> {
        if(element is ZigFile) {
            val members = PsiTreeUtil.getChildrenOfType(element, ZigContainerMembers::class.java)
            if(members.isNullOrEmpty()) return emptyArray()

            return members.first().containerDeclarationsList
                    .map {it.topLevelDeclList}
                    .flatten()
                    .map { el -> ZigStructureViewElement(el as NavigatablePsiElement)}
                    .toTypedArray()
        }
        return emptyArray()
    }

    override fun navigate(requestFocus: Boolean) = element.navigate(requestFocus)

    override fun canNavigate() = element.canNavigate()

    override fun canNavigateToSource() = element.canNavigateToSource()

    override fun getValue() = element

    override fun getAlphaSortKey() = element.name ?: ""
}
