/** Essential Carrot<sup>2</sup> infrastructure, interfaces and implementations. */
module org.carrot2.core {
  // javadoc expects a different folder structure.
  // https://stackoverflow.com/questions/49476559/java-9-error-not-in-a-module-on-the-module-source-path
  requires transitive com.carrotsearch.hppc;

  exports org.carrot2.attrs;
  exports org.carrot2.clustering;
  exports org.carrot2.clustering.kmeans;
  exports org.carrot2.clustering.lingo;
  exports org.carrot2.clustering.stc;
  exports org.carrot2.language;
  exports org.carrot2.language.snowball;
  exports org.carrot2.math;
  exports org.carrot2.math.matrix;
  exports org.carrot2.text.preprocessing;
  exports org.carrot2.text.suffixtree;
  exports org.carrot2.text.vsm;
  exports org.carrot2.util;
}
