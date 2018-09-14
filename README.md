# `org.buffalo-coders.javaee:demo`

Welcome to `org.buffalo-coders.javaee:demo`!

## Prerequisites

  * Install: [GNU Make](https://www.gnu.org/software/make/)
  * Install: [Java SE 8, or higher](http://www.oracle.com/technetwork/java/javase/)
  * Install: [Apache Maven 3.5, or higher](https://maven.apache.org/)
  * [optional] Clone and Build: [buffalo-coders/dockerfiles](https://github.com/buffalo-coders/dockerfiles)

## Building

  * Run: `make`

## Testing

  * Run: `make test`

## Running

  * Run: `make run-backend`
  * Open: http://localhost:8080/
  * Open: http://localhost:8080/api/greetings
  * `^C` to stop backend
  * Run: `make clean install`
  * Run: `make docker-build`
  * Run: `docker-compose up -d backend`
  * Run `make run-frontend`
  * Open: http://localhost:8080/

## Miscellaneous

This project was initially created from an archetype found at
https://github.com/buffalo-coders/archetype-javaee-8
