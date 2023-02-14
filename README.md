# Java, Junit and Selenium Test Automation Project

## Description:

This project was developed based on the [SauceDemo Website](https://www.saucedemo.com/) to ensure the BuyFlow of distinct users. Therefore, were created some E2E test cases related to positive and negative scenarios.

The tests are performed using a cross-browser approach and at that moment are considering the following browsers:

- Google Chrome, Firefox and Microsoft Edge.

Any additional browser is possible to run, just adding it in the BaseTest class.

## Getting Started

### Dependencies

The stack used for that is Java (11.0.12 or higher), Maven(3.8.4 or higher), Docker (20.10.20 or higher), Junit 4 and Selenium 4. 

However, to run the project it is only necessary to install Make and Docker.

### How to Run

The automated tests are performed using Junit and Selenium Grid to run in a parallel and parameterized way and with all declared browsers. So, for that is just necessary to deploy selenium grid (http://localhost:4444/ui) to make available all these browsers in specific nodes. In addition, this docker-compose file will build the app based on the maven project and will run all tests.

To execute the docker-compose commands, just type:

```bash
make up
```

### Test Results

To get all output files copy them from docker container to your machine:

```bash
docker cp <<docker_container>>:<<DOCKER_FOLDER>><<DIR_LOCAL_MACHINE>>
```
Example: 

```bash
docker cp 4bebbe8a8632:/sample C:/test
```

The test report is generated in the following path: target/site/index.html and all screenshots are stored in src/test/resources/screenshots

## Maintainers

Contributors names and contact info

If you have questions or suggestions, please contact the current maintainers: andrevdrodrigues@gmail.com
