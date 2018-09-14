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

package org.buffalo.coders.javaee.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "message" }) })
public class Greeting extends GenericEntity {

	private static final int JVC_MESSAGE_SIZE_MAX = 128;

	@Column(nullable = false, length = JVC_MESSAGE_SIZE_MAX)
	@NotNull
	@Size(max = JVC_MESSAGE_SIZE_MAX)
	private String message;

	protected Greeting() {
		// required for JPA
	}

	public Greeting(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Greeting [id=" + id + ", message=" + message + ", version=" + version + "]";
	}

}