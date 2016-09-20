package com.github.gantsign.errorprone.require.charset;

import static com.google.errorprone.BugPattern.Category.JDK;
import static com.google.errorprone.BugPattern.MaturityLevel.MATURE;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.sun.source.tree.MethodTree;

/**
 * Bug checker to detect usage of {@link String#String(byte[])}.
 */
@AutoService(BugChecker.class)
@BugPattern(
    name = "NewStringWithoutCharset",
    summary = "Charset must be specified when constructing a new String from a byte array.",
    category = JDK,
    severity = ERROR,
    maturity = MATURE)
public class NewStringWithoutCharset
    extends BugChecker
    implements MethodTreeMatcher {

  @Override
  public Description matchMethod(MethodTree tree, VisitorState state) {

    // TODO
    return Description.NO_MATCH;
  }
}
