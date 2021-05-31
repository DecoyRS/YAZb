package com.github.decoyrs.ziggij.language

import Icons.ZigIcons
import com.github.decoyrs.ziggij.ZigFileType
import com.github.decoyrs.ziggij.ZiggIjBundle
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class ZigColorSettingsPage : ColorSettingsPage {
    companion object{
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor(ZiggIjBundle.message("ziggij.highlighter.settings.keyword"), ZigSyntaxHighlighter.KEYWORD),
            AttributesDescriptor(ZiggIjBundle.message("ziggij.highlighter.settings.operator.ordinary"), ZigSyntaxHighlighter.OPERATOR),
            AttributesDescriptor(ZiggIjBundle.message("ziggij.highlighter.settings.operator.parens"), ZigSyntaxHighlighter.PAREN),
            AttributesDescriptor(ZiggIjBundle.message("ziggij.highlighter.settings.operator.brackets"), ZigSyntaxHighlighter.BRACKET),
            AttributesDescriptor(ZiggIjBundle.message("ziggij.highlighter.settings.operator.braces"), ZigSyntaxHighlighter.BRACE),
        )
    }

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = ZigFileType.INSTANCE.name

    override fun getIcon() = ZigIcons.FILE_ICON

    override fun getHighlighter() = ZigSyntaxHighlighter

    override fun getDemoText() =
        """
		// Comment
		const std = @<builtinFunction>import</builtinFunction>("std");
		pub fn <functionName>main</functionName>() !void {
		    var aVar = []f64{1 + 2.0};
		    std.debug.warn("This is a<escape>\n</escape>new line<escapeInvalid>\g</escapeInvalid>");
		}
		""".trimIndent()

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? = null
}