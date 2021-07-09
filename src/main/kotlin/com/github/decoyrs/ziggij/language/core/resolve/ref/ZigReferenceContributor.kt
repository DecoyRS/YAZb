package com.github.decoyrs.ziggij.language.core.resolve.ref

import com.github.decoyrs.ziggij.language.core.ZigPsiPattern
import com.github.decoyrs.ziggij.language.psi.ZigStringLiteral
import com.github.decoyrs.ziggij.toolchain.ZigProjectToolchainHolder
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.util.ProcessingContext

class ZigReferenceContributor:PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(ZigPsiPattern.importString, ZigPackageReferenceProvider())
    }

    private class ZigPackageReferenceProvider: PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<FileReference> {
            if(element !is ZigStringLiteral) return emptyArray()
            val fs = element.containingFile.originalFile.virtualFile.fileSystem
            val text = element.text.trim('\"')
            val offsetInElement = element.text.indexOfFirst { it == '\"' } + 1
            val toolchainInfo = ZigProjectToolchainHolder.Instance.getToolchainInfo()
            return ZigPackageReferenceSet(text, element, offsetInElement, fs.isCaseSensitive).allReferences
        }
    }

    private class ZigPackageReferenceSet(str: String,
        element: ZigStringLiteral,
        startOffset: Int,
        isCaseSensitive: Boolean
    ) : FileReferenceSet(str, element, startOffset, null, isCaseSensitive) {
        override fun getDefaultContexts(): Collection<PsiFileSystemItem> = parentDirectoryContext
    }
}