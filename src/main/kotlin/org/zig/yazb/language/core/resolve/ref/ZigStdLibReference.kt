package org.zig.yazb.language.core.resolve.ref

import Icons.ZigIcons
import org.zig.yazb.YAZbBundle
import org.zig.yazb.language.psi.ZigFile
import org.zig.yazb.language.psi.ZigStringLiteral
import org.zig.yazb.language.psi.ZigStringLiteralMixin
import org.zig.yazb.toolchain.ZigProjectToolchainHolder
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiManager
import com.intellij.psi.ResolveResult
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReference
import com.intellij.util.IncorrectOperationException

class ZigStdLibReference(private val element:ZigStringLiteral) : PsiFileReference {
    @Suppress("HardCodedStringLiteral")
    private val stdLibs = setOf("std", "builtin")

    override fun getElement() = element
    private fun getRawText() = (element as? ZigStringLiteralMixin)?.getRawText() ?: ""

    override fun getRangeInElement(): TextRange {
        return TextRange(1, getRawText().length + 1)
    }

    override fun resolve(): PsiElement? {
        return multiResolve(false).firstOrNull()?.element
    }

    override fun getCanonicalText(): String = "package::${getRawText()}"

    override fun handleElementRename(newElementName: String): PsiElement {
        throw IncorrectOperationException("Renaming standard package is not supported")
    }

    override fun bindToElement(element: PsiElement): PsiElement {
        throw IncorrectOperationException("Rebinding standard package is not supported")
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        if (element !is ZigFile) return false

        return element.virtualFile.nameWithoutExtension == getRawText()
    }

    override fun getVariants(): Array<Any> {
        val lookupElements = stdLibs.map {
            LookupElementBuilder.create(it)
                .withIcon(ZigIcons.ZIG_BIG_ICON)
                .withTypeText(YAZbBundle.message("yazb.completion.stdlibs"))
                .bold()
        }
        return lookupElements.toTypedArray()
    }

    override fun isSoft() = false

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val text = getRawText()
        if (stdLibs.contains(text).not() ) return emptyArray()

        val toolchainInfo = ZigProjectToolchainHolder.Instance.getToolchainInfo()
        val pathToLib = toolchainInfo.std_dir.resolve("$text.zig")
        val libVirtualFile = LocalFileSystem.getInstance().findFileByIoFile(pathToLib)?: return emptyArray()
        val psiFile = PsiManager.getInstance(element.project).findFile(libVirtualFile)?: return emptyArray()

        return arrayOf(PsiElementResolveResult(psiFile))
    }
}