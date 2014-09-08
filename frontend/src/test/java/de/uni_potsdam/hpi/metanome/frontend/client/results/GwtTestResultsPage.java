/*
 * Copyright 2014 by the Metanome project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.uni_potsdam.hpi.metanome.frontend.client.results;


import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;

import de.uni_potsdam.hpi.metanome.frontend.client.BasePage;
import de.uni_potsdam.hpi.metanome.frontend.client.TabWrapper;
import de.uni_potsdam.hpi.metanome.frontend.client.TestHelper;
import de.uni_potsdam.hpi.metanome.frontend.client.services.ExecutionService;
import de.uni_potsdam.hpi.metanome.frontend.client.services.ExecutionServiceAsync;

public class GwtTestResultsPage extends GWTTestCase {

  /**
   * Test method for {@link de.uni_potsdam.hpi.metanome.frontend.client.results.ResultsPage#ResultsPage(de.uni_potsdam.hpi.metanome.frontend.client.BasePage)}
   */
  public void testResultTable() {
    // Set up
    TestHelper.resetDatabaseSync();

    BasePage parent = new BasePage();

    // Expected Values
    // Execute
    ResultsPage page = new ResultsPage(parent);

    // Check
    assertNotNull(page.basePage);
    assertTrue(page.getWidget(0) instanceof Label);

    // Cleanup
    TestHelper.resetDatabaseSync();
  }

  /**
   * Test method for {@link ResultsPage#startPolling()}
   */
  public void testStartPolling() {
    // Set up
    TestHelper.resetDatabaseSync();

    BasePage parent = new BasePage();
    ResultsPage page = new ResultsPage(parent);

    ExecutionServiceAsync executionService = GWT.create(ExecutionService.class);
    page.setExecutionParameter(executionService, "identifier", "name");

    // Expected Values
    // Execute
    page.startPolling();

    // Check
    assertEquals(3, page.getWidgetCount());
    assertNotNull(page.runningIndicator);
    assertNotNull(page.progressBar);

    // Cleanup
    TestHelper.resetDatabaseSync();
  }


  /**
   * Test method for {@link ResultsPage#updateOnError(String)}
   */
  public void testUpdateOnError() {
    // Set up
    TestHelper.resetDatabaseSync();

    BasePage parent = new BasePage();
    ResultsPage page = new ResultsPage(parent);
    page.setMessageReceiver(new TabWrapper());
    page.timer = new Timer() {
      @Override
      public void run() {

      }
    };

    // Expected Values
    // Execute
    page.updateOnError("message");

    // Check
    assertEquals(1, page.getWidgetCount());
    assertTrue(page.getWidget(0) instanceof Label);

    // Cleanup
    TestHelper.resetDatabaseSync();
  }

  /**
   * Test method for {@link ResultsPage#updateOnSuccess(ResultsTablePage, ResultsVisualizationPage,
   * Long)}
   */
  public void testUpdateOnSuccess() {
    // Set up
    TestHelper.resetDatabaseSync();

    BasePage parent = new BasePage();
    ResultsPage page = new ResultsPage(parent);
    page.timer = new Timer() {
      @Override
      public void run() {

      }
    };

    ExecutionServiceAsync executionService = GWT.create(ExecutionService.class);
    ResultsTablePage tablePage = new ResultsTablePage(executionService, "identifier");
    ResultsVisualizationPage visualizationPage = new ResultsVisualizationPage();

    page.setExecutionParameter(executionService, "identifier", "name");

    // Expected Values
    // Execute
    page.updateOnSuccess(tablePage, visualizationPage, (long) 4543);

    // Check
    assertEquals(2, page.getWidgetCount());
    assertTrue(page.getWidget(1) instanceof TabLayoutPanel);
    assertEquals(2, ((TabLayoutPanel) page.getWidget(1)).getWidgetCount());

    // Cleanup
    TestHelper.resetDatabaseSync();
  }


  @Override
  public String getModuleName() {
    return "de.uni_potsdam.hpi.metanome.frontend.MetanomeTest";
  }

}