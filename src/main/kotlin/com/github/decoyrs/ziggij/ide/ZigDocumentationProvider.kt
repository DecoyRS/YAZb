package com.github.decoyrs.ziggij.ide

import com.github.decoyrs.ziggij.language.psi.ZigFnProto
import com.github.decoyrs.ziggij.language.psi.ZigTopLevelDecl
import com.github.decoyrs.ziggij.language.psi.impl.ZigFnProtoImpl
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.lang.documentation.DocumentationMarkup
import com.intellij.psi.PsiElement
import java.lang.StringBuilder

class ZigDocumentationProvider : AbstractDocumentationProvider() {
    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        if(element is ZigFnProto) {
            val parent = element.parent
            if(parent is ZigTopLevelDecl) {
                parent.itemComment?.let {
                    return it.firstChild.renderDoc()
                }
            }
        }
        return null
    }
}

private fun PsiElement.renderDoc(): String {
    val stringBuilder = StringBuilder()
    stringBuilder.append(DocumentationMarkup.DEFINITION_START)
    stringBuilder.append("Zig Function")
    stringBuilder.append(DocumentationMarkup.DEFINITION_END)
    stringBuilder.append(DocumentationMarkup.CONTENT_START)
    var currentItem:PsiElement? = this
    while(currentItem != null) {
        stringBuilder.append(currentItem.text.substringAfter("///"))
        currentItem = currentItem.nextSibling
    }
    stringBuilder.append(DocumentationMarkup.CONTENT_END)
    return stringBuilder.toString()
}

//private fun <E> List<E>.renderDoc(): String {
//    val stringBuilder = StringBuilder()
//    stringBuilder.append(DocumentationMarkup.DEFINITION_START)
//    stringBuilder.append("Zig Function")
//    stringBuilder.append(DocumentationMarkup.DEFINITION_END)
//    stringBuilder.append(DocumentationMarkup.CONTENT_START)
//    stringBuilder.append(this.joinToString("\n"))
//    stringBuilder.append(DocumentationMarkup.CONTENT_END)
//    return stringBuilder.toString()
//}
