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

package org.buffalo.coders.javaee.demo.api;

import javax.ejb.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.buffalo.coders.javaee.demo.domain.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("Greetings")
@Consumes(MediaType.APPLICATION_JSON)
@Path("greetings")
@Produces(MediaType.APPLICATION_JSON)
@Singleton
public class GreetingsResource extends GenericResource {

	@ApiOperation(value = "Create a new Greeting.", response = Greeting.class)
	@POST
	public Response create(@ApiParam(required = true) @NotNull @Valid final Greeting greeting) {
		return Response.ok(greetingService.create(greeting)).build();
	}

	@ApiOperation(value = "Delete a Greeting by id.", response = Greeting.class)
	@DELETE
	@Path("{id}")
	public Response deleteById(@ApiParam(required = true) @PathParam("id") final Long id) {
		return Response.ok(greetingService.deleteById(id)).build();
	}

	@ApiOperation(value = "Get all Greetings.", response = Greeting.class, responseContainer = "List")
	@GET
	public Response getAll() {
		return Response.ok(greetingService.getAll()).build();
	}

	@ApiOperation(value = "Get a Greeting by id.", response = Greeting.class)
	@GET
	@Path("{id}")
	public Response getById(@ApiParam(required = true) @PathParam("id") final Long id) {
		return Response.ok(greetingService.getById(id)).build();
	}

	@ApiOperation(value = "Update a Greeting by id.", response = Greeting.class)
	@PUT
	@Path("{id}")
	public Response updateById(@ApiParam(required = true) @PathParam("id") final Long id,
			@ApiParam(required = true) @NotNull @Valid final Greeting greeting) {
		final Greeting existing = greetingService.getById(id);
		greeting.setId(existing.getId());
		greeting.setVersion(existing.getVersion());
		return Response.ok(greetingService.update(greeting)).build();
	}

}
