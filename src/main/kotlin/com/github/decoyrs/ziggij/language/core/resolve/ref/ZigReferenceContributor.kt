package com.github.decoyrs.ziggij.language.core.resolve.ref

import com.github.decoyrs.ziggij.language.core.ZigPsiPattern
import com.github.decoyrs.ziggij.language.psi.ZigStringLiteral
import com.github.decoyrs.ziggij.toolchain.ZigProjectToolchainHolder
import com.intellij.openapi.util.Condition
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.psi.*
import com.intellij.psi.impl.file.PsiDirectoryFactory
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReference
import com.intellij.psi.impl.source.resolve.reference.impl.providers.FileReferenceSet
import com.intellij.util.ProcessingContext
import org.jetbrains.annotations.NotNull

class ZigReferenceContributor:PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(ZigPsiPattern.importString, ZigPackageReferenceProvider())
        registrar.registerReferenceProvider(ZigPsiPattern.importString, ZigStdPackageReferenceProvider())
    }

    private class ZigStdPackageReferenceProvider: PsiReferenceProvider(){
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<FileReference> {
            if(element !is ZigStringLiteral) return emptyArray()

            val fs = element.containingFile.originalFile.virtualFile.fileSystem
            val text = element.text.trim('\"')
            val offsetInElement = element.text.indexOfFirst { it == '\"' } + 1

            val toolchainInfo = ZigProjectToolchainHolder.Instance.getToolchainInfo()
            val stdDir = LocalFileSystem.getInstance().findFileByIoFile(toolchainInfo.std_dir)?: return emptyArray()

            val psiStdDir = PsiDirectoryFactory.getInstance(element.project).createDirectory(stdDir)
            return ZigStdPackageReferenceSet(text, element, offsetInElement, fs.isCaseSensitive, psiStdDir).allReferences
        }

        private class ZigStdPackageReferenceSet(
            str: String,
            element: ZigStringLiteral,
            startOffset: Int,
            isCaseSensitive: Boolean,
            private val stdDir: PsiDirectory
        ) : FileReferenceSet(str, element, startOffset, null, isCaseSensitive) {
            val stdLibs = setOf("std.zig", "builtin.zig")

            override fun getDefaultContexts(): Collection<PsiFileSystemItem> = setOf(stdDir)
            override fun getReferenceCompletionFilter(): Condition<PsiFileSystemItem> {
                return Condition { it.isDirectory.not() && stdLibs.contains(it.virtualFile.name) }
            }
        }
    }

    private class ZigPackageReferenceProvider: PsiReferenceProvider() {
        override fun getReferencesByElement(element: PsiElement, context: ProcessingContext): Array<FileReference> {
            if(element !is ZigStringLiteral) return emptyArray()

            val fs = element.containingFile.originalFile.virtualFile.fileSystem
            val text = element.text.trim('\"')
            val offsetInElement = element.text.indexOfFirst { it == '\"' } + 1
            return ZigPackageReferenceSet(text, element, offsetInElement, fs.isCaseSensitive).allReferences
        }

        private class ZigPackageReferenceSet(str: String,
                                             element: ZigStringLiteral,
                                             startOffset: Int,
                                             isCaseSensitive: Boolean
        ) : FileReferenceSet(str, element, startOffset, null, isCaseSensitive) {
            override fun getDefaultContexts(): Collection<PsiFileSystemItem> = parentDirectoryContext
        }
    }

}