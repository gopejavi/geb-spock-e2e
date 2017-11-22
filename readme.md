# Example Spock-Geb E2E tests
[![CircleCI](https://circleci.com/gh/gopejavi/geb-spock-e2e.svg?style=svg)](https://circleci.com/gh/gopejavi/geb-spock-e2e)

## Description

This is an example of E2E acceptance tests, done with the stack Spock-Geb (Groovy) over a Phalcon Framework example applicaton.
For this example project Trello was used to simulate sprints and User Stories with Acceptante Criteria.
Also, CircleCI is used to pass the builds. Two Docker containers are also used: one for the app to test, another for the testing framework (this project).

## Usage

1. If you don't have it already, install Docker.
2. With Docker installed (or from the Docker Terminal in Windows) run:
`docker run -p 80:80 -p 3306:3306 --name vokuro_container gopejavi/vokuro`. Be sure where the Vokuro project is running: try 127.0.0.1 in your browser (if not, 192.168.99.100). You will see a web named Vokuro.
4. Clone this project (tests can be launched locally).
5. Run the tests: in the root of the project, `.../geb-spock-e2e`, execute: 
`./gradlew -P baseUrl=http://VOKURO_IP BROWSER_TEST` where VOKURO_IP is the IP where you see your Vokuro web and BROWSER_TEST is `chromeTest`, `firefoxTest` or `chromeHeadlessTest`. This last one will launch the tests on Chrome without rendering the windows on screen. Very useful for continuous integration based on Linux distros without GUI.
6. After the tests are run, you can find the reports in `.../geb-spock-e2e/build/spock-reports/index.html`

You can also run particular tests by name or by definition, using for example:
`./gradlew --info -P baseUrl=http://VOKURO_IP chromeTest --tests *CreateUser*`
This will run, providing more info (--info) about the state of the execution (useful if any error happens), and will execute only the files containing CreateUser with any set of characters before and after. It also searches in all files for every scenario defined in every file and it will execute scenarios from other files with the same criteria.

When you end, you can stop the container with:
`docker container stop vokuro_container`

## Thanks

Thanks to Geb and the original base repo! https://github.com/geb/geb-example-gradle

## Improvements

If you think that something is wrong or can be improved, first be sure it is about this fork and not the original base project. Then, if it is about this fork, send the issue or Pull Request via GitHub.

Thank you!
