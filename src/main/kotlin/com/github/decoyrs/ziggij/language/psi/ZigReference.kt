package com.github.decoyrs.ziggij.language.psi

import com.intellij.psi.PsiPolyVariantReference

interface ZigReference : PsiPolyVariantReference {

    override fun getElement(): ZigElement

    override fun resolve(): ZigElement?
}