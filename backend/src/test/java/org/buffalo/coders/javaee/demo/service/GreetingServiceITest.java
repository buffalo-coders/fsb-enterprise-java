package org.buffalo.coders.javaee.demo.service;

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

import java.util.Collections;

import javax.inject.Inject;
import javax.ejb.EJBException;

import org.buffalo.coders.javaee.demo.GenericITest;
import org.buffalo.coders.javaee.demo.domain.Greeting;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class GreetingServiceITest extends GenericITest {

	@Inject
	private GreetingService greetingService;

	@Ignore
	@Test(expected = EJBException.class)
	public void testCreateBad1() {
		final String message = null;
		greetingService.create(new Greeting(message));
	}

	@Ignore
	@Test(expected = EJBException.class)
	public void testCreateBad2() {
		final String message = String.join("", Collections.nCopies(129, "x"));
		greetingService.create(new Greeting(message));
	}

	@Test
	public void testCreateGood1() {
		final String message = "TEST";
		final Greeting greeting = greetingService.create(new Greeting(message));
		Assert.assertNotNull(greeting);
		Assert.assertNotNull(greeting.getId());
		Assert.assertEquals(message, greeting.getMessage());
	}

	@Test
	public void testCreateGood2() {
		final String message = String.join("", Collections.nCopies(128, "x"));
		greetingService.create(new Greeting(message));
	}

}
