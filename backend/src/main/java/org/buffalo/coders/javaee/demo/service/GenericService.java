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

package org.buffalo.coders.javaee.demo.service;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.buffalo.coders.javaee.demo.domain.GenericEntity;

public abstract class GenericService<T extends GenericEntity> {

	@Inject
	private Logger log;

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager manager;

	public T create(@NotNull @Valid final T entity) {
		entity.setId(null);
		entity.setVersion(null);
		log.info(() -> "CREATE: entity=" + entity);
		manager.persist(entity);
		return entity;
	}

	private T delete(@NotNull final T entity) {
		log.info(() -> "DELETE: entity=" + entity);
		manager.remove(manager.merge(entity));
		return entity;
	}

	public T deleteById(@NotNull final Long id) {
		log.info(() -> "DELETE: id=" + id);
		return delete(getById(id));
	}

	public List<T> getAll() {
		log.info(() -> "GET ALL");
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(getGenericEntityClass());
		final Root<T> root = query.from(getGenericEntityClass());
		return manager.createQuery(query.select(root)).getResultList();
	}

	public T getById(@NotNull final Long id) {
		log.info(() -> "GET: id=" + id);
		final CriteriaBuilder builder = manager.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(getGenericEntityClass());
		final Root<T> root = query.from(getGenericEntityClass());
		return manager.createQuery(query.select(root).where(builder.equal(root.get("id"), id))).getSingleResult();
	}

	public abstract Class<T> getGenericEntityClass();

	public T update(@NotNull @Valid final T entity) {
		log.info(() -> "UPDATE: entity=" + entity);
		return manager.merge(entity);
	}

}