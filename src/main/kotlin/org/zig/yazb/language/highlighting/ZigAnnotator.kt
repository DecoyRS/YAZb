package org.zig.yazb.language.highlighting

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import org.zig.yazb.language.psi.ZigFnProto

class ZigAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when(element) {
            is ZigFnProto -> annotate(element, holder)
        }
    }

    private fun annotate(element:ZigFnProto, holder: AnnotationHolder){
        element.symbolDecl?.let {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(it)
                .textAttributes(ZigColors.FUNCTION.textAttributesKey)
                .create()
        }

    }
}