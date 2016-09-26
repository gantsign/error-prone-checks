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

import static com.google.common.collect.Maps.immutableEntry;
import static com.google.errorprone.BugPattern.Category.JDK;
import static com.google.errorprone.BugPattern.MaturityLevel.MATURE;
import static com.google.errorprone.BugPattern.SeverityLevel.ERROR;
import static com.google.errorprone.fixes.SuggestedFix.replace;
import static com.google.errorprone.matchers.Matchers.methodInvocation;
import static com.google.errorprone.matchers.Matchers.staticMethod;
import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.BugPattern;
import com.google.errorprone.VisitorState;
import com.google.errorprone.bugpatterns.BugChecker;
import com.google.errorprone.bugpatterns.BugChecker.MethodInvocationTreeMatcher;
import com.google.errorprone.matchers.Description;
import com.google.errorprone.matchers.Matcher;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.LiteralTree;
import com.sun.source.tree.MethodInvocationTree;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Bug checker to detect usage of {@link Charset#forName(String)} that can be replaced with
 * {@link StandardCharsets}.
 */
@AutoService(BugChecker.class)
@BugPattern(
    name = "CharsetForStandardCharset",
    summary =
        "Must use java.nio.charset.StandardCharsets where possible.",
    category = JDK,
    severity = ERROR,
    maturity = MATURE)
public class CharsetForStandardCharset
    extends BugChecker
    implements MethodInvocationTreeMatcher {

  private static final String STANDARD_CHARSETS = StandardCharsets.class.getName();
  private static final String CHARSET = Charset.class.getName();
  private static final String STRING = String.class.getName();

  private static final Matcher<ExpressionTree> method = methodInvocation(
      staticMethod().onClass(CHARSET).named("forName").withParameters(STRING));

  private static final ImmutableMap<Charset, String> STANDARD_CHARSET_FIELD_MAP =
      ImmutableMap.<Charset, String>builder()
          .put(StandardCharsets.US_ASCII, "US_ASCII")
          .put(StandardCharsets.ISO_8859_1, "ISO_8859_1")
          .put(StandardCharsets.UTF_8, "UTF_8")
          .put(StandardCharsets.UTF_16BE, "UTF_16BE")
          .put(StandardCharsets.UTF_16LE, "UTF_16LE")
          .put(StandardCharsets.UTF_16, "UTF_16")
          .build();

  private static final ImmutableMap<String, Charset> STANDARD_CHARSET_MAP =
      STANDARD_CHARSET_FIELD_MAP.keySet()
          .stream()
          .map(charset ->
              ImmutableMap.<String, Charset>builder()
                  .put(charset.name(), charset)
                  .putAll(
                      charset.aliases()
                          .stream()
                          .collect(toMap(identity(), alias -> charset)))
                  .build())
          .map(ImmutableMap::entrySet)
          .flatMap(ImmutableSet::stream)
          .map(entry -> immutableEntry(entry.getKey().toLowerCase(), entry.getValue()))
          .collect(collectingAndThen(toList(), ImmutableMap::copyOf));

  private static boolean isStandardCharset(String name) {
    return STANDARD_CHARSET_MAP.containsKey(requireNonNull(name).toLowerCase());
  }

  private static String fieldNameForCharset(String name) {
    Charset charset = STANDARD_CHARSET_MAP.get(requireNonNull(name).toLowerCase());
    return STANDARD_CHARSET_FIELD_MAP.get(requireNonNull(charset));
  }

  @Override
  public Description matchMethodInvocation(MethodInvocationTree tree, VisitorState state) {
    if (!method.matches(tree, state)) {
      return Description.NO_MATCH;
    }

    ExpressionTree name = tree.getArguments().get(0);
    if (!(name instanceof LiteralTree)) {
      return Description.NO_MATCH;
    }

    LiteralTree literal = (LiteralTree) name;
    String value = (String) literal.getValue();
    if (!isStandardCharset(value)) {
      return Description.NO_MATCH;
    }

    Description.Builder builder = buildDescription(tree);

    String fieldName = requireNonNull(fieldNameForCharset(value));

    String suggestion = format("%s.%s", STANDARD_CHARSETS, fieldName);
    builder.addFix(replace(tree, suggestion));
    return builder.build();
  }

}
