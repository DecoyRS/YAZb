package org.zig.yazb.language.core.resolve.ref

import org.zig.yazb.ZigFileType
import org.zig.yazb.language.core.ZigPsiPattern
import org.zig.yazb.language.psi.ZigStringLiteral
import com.intellij.openapi.util.Condition
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.psi.impl.source.resolve.reference.impl.providers.PsiFileReference
import com.intellij.util.ProcessingContext

class ZigPackageReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(ZigPsiPattern.importString, ZigPackageReferenceProvider())
        registrar.registerReferenceProvider(ZigPsiPattern.importString, ZigStdPackageReferenceProvider())
    }

    private class ZigStdPackageReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<PsiFileReference> {
            if (element !is ZigStringLiteral) return emptyArray()

            return arrayOf(ZigStdLibReference(element))
        }
    }

    private class ZigPackageReferenceProvider : PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<FileReference> {
            if (element !is ZigStringLiteral) return emptyArray()

            val fs = element.containingFile.originalFile.virtualFile.fileSystem
            val text = element.text.trim('\"')
            val offsetInElement = element.text.indexOfFirst { it == '\"' } + 1
            return ZigPackageReferenceSet(text, element, offsetInElement, fs.isCaseSensitive).allReferences
        }

        private class ZigPackageReferenceSet(
            str: String,
            element: ZigStringLiteral,
            startOffset: Int,
            isCaseSensitive: Boolean
        ) : FileReferenceSet(str, element, startOffset, null, isCaseSensitive) {
            override fun getDefaultContexts(): Collection<PsiFileSystemItem> = parentDirectoryContext
            override fun getReferenceCompletionFilter(): Condition<PsiFileSystemItem> {
                return Condition { it.isDirectory || it.virtualFile.fileType == ZigFileType }
            }
        }
    }
}
