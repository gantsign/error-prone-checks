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
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.fixes.SuggestedFix.replace;
import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.NewClassTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.matchers.Matchers;
import com.google.errorprone.suppliers.Supplier;
import com.google.errorprone.suppliers.Suppliers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.NewClassTree;
import com.sun.tools.javac.code.Type;

/**
 * Bug checker to detect usage of {@link String#String(byte[])} and
 * {@link String#String(byte[], int, int)}.
 */
@AutoService(BugChecker.class)
@BugPattern(
    name = "NewStringWithoutCharset",
    summary = "java.nio.charset.Charset must be specified when constructing a new java.lang.String"
        + " from a byte array.",
    category = JDK,
    severity = ERROR)
public class NewStringWithoutCharset
    extends BugChecker
    implements NewClassTreeMatcher {

  private static final String STRING = String.class.getName();

  private static final Supplier<Type> BYTE_ARRAY = Suppliers.arrayOf(Suppliers.BYTE_TYPE);

  private static final Matcher<ExpressionTree> constructors = Matchers.anyOf(
      Matchers.constructor().forClass(STRING).withParameters(ImmutableList.of(BYTE_ARRAY)),
      Matchers.constructor().forClass(STRING).withParameters(
          ImmutableList.of(BYTE_ARRAY, Suppliers.INT_TYPE, Suppliers.INT_TYPE)));

  @Override
  public Description matchNewClass(NewClassTree tree, VisitorState state) {
    if (!constructors.matches(tree, state)) {
      return Description.NO_MATCH;
    }

    Description.Builder builder = buildDescription(tree);

    String classIdentifier = state.getSourceForNode(tree.getIdentifier());

    String args = tree.getArguments()
        .stream()
        .map(state::getSourceForNode)
        .collect(joining(", "));

    for (String charset : CHARSET_SUGGESTIONS) {
      String suggestion = format("new %s(%s, %s)", classIdentifier, args, charset);
      builder.addFix(replace(tree, suggestion));
    }
    return builder.build();
  }
}
