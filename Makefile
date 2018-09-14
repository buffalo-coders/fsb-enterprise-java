#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#   http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#

default: verify

.PHONY: clean
clean:
	@mvn clean

.PHONY: deploy-local
deploy-local:
	@mvn -Dmaven.test.skip=true -DskipTests=true -DaltDeploymentRepository=releases::default::http://archiva.buffalo-coders.org:7000/repository/internal/ deploy

.PHONY: docker
docker: docker-build

.PHONY: docker-build
docker-build:
	@mvn --projects backend,frontend exec:exec@docker-build

.PHONY: docker-push
docker-push:
	@mvn --projects backend,frontend exec:exec@docker-push

.PHONY: install
install:
	@mvn install

.PHONY: sonar
sonar:
	@mvn -Dsonar.host.url=http://sonarqube.buffalo-coders.org:9000 sonar:sonar

.PHONY: run-backend
run-backend:
	@mvn --projects backend package wildfly:run

.PHONY: run-frontend
run-frontend:
	@mvn --projects frontend package wildfly:run

.PHONY: test
test: test-ut test-it

.PHONY: test-ut
test-ut: verify

.PHONY: test-it
test-it:
	@mvn --activate-profiles run-it verify

.PHONY: test-uat
test-uat:
	@mvn --activate-profiles run-uat verify

.PHONY: verify
verify:
	@mvn verify
