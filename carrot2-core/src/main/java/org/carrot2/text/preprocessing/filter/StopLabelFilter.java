
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2019, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.text.preprocessing.filter;

import org.carrot2.language.LexicalData;
import org.carrot2.text.preprocessing.LabelFormatter;
import org.carrot2.text.preprocessing.PreprocessingContext;
import org.carrot2.attrs.AttrBoolean;

/**
 * Accepts labels that are not declared as stop labels in the stoplabels.&lt;lang&gt;
 * files.
 */
public class StopLabelFilter extends SingleLabelFilterBase {
  /**
   * Remove stop labels. Removes labels that are declared as stop labels in the
   * stoplabels.&lt;lang&gt; files. Please note that adding a long list of regular
   * expressions to the stoplabels file may result in a noticeable performance penalty.
   */
  public AttrBoolean enabled = attributes.register("enabled", AttrBoolean.builder()
      .label("Remove stop labels")
      .defaultValue(true));

  private LabelFormatter labelFormatter = new LabelFormatter();
  private LexicalData lexicalData;

  @Override
  public void filter(PreprocessingContext context, boolean[] acceptedStems, boolean[] acceptedPhrases) {
    lexicalData = context.languageComponents.lexicalData;
    super.filter(context, acceptedStems, acceptedPhrases);
  }

  @Override
  public boolean acceptPhrase(PreprocessingContext context, int phraseIndex) {
    final String formatedLabel = labelFormatter.format(context, phraseIndex + context.allWords.image.length);
    return !lexicalData.ignoreLabel(formatedLabel);
  }

  @Override
  public boolean acceptWord(PreprocessingContext context, int wordIndex) {
    final String formattedLabel = labelFormatter.format(context, wordIndex);
    return !lexicalData.ignoreLabel(formattedLabel);
  }

  public boolean isEnabled() {
    return enabled.get();
  }
}
