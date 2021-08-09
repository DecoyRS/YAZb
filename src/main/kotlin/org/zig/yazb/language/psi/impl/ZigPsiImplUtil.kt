@file:Suppress("style.UtilityClassWithPublicConstructor")

package org.zig.yazb.language.psi.impl

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import org.zig.yazb.language.psi.ZigStringLiteral
import org.zig.yazb.language.psi.ZigTopLevelDecl
import org.zig.yazb.language.psi.ZigTypes
import org.zig.yazb.language.psi.ZigTypes.KEYWORD_CONST

class ZigPsiImplUtil {
    companion object {
        @JvmStatic
        @Override
        fun getPresentation(element:ZigTopLevelDecl) = object : ItemPresentation {
            override fun getPresentableText() =
                if(element.fnProto != null) {
                    val funName = element.fnProto?.symbolDecl?.text?:""
                    val argList = element.fnProto!!.paramDeclList?.text?:""
                    val retType = element.fnProto!!.expr?.text?:""
                    "$funName($argList):$retType"
                } else if (element.varDecl != null) {
                    element.varDecl!!.symbolDecl?.text
                } else null

            override fun getIcon(unused: Boolean) =
                if(element.fnProto != null) AllIcons.Nodes.Function
                else if(element.varDecl != null) {
                    if(element.varDecl?.firstChild == KEYWORD_CONST)
                        AllIcons.Nodes.Constant
                    else
                        AllIcons.Nodes.Field
                }
                else null
        }

        fun getStringValue(element: ZigStringLiteral): Array<String> {
            val stringLiteral = element.node.findChildByType(ZigTypes.STRING_LITERAL_SINGLE)
            if (stringLiteral != null) {
                return arrayOf(stringLiteral.text.drop(1).dropLast(1))
            }
            val lineString = element.node.findChildByType(ZigTypes.LINE_STRING)
            if (lineString != null) {
                return arrayOf(lineString.text.split('\n').joinToString("\n") { it.trim().drop(2) })
            }

            return emptyArray()
        }
    }
}
