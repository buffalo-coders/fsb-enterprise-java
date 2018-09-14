package org.buffalo.coders.javaee.demo.api;

/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/

import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.buffalo.coders.javaee.demo.GenericITest;
import org.buffalo.coders.javaee.demo.domain.Greeting;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class GreetingsResourceITest extends GenericITest {

	@Ignore
	@Test(expected = WebApplicationException.class)
	public void testCreateBad1() throws Exception {
		final String message = null;
		final Greeting greeting = new Greeting(message);

		ClientBuilder.newClient().target(url.toURI()).path("api").path("greetings").request()
				.accept(MediaType.APPLICATION_JSON).post(Entity.json(greeting), Greeting.class);
	}

	@Ignore
	@Test
	public void testCreateGood1() throws Exception {
		final String message = "TEST";
		final Greeting greeting = new Greeting(message);

		final Greeting found = ClientBuilder.newClient().target(url.toURI()).path("api").path("greetings").request()
				.accept(MediaType.APPLICATION_JSON).post(Entity.json(greeting), Greeting.class);

		Assert.assertNotNull(found);
		Assert.assertNotNull(found.getId());
		Assert.assertEquals(greeting.getMessage(), found.getMessage());
	}

	@Test
	public void testGetAll1() throws Exception {
		final List<Greeting> found = ClientBuilder.newClient().target(url.toURI()).path("api").path("greetings")
				.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<List<Greeting>>() {
				});

		Assert.assertNotNull(found);
	}

}
