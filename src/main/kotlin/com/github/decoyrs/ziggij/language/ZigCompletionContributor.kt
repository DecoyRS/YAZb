package com.github.decoyrs.ziggij.language

import Icons.ZigIcons
import com.github.decoyrs.ziggij.ZiggIjBundle
import com.github.decoyrs.ziggij.language.psi.ZigTypes
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.util.ProcessingContext

class ZigCompletionProvider(private val list: List<LookupElement>)
    : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet
    ) =
        list.forEach(result::addElement)
}

class ZigCompletionContributor : CompletionContributor() {
    companion object {
        val KEYWORDS = arrayOf(
            "align",
            "allowzero",
            "and",
            "anyframe",
            "anytype",
            "asm",
            "async",
            "await",
            "break",
            "callconv",
            "catch",
            "comptime",
            "const",
            "continue",
            "defer",
            "else",
            "enum",
            "errdefer",
            "error",
            "export",
            "extern",
            "false",
            "fn",
            "for",
            "if",
            "inline",
            "linksection",
            "noalias",
            "nosuspend",
            "null",
            "opaque",
            "or",
            "orelse",
            "packed",
            "pub",
            "resume",
            "return",
            "struct",
            "suspend",
            "switch",
            "test",
            "threadlocal",
            "true",
            "try",
            "undefined",
            "union",
            "unreachable",
            "usingnamespace",
            "var",
            "volatile",
            "while",
        ).map {
            LookupElementBuilder.create(it)
                .withIcon(ZigIcons.ZIG_BIG_ICON)
                .withTypeText(ZiggIjBundle.message("ziggij.completion.keywords"))
                .bold()
        }

        val BUILTINS = arrayOf(
            "addWithOverflow",
            "alignCast",
            "alignOf",
            "as",
            "asyncCall",
            "atomicLoad",
            "atomicRmw",
            "atomicStore",
            "bitCast",
            "bitOffsetOf",
            "boolToInt",
            "bitSizeOf",
            "breakpoint",
            "mulAdd",
            "byteSwap",
            "bitReverse",
            "byteOffsetOf",
            "call",
            "cDefine",
            "cImport",
            "cInclude",
            "clz",
            "cmpxchgStrong",
            "cmpxchgWeak",
            "compileError",
            "compileLog",
            "ctz",
            "cUndef",
            "divExact",
            "divFloor",
            "divTrunc",
            "embedFile",
            "enumToInt",
            "errorName",
            "errorReturnTrace",
            "errorToInt",
            "errSetCast",
            "export",
            "fence",
            "field",
            "fieldParentPtr",
            "floatCast",
            "floatToInt",
            "frame",
            "Frame",
            "frameAddress",
            "frameSize",
            "hasDecl",
            "hasField",
            "import",
            "intCast",
            "intToEnum",
            "intToError",
            "intToFloat",
            "intToPtr",
            "memcpy",
            "memset",
            "wasmMemorySize",
            "wasmMemoryGrow",
            "mod",
            "mulWithOverflow",
            "panic",
            "popCount",
            "ptrCast",
            "ptrToInt",
            "rem",
            "returnAddress",
            "setAlignStack",
            "setCold",
            "setEvalBranchQuota",
            "setFloatMode",
            "setRuntimeSafety",
            "shlExact",
            "shlWithOverflow",
            "shrExact",
            "shuffle",
            "sizeOf",
            "splat",
            "reduce",
            "src",
            "sqrt",
            "sin",
            "cos",
            "exp",
            "exp2",
            "log",
            "log2",
            "log10",
            "fabs",
            "floor",
            "ceil",
            "trunc",
            "round",
            "subWithOverflow",
            "tagName",
            "TagType",
            "This",
            "truncate",
            "Type",
            "typeInfo",
            "typeName",
            "TypeOf",
            "unionInit",
        ).map {
            LookupElementBuilder.create(it)
                .withIcon(ZigIcons.ZIG_BIG_ICON)
                .withTypeText(ZiggIjBundle.message("ziggij.completion.keywords"))
                .bold()
        }
    }

    init {
        extend(
            CompletionType.BASIC,
            psiElement(ZigTypes.IDENTIFIER),
            ZigCompletionProvider(KEYWORDS))
        extend(
            CompletionType.BASIC,
            psiElement().afterLeaf("@").andNot(psiElement().afterLeaf(".")),
            ZigCompletionProvider(BUILTINS))
    }
}