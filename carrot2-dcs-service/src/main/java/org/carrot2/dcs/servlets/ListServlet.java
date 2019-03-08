package org.carrot2.dcs.servlets;

import org.carrot2.dcs.client.ListResponse;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class ListServlet extends RestEndpoint {
  private DcsContext dcsContext;
  private ListResponse defaultResponse;

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);

    dcsContext = DcsContext.load(config.getServletContext());
    defaultResponse = new ListResponse(
        new ArrayList<>(dcsContext.algorithmSuppliers.keySet()),
        new ArrayList<>(dcsContext.languages.keySet()),
        new ArrayList<>(dcsContext.templates.keySet()));
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    writeJsonResponse(response, shouldIndent(request), defaultResponse);
  }
}
