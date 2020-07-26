package dataStructure.stack.problems;

import java.util.Stack;

/**

Given a string representing a code snippet, you need to implement a tag validator to parse the code and return whether it is valid.
A code snippet is valid if all the following rules hold:

1. The code must be wrapped in a valid closed tag. Otherwise, the code is invalid.
2. A closed tag (not necessarily valid) has exactly the following format : <TAG_NAME>TAG_CONTENT</TAG_NAME>.
   Among them, <TAG_NAME> is the start tag, and </TAG_NAME> is the end tag. The TAG_NAME in start and end tags should be the same.
   A closed tag is valid if and only if the TAG_NAME and TAG_CONTENT are valid.
3. A valid TAG_NAME only contain upper-case letters, and has length in range [1,9]. Otherwise, the TAG_NAME is invalid.
4. A valid TAG_CONTENT may contain other valid closed tags, cdata and any characters (see note1) EXCEPT unmatched <, unmatched start and end tag, and unmatched or closed tags with invalid TAG_NAME. Otherwise, the TAG_CONTENT is invalid.
5. A start tag is unmatched if no end tag exists with the same TAG_NAME, and vice versa. However, you also need to consider the issue of unbalanced when tags are nested.
6. A < is unmatched if you cannot find a subsequent >. And when you find a < or </, all the subsequent characters until the next > should be parsed as TAG_NAME (not necessarily valid).
7. The cdata has the following format : <![CDATA[CDATA_CONTENT]]>. The range of CDATA_CONTENT is defined as the characters between <![CDATA[ and the first subsequent ]]>.
8. CDATA_CONTENT may contain any characters. The function of cdata is to forbid the validator to parse CDATA_CONTENT, so even it has some characters that can be parsed as tag (no matter valid or invalid), you should treat it as regular characters.

Valid Code Examples:

Input: "<DIV>This is the first line <![CDATA[<div>]]></DIV>"
Output: True

Explanation: 
The code is wrapped in a closed tag : <DIV> and </DIV>. 
The TAG_NAME is valid, the TAG_CONTENT consists of some characters and cdata. 
Although CDATA_CONTENT has unmatched start tag with invalid TAG_NAME, it should be considered as plain text, not parsed as tag.
So TAG_CONTENT is valid, and then the code is valid. Thus return true.


Input: "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>"
Output: True

Explanation:
We first separate the code into : start_tag|tag_content|end_tag.
start_tag -> "<DIV>"
end_tag -> "</DIV>"
tag_content could also be separated into : text1|cdata|text2.
text1 -> ">>  ![cdata[]] "
cdata -> "<![CDATA[<div>]>]]>", where the CDATA_CONTENT is "<div>]>"
text2 -> "]]>>]"
The reason why start_tag is NOT "<DIV>>>" is because of the rule 6.
The reason why cdata is NOT "<![CDATA[<div>]>]]>]]>" is because of the rule 7.


Invalid Code Examples:

Input: "<A>  <B> </A>   </B>"
Output: False
Explanation: Unbalanced. If "<A>" is closed, then "<B>" must be unmatched, and vice versa.

Input: "<DIV>  div tag is not closed  <DIV>"
Output: False

Input: "<DIV>  unmatched <  </DIV>"
Output: False

Input: "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>"
Output: False

Input: "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>"
Output: False

Input: "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>"
Output: False

Note:
For simplicity, you could assume the input code (including the any characters mentioned above) only contain letters, digits, '<','>','/','!','[',']' and ' '.


Referrence:
https://leetcode.com/problems/tag-validator/solution/

*/
public class TagValidator {
  static final char OPEN_ANGLE_BRACKET = '<';
  static final char CLOSE_ANGLE_BRACKET = '>';
  static final char EXCLAMATION_POINT = '!';
  static final char SLASH = '/';
  static final String OPEN_CDATA = "[CDATA[";
  static final String CLOSE_CDATA = "]]";
  Stack<Tag> stack = new Stack<>();

  class Tag {
    String open;
    String close;

    Tag(String open, String close) {
      this.open = open;
      this.close = close;
    }
  }

  public boolean isValid(String code) {
    int length = code.length();
    if (length == 0) return false;
    if (code.charAt(0) != OPEN_ANGLE_BRACKET || code.charAt(length - 1) != CLOSE_ANGLE_BRACKET) return false;

    for (int i = 0; i < length; i++) {
      char c = code.charAt(i);

      if (c == OPEN_ANGLE_BRACKET) {
        boolean hasExclamationPoint = code.indexOf(EXCLAMATION_POINT, i) == i + 1;
        if (hasExclamationPoint) {
          // check CDATA
          i = ValidateCData(code, i + 2);
          if (i == -1) {
            return false;
          }
        } else {
          // TAG_NAME
          boolean hasSlash = i + 1 < length ? code.charAt(i + 1) == SLASH : false;
          if (hasSlash) {
            // close Tag
            if (!stack.isEmpty()) {
              int closeTagIndex = code.indexOf(CLOSE_ANGLE_BRACKET, i + 1);
              if (closeTagIndex == -1) return false;

              Tag tag = stack.peek();
              if (
                !tag.close.equals("<" + code.substring(i + 1, closeTagIndex) + ">") // close tag must match
              ) {
                return false;
              }
              stack.pop();
              i = closeTagIndex;

              if (i == (length - 1)) {
                return stack.isEmpty();
              }
              continue;
            }

            // do not have any open tag
            return false;
          } 

          // open Tag
          int closeTagIndex = code.indexOf(CLOSE_ANGLE_BRACKET, i);
          if (closeTagIndex == -1 || !ValidateTagName(code, i + 1, closeTagIndex)) {
            return false;
          }
          // open tag and stack is empty means that <A></A><B></B>
          if (i > 0 && stack.isEmpty()) return false;

          Tag tag = new Tag("<" + code.substring(i + 1, closeTagIndex) + ">", "</" + code.substring(i + 1, closeTagIndex) + ">");
          stack.push(tag);
          i = closeTagIndex;
        }
      }
    }

    return false;
  }

  public boolean ValidateTagName(String code, int fromIndex, int endIndex) {
    for (int i = fromIndex; i < endIndex; i++) {
      char c = code.charAt(i);
      if (c < 'A' || c > 'Z') return false;
    }
    return 0 < (endIndex - fromIndex) && (endIndex - fromIndex) <= 9;
  }

  public int ValidateCData(String code, int fromIndex) {
    int openCDATAIndex = code.indexOf(OPEN_CDATA, fromIndex + 2);
    boolean hasOpenCDATA = openCDATAIndex == (fromIndex + 2);
    if (!hasOpenCDATA) return -1;

    int closeCDATAIndex = code.indexOf(CLOSE_CDATA + ">", fromIndex + 2 + OPEN_CDATA.length());
    if (
      closeCDATAIndex == -1 ||
      closeCDATAIndex < (openCDATAIndex + OPEN_CDATA.length()) ||
      closeCDATAIndex >= code.length()
    ) {
      return -1;
    }
    return closeCDATAIndex + CLOSE_CDATA.length();
  }

  public static void main(String[] args) {
    String input = "<AAAAAAAAAA></AAAAAAAAAA>";
    boolean output = new TagValidator().isValid(input);

    System.out.println(output);
  }
}