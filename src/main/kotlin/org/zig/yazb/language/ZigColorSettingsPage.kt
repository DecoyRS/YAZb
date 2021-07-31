@file:Suppress("MaximumLineLength", "style.MaxLineLength")

package org.zig.yazb.language

import Icons.ZigIcons
import org.zig.yazb.YAZbBundle
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage

class ZigColorSettingsPage : ColorSettingsPage {
    companion object {
        val DESCRIPTORS = arrayOf(
            ZigColors.KEYWORD.attributesDescriptor,
            ZigColors.OPERATORS.attributesDescriptor,
            ZigColors.PARENTHESES.attributesDescriptor,
            ZigColors.BRACKETS.attributesDescriptor,
            ZigColors.BRACES.attributesDescriptor,
        )
    }

    override fun getAttributeDescriptors() = DESCRIPTORS

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = YAZbBundle.message("configurable.name.zig")

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
