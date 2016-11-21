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

import com.google.auto.service.AutoService;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.NewClassTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.google.errorprone.matchers.Matchers;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.NewClassTree;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Bug checker to detect instantiation of {@link OutputStreamWriter} without specifying charset.
 */
@AutoService(BugChecker.class)
@BugPattern(
    name = "OutputStreamWriterWithoutCharset",
    summary = "Charset must be specified when constructing a new java.io.OutputStreamWriter.",
    category = JDK,
    severity = ERROR)
public class OutputStreamWriterWithoutCharset
    extends BugChecker
    implements NewClassTreeMatcher {

  private static final String OUTPUT_STREAM = OutputStream.class.getName();
  private static final String OUTPUT_STREAM_WRITER = OutputStreamWriter.class.getName();

  private static final Matcher<ExpressionTree> constructor =
      Matchers.constructor().forClass(OUTPUT_STREAM_WRITER).withParameters(OUTPUT_STREAM);

  @Override
  public Description matchNewClass(NewClassTree tree, VisitorState state) {
    if (!constructor.matches(tree, state)) {
      return Description.NO_MATCH;
    }

    Description.Builder builder = buildDescription(tree);

    String classIdentifier = state.getSourceForNode(tree.getIdentifier());
    String streamParam = state.getSourceForNode(tree.getArguments().get(0));

    for (String charset : CHARSET_SUGGESTIONS) {
      String suggestion = format("new %s(%s, %s)", classIdentifier, streamParam, charset);
      builder.addFix(replace(tree, suggestion));
    }
    return builder.build();
  }
}
