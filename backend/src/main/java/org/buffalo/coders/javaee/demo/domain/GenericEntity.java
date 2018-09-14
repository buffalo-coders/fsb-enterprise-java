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

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GenericEntity {

	@Id
	@GeneratedValue
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	protected Date lastModified;

	@Version
	protected Long version;

	public Long getId() {
		return id;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public Long getVersion() {
		return version;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setLastModified(final Date lastModified) {
		this.lastModified = lastModified;
	}

	public void setVersion(final Long version) {
		this.version = version;
	}

	@PrePersist
	@PreUpdate
	protected void updateLastModified() {
		setLastModified(new Date());
	}

}
