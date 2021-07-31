package org.zig.yazb.language.tests

import org.zig.yazb.language.psi.ZigTestDecl
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.icons.AllIcons
import com.intellij.psi.PsiElement

class ZigTestLineMarkerProvider : RunLineMarkerContributor() {
    override fun getInfo(element: PsiElement): Info? {
        if (element !is ZigTestDecl) return null

        return withExecutorActions(AllIcons.RunConfigurations.TestState.Run)
    }
}