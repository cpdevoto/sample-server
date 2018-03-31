/**
 * Copyright (C) 2012-2018 the original author or authors.
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

/**
 * Copyright (C) 2013 the original author or authors.
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

package controllers;


import org.doctester.testbrowser.Request;
import org.doctester.testbrowser.Response;
import org.hamcrest.CoreMatchers;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.RuleChain;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.containsString;

import ninja.NinjaDocTester;
import testutils.rules.dockerdb.DatabaseSeeder;
import testutils.rules.dockerdb.DockerDatabase;

public class ApiControllerDocTesterTest extends NinjaDocTester {

  private static final DockerDatabase dockerDatabase = new DockerDatabase();
  
  private static final DatabaseSeeder seeder = DatabaseSeeder.builder()
      .withSeedScript(ApiControllerDocTesterTest.class, "base-data.sql")
      .withTearDownScript(ApiControllerDocTesterTest.class, "base-teardown.sql")
      .build();

  @ClassRule
  public static final RuleChain CHAIN = RuleChain
      .outerRule(dockerDatabase)
      .around(seeder);
  

  String URL_INDEX = "/";
  String URL_HELLO_WORLD_JSON = "/hello_world.json";
  String URL_USERS_INDEX = "/api/v1/users";

  @Test
  public void testGetIndex() {

    Response response = makeRequest(
        Request.GET().url(
            testServerUrl().path(URL_INDEX)));

    assertThat(response.payload, containsString("Wantify!"));

  }

  @Test
  public void testGetHelloWorldJson() {

    Response response = makeRequest(
        Request.GET().url(
            testServerUrl().path(URL_HELLO_WORLD_JSON)));

    ApplicationController.SimplePojo simplePojo =
        response.payloadJsonAs(ApplicationController.SimplePojo.class);

    assertThat(simplePojo.content, CoreMatchers.equalTo("Hello World! Hello Json!!"));

  }

  @Test
  public void testGetUsersIndex() {

    Response response = makeRequest(
        Request.GET().url(
            testServerUrl().path(URL_USERS_INDEX)));

    assertThat(response.payload, containsString("cpdevoto@gmail.com"));

  }
  
}
