package com.github.decoyrs.ziggij;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.github.decoyrs.ziggij.language.psi.ZigTypes;
import com.github.decoyrs.ziggij.language.psi.ZigTokenType;

%%

%{
  public ZigLexer() { this((java.io.Reader) null); }
%}

%public
%class ZigLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

OTHERWISE=[^]

EOL_WS           = \n | \r | \r\n
LINE_WS          = [\ \t]
WHITE_SPACE_CHAR = {EOL_WS} | {LINE_WS}
WHITE_SPACE      = {WHITE_SPACE_CHAR}+

HEX = [0-9a-fA-F]
DEC = [0-9]
OCT = [0-7]
BIN = [01]

DEC_INT = {DEC} ("_"? {DEC}+)*
HEX_INT = {HEX} ("_"? {HEX}+)*
OCT_INT = {OCT} ("_"? {OCT}+)*
BIN_INT = {BIN} ("_"? {BIN}+)*

CHAR_ESCAPE = ( "\x" {HEX} {HEX} | "\u\{" {HEX}+ "}" | "\\" [nr\\t'\"] )
CHAR_CHAR = ({CHAR_ESCAPE} | [^\\'\n])
STRING_CHAR = ({CHAR_ESCAPE} | [^\\\"\n])
CONTAINER_DOC_COMMENT = ({LINE_WS}*"//!".*{EOL_WS})*({LINE_WS}*"//!".*)
// !(!a|b) is a (set) difference between a and b.
DOC_COMMENT  = {LINE_WS}*!(!("///".*)|("////".*))
LINE_STRING = ("\\\\" [^\n]* [ \n]*)+

CHAR_LITERAL = "'" {CHAR_CHAR} "'"
INCOMPLETE_CHAR = "'" {CHAR_CHAR}
FLOAT_LITERAL = (
      "0x" {HEX_INT} "."    {HEX_INT} ( [pP] [-+]? {HEX_INT})?
    |      {DEC_INT} "."    {DEC_INT} ( [eE] [-+]? {DEC_INT})?
    | "0x" {HEX_INT} "."?               [pP] [-+]? {HEX_INT}
    |      {DEC_INT} "."?               [eE] [-+]? {DEC_INT}
)
INTEGER_LITERAL = (
      "0b" {BIN_INT}
    | "0o" {OCT_INT}
    | "0x" {HEX_INT}
    |      {DEC_INT}
)
STRING_LITERAL_SINGLE = "\"" {STRING_CHAR}* "\""
INCOMPLETE_STRING     = "\"" {STRING_CHAR}*
IDENTIFIER = (// !keyword
      [A-Za-z_][A-Za-z0-9_]*
    | "@\""{STRING_CHAR}*"\""
    )
BUILTINIDENTIFIER = "@"[A-Za-z_][A-Za-z0-9_]*

%%

<YYINITIAL>{
    {WHITE_SPACE}             { return TokenType.WHITE_SPACE; }

    "&"    { return ZigTypes.AMPERSAND; }
    "&="   { return ZigTypes.AMPERSANDEQUAL; }
    "*"    { return ZigTypes.ASTERISK; }
    "**"   { return ZigTypes.ASTERISK2; }
    "*="   { return ZigTypes.ASTERISKEQUAL; }
    "*%"   { return ZigTypes.ASTERISKPERCENT; }
    "*%="  { return ZigTypes.ASTERISKPERCENTEQUAL; }
    "^"    { return ZigTypes.CARET; }
    "^="   { return ZigTypes.CARETEQUAL; }
    ":"    { return ZigTypes.COLON; }
    ","    { return ZigTypes.COMMA; }
    "."    { return ZigTypes.DOT; }
    ".."   { return ZigTypes.DOT2; }
    "..."  { return ZigTypes.DOT3; }
    ".*"   { return ZigTypes.DOTASTERISK; }
    ".?"   { return ZigTypes.DOTQUESTIONMARK; }
    "="    { return ZigTypes.EQUAL; }
    "=="   { return ZigTypes.EQUALEQUAL; }
    "=>"   { return ZigTypes.EQUALRARROW; }
    "!"    { return ZigTypes.EXCLAMATIONMARK; }
    "!="   { return ZigTypes.EXCLAMATIONMARKEQUAL; }
    "<"    { return ZigTypes.LARROW; }
    "<<"   { return ZigTypes.LARROW2; }
    "<<="  { return ZigTypes.LARROW2EQUAL; }
    "<="   { return ZigTypes.LARROWEQUAL; }
    "{"    { return ZigTypes.LBRACE; }
    "["    { return ZigTypes.LBRACKET; }
    "("    { return ZigTypes.LPAREN; }
    "-"    { return ZigTypes.MINUS; }
    "-="   { return ZigTypes.MINUSEQUAL; }
    "-%"   { return ZigTypes.MINUSPERCENT; }
    "-%="  { return ZigTypes.MINUSPERCENTEQUAL; }
    "->"   { return ZigTypes.MINUSRARROW; }
    "%"    { return ZigTypes.PERCENT; }
    "%="   { return ZigTypes.PERCENTEQUAL; }
    "|"    { return ZigTypes.PIPE; }
    "||"   { return ZigTypes.PIPE2; }
    "|="   { return ZigTypes.PIPEEQUAL; }
    "+"    { return ZigTypes.PLUS; }
    "++"   { return ZigTypes.PLUS2; }
    "+="   { return ZigTypes.PLUSEQUAL; }
    "+%"   { return ZigTypes.PLUSPERCENT; }
    "+%="  { return ZigTypes.PLUSPERCENTEQUAL; }
    "?"    { return ZigTypes.QUESTIONMARK; }
    ">"    { return ZigTypes.RARROW; }
    ">>"   { return ZigTypes.RARROW2; }
    ">>="  { return ZigTypes.RARROW2EQUAL; }
    ">="   { return ZigTypes.RARROWEQUAL; }
    "}"    { return ZigTypes.RBRACE; }
    "]"    { return ZigTypes.RBRACKET; }
    ")"    { return ZigTypes.RPAREN; }
    ";"    { return ZigTypes.SEMICOLON; }
    "/"    { return ZigTypes.SLASH; }
    "/="   { return ZigTypes.SLASHEQUAL; }
    "~"    { return ZigTypes.TILDE; }

    "align"           { return ZigTypes.KEYWORD_ALIGN; }
    "allowzero"       { return ZigTypes.KEYWORD_ALLOWZERO; }
    "and"             { return ZigTypes.KEYWORD_AND; }
    "anyframe"        { return ZigTypes.KEYWORD_ANYFRAME; }
    "anytype"         { return ZigTypes.KEYWORD_ANYTYPE; }
    "asm"             { return ZigTypes.KEYWORD_ASM; }
    "async"           { return ZigTypes.KEYWORD_ASYNC; }
    "await"           { return ZigTypes.KEYWORD_AWAIT; }
    "break"           { return ZigTypes.KEYWORD_BREAK; }
    "callconv"        { return ZigTypes.KEYWORD_CALLCONV; }
    "catch"           { return ZigTypes.KEYWORD_CATCH; }
    "comptime"        { return ZigTypes.KEYWORD_COMPTIME; }
    "const"           { return ZigTypes.KEYWORD_CONST; }
    "continue"        { return ZigTypes.KEYWORD_CONTINUE; }
    "defer"           { return ZigTypes.KEYWORD_DEFER; }
    "else"            { return ZigTypes.KEYWORD_ELSE; }
    "enum"            { return ZigTypes.KEYWORD_ENUM; }
    "errdefer"        { return ZigTypes.KEYWORD_ERRDEFER; }
    "error"           { return ZigTypes.KEYWORD_ERROR; }
    "export"          { return ZigTypes.KEYWORD_EXPORT; }
    "extern"          { return ZigTypes.KEYWORD_EXTERN; }
    "false"           { return ZigTypes.KEYWORD_FALSE; }
    "fn"              { return ZigTypes.KEYWORD_FN; }
    "for"             { return ZigTypes.KEYWORD_FOR; }
    "if"              { return ZigTypes.KEYWORD_IF; }
    "inline"          { return ZigTypes.KEYWORD_INLINE; }
    "noalias"         { return ZigTypes.KEYWORD_NOALIAS; }
    "nosuspend"       { return ZigTypes.KEYWORD_NOSUSPEND; }
    "null"            { return ZigTypes.KEYWORD_NULL; }
    "opaque"          { return ZigTypes.KEYWORD_OPAQUE; }
    "or"              { return ZigTypes.KEYWORD_OR; }
    "orelse"          { return ZigTypes.KEYWORD_ORELSE; }
    "packed"          { return ZigTypes.KEYWORD_PACKED; }
    "pub"             { return ZigTypes.KEYWORD_PUB; }
    "resume"          { return ZigTypes.KEYWORD_RESUME; }
    "return"          { return ZigTypes.KEYWORD_RETURN; }
    "linksection"     { return ZigTypes.KEYWORD_LINKSECTION; }
    "struct"          { return ZigTypes.KEYWORD_STRUCT; }
    "suspend"         { return ZigTypes.KEYWORD_SUSPEND; }
    "switch"          { return ZigTypes.KEYWORD_SWITCH; }
    "test"            { return ZigTypes.KEYWORD_TEST; }
    "threadlocal"     { return ZigTypes.KEYWORD_THREADLOCAL; }
    "true"            { return ZigTypes.KEYWORD_TRUE; }
    "try"             { return ZigTypes.KEYWORD_TRY; }
    "undefined"       { return ZigTypes.KEYWORD_UNDEFINED; }
    "union"           { return ZigTypes.KEYWORD_UNION; }
    "unreachable"     { return ZigTypes.KEYWORD_UNREACHABLE; }
    "usingnamespace"  { return ZigTypes.KEYWORD_USINGNAMESPACE; }
    "var"             { return ZigTypes.KEYWORD_VAR; }
    "volatile"        { return ZigTypes.KEYWORD_VOLATILE; }
    "while"           { return ZigTypes.KEYWORD_WHILE; }


    {CHAR_LITERAL}          { return ZigTypes.CHAR_LITERAL; }
    {STRING_LITERAL_SINGLE} { return ZigTypes.STRING_LITERAL_SINGLE; }
    {LINE_STRING}           { return ZigTypes.LINE_STRING; }

    {INCOMPLETE_STRING}     { return TokenType.BAD_CHARACTER; }
    {INCOMPLETE_CHAR}       { return TokenType.BAD_CHARACTER; }

    {INTEGER_LITERAL}       { return ZigTypes.INTEGER_LITERAL; }
    {FLOAT_LITERAL}         { return ZigTypes.FLOAT_LITERAL; }

    {BUILTINIDENTIFIER}     { return ZigTypes.BUILTINIDENTIFIER; }
    {IDENTIFIER}            { return ZigTypes.IDENTIFIER; }

    "////" .*               { return ZigTypes.LINE_COMMENT; }
    {CONTAINER_DOC_COMMENT} { return ZigTypes.CONTAINER_DOC_COMMENT; }
    {DOC_COMMENT}           { return ZigTypes.DOC_COMMENT; }
    "//" .*               { return ZigTypes.LINE_COMMENT; }

    {OTHERWISE}             { return TokenType.BAD_CHARACTER; }
}

