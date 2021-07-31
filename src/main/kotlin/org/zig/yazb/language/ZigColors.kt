package org.zig.yazb.language

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.options.colors.AttributesDescriptor

enum class ZigColors(humanName: String, default: TextAttributesKey? = null) {
    IDENTIFIER("Variables//Default", DefaultLanguageHighlighterColors.IDENTIFIER),
    FIELD("Variables//Field", DefaultLanguageHighlighterColors.INSTANCE_FIELD),
    CONSTANT("Variables//Constant", DefaultLanguageHighlighterColors.CONSTANT),
    LABEL("Variables//Label", DefaultLanguageHighlighterColors.LABEL),

    FUNCTION("Functions//Function declaration", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION),
    METHOD("Functions//Method declaration", DefaultLanguageHighlighterColors.INSTANCE_METHOD),
    FUNCTION_CALL("Functions//Function call", DefaultLanguageHighlighterColors.FUNCTION_CALL),
    METHOD_CALL("Functions//Method call", DefaultLanguageHighlighterColors.FUNCTION_CALL),
    BUILTIN_FUNCTION_CALL("Functions//Builtin call", DefaultLanguageHighlighterColors.FUNCTION_CALL),

    PARAMETER("Parameters//Parameter", DefaultLanguageHighlighterColors.PARAMETER),
    SELF_PARAMETER("Parameters//Self parameter", DefaultLanguageHighlighterColors.KEYWORD),
    TYPE_PARAMETER("Parameters//Type parameter", DefaultLanguageHighlighterColors.IDENTIFIER),
    CONST_PARAMETER("Parameters//Const parameter", DefaultLanguageHighlighterColors.CONSTANT),

    PRIMITIVE_TYPE("Types//Primitive", DefaultLanguageHighlighterColors.KEYWORD),
    STRUCT("Types//Struct", DefaultLanguageHighlighterColors.CLASS_NAME),
    UNION("Types//Union", DefaultLanguageHighlighterColors.CLASS_NAME),
    ENUM("Types//Enum", DefaultLanguageHighlighterColors.CLASS_NAME),
    PACKAGE("Types//Module", DefaultLanguageHighlighterColors.IDENTIFIER),

    KEYWORD("Keywords//Keyword", DefaultLanguageHighlighterColors.KEYWORD),

    CHAR("Literals//Char", DefaultLanguageHighlighterColors.STRING),
    NUMBER("Literals//Number", DefaultLanguageHighlighterColors.NUMBER),
    STRING("Literals//Strings//String", DefaultLanguageHighlighterColors.STRING),

    COMMENT("Comments//Block comment", DefaultLanguageHighlighterColors.BLOCK_COMMENT),
    DOC_COMMENT("Rustdoc//Comment", DefaultLanguageHighlighterColors.DOC_COMMENT),

    BRACES("Braces and Operators//Braces", DefaultLanguageHighlighterColors.BRACES),
    BRACKETS("Braces and Operators//Brackets", DefaultLanguageHighlighterColors.BRACKETS),
    OPERATORS("Braces and Operators//Operation sign", DefaultLanguageHighlighterColors.OPERATION_SIGN),
    SEMICOLON("Braces and Operators//Semicolon", DefaultLanguageHighlighterColors.SEMICOLON),
    DOT("Braces and Operators//Dot", DefaultLanguageHighlighterColors.DOT),
    COMMA("Braces and Operators//Comma", DefaultLanguageHighlighterColors.COMMA),
    PARENTHESES("Braces and Operators//Parentheses", DefaultLanguageHighlighterColors.PARENTHESES),

    CFG_DISABLED_CODE("Conditionally disabled code"),
    ;

    val textAttributesKey = TextAttributesKey.createTextAttributesKey("org.zig.$name", default)
    val attributesDescriptor = AttributesDescriptor(humanName, textAttributesKey)
    val testSeverity: HighlightSeverity = HighlightSeverity(name, HighlightSeverity.INFORMATION.myVal)
}