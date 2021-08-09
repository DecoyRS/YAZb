package org.zig.yazb.ide

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.psi.PsiFile
import org.zig.yazb.language.psi.ZigTopLevelDecl

class ZigStructureViewModel(psiFile: PsiFile) : StructureViewModelBase(psiFile, ZigStructureViewElement(psiFile)), StructureViewModel.ElementInfoProvider {
    override fun getSorters() = arrayOf(Sorter.ALPHA_SORTER)

    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?) = false

    override fun isAlwaysLeaf(element: StructureViewTreeElement?) = when(element?.value) {
        is ZigTopLevelDecl -> true
        else -> false
    }


}
