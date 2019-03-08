package org.carrot2.dcs.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.carrot2.dcs.client.ListResponse;
import org.carrot2.language.LanguageComponents;
import org.junit.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.mockito.Mockito.when;

public class ListServletTest extends AbstractServletTest {
  @Test
  public void testGet() throws Exception {
    setupMockTemplates("template1.json", "template2.foo");

    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    when(response.getWriter()).thenReturn(pw);

    ListServlet servlet = new ListServlet();
    servlet.init(config);
    servlet.doGet(request, response);
    pw.flush();

    ObjectMapper om = new ObjectMapper();
    Assertions.assertThat(om.readValue(sw.toString(), ListResponse.class).languages)
        .containsOnlyElementsOf(LanguageComponents.languages());

    Assertions.assertThat(om.readValue(sw.toString(), ListResponse.class).algorithms)
        .containsOnly("STC", "Lingo", "Bisecting K-Means", "Dummy");

    Assertions.assertThat(om.readValue(sw.toString(), ListResponse.class).templates)
        .containsOnly("template1");
  }
}