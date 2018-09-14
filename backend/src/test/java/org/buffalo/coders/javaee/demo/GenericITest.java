package org.buffalo.coders.javaee.demo;

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

import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public abstract class GenericITest {

	protected static final Logger LOG = Logger.getAnonymousLogger();

	@Deployment
	public static WebArchive createDeployment() {
		final WebArchive war = ShrinkWrap.create(WebArchive.class);

		war.addAsManifestResource(new ClassLoaderAsset("META-INF/beans.xml"), "beans.xml");
		war.addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
		war.addPackages(true, "org.buffalo.coders.javaee.demo");
		war.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
				.resolve(Arrays.asList("javax.servlet:jstl", "taglibs:standard")).withTransitivity().asFile());

		if (LOG.isLoggable(Level.FINE)) {
			LOG.fine(war.toString(true));
		}

		return war;
	}

	@ArquillianResource
	protected URL url;

}
