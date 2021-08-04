package org.zig.yazb.language.highlighting

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import org.zig.yazb.language.psi.*

class ZigHighlighterAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when(element) {
            is ZigSymbolDecl -> annotate(element, holder)
        }
    }

    private fun annotate(element:ZigSymbolDecl, holder: AnnotationHolder) {
        holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
            .range(element)
            .textAttributes(getTextAttrForDecl(element))
            .create()
    }

    private fun getTextAttrForDecl(element: ZigSymbolDecl): TextAttributesKey {
        val defaultAttr = ZigColors.IDENTIFIER.textAttributesKey
        val parent = element.parent?: return defaultAttr
        return when(parent) {
            is ZigFnProto -> ZigColors.FUNCTION.textAttributesKey
            is ZigVarDecl -> if (parent.firstChild.elementType == ZigTypes.KEYWORD_CONST) ZigColors.CONSTANT.textAttributesKey else defaultAttr
            is ZigBlockLabel -> ZigColors.LABEL.textAttributesKey
            is ZigPtrPayload, is ZigPayload, is ZigSequenceItemDecl, is ZigSequenceIndexDecl -> defaultAttr
            else -> defaultAttr
        }
    }
}