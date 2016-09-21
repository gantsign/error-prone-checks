/*
 * Copyright 2016 GantSign Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.github.gantsign.errorprone.require.charset;

import static com.github.gantsign.errorprone.require.charset.CharsetSuggestions.CHARSET_SUGGESTIONS;
import static com.google.errorprone.BugPattern.Category.JDK;
import static com.google.errorprone.BugPattern.MaturityLevel.MATURE;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.fixes.SuggestedFix.replace;
import static com.google.errorprone.matchers.Matchers.instanceMethod;
import static com.google.errorprone.matchers.Matchers.methodInvocation;
import static java.lang.String.format;

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.MethodInvocationTree;

/**
 * Bug checker to detect usage of {@link String#getBytes()}.
 */
@AutoService(BugChecker.class)
@BugPattern(
    name = "GetBytesWithoutCharset",
    summary =
        "java.nio.charset.Charset must be specified when calling java.lang.String.getBytes().",
    category = JDK,
    severity = ERROR,
    maturity = MATURE)
public class GetBytesWithoutCharset
    extends BugChecker
    implements MethodInvocationTreeMatcher {

  private static final String STRING = String.class.getName();

  private static final Matcher<ExpressionTree> method = methodInvocation(
      instanceMethod().onExactClass(STRING).named("getBytes").withParameters());

  @Override
  public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
    if (!method.matches(tree, state)) {
      return Description.NO_MATCH;
    }

    String methodSelect = state.getSourceForNode(tree.getMethodSelect());

    Description.Builder builder = buildDescription(tree);

    for (String charset : CHARSET_SUGGESTIONS) {
      String suggestion = format("%s(%s)", methodSelect, charset);
      builder.addFix(replace(tree, suggestion));
    }
    return builder.build();
  }
}
