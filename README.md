# Java-Selenium-Ecommerce project

### Requirements
* Java 17
* Maven 3.5.4
* Docker (if running in container)

### How to run tests on local cmd line
* Build project:
    * `mvn compile`
* Run all tests:
    * `mvn test`
* Run tests by specific tag:
    * `mvn test -Dgroups="guest"`

### How to run tests remote on Docker container
* Open Docker
* Run docker container with:
  * Chromium
    * docker run --rm -it -p 4444:4444 --shm-size 2g seleniarm/standalone-chromium:latest
  * Chrome (this is not working on M1 chip laptops)
    * docker run -d -p 4444:4444 --shm-size="2g" selenium/standalone-chrome:latest
* Run tests from cmd line: mvn test

### Tech stack

* Java & Maven & Selenium
* WebDriverManager for chrome driver management
* Faker for test data
* Junit as test framework
* Page Object Model implementation
* Screenshots
* Environment parameters
* Log4j logging
* Git
* Docker integration using dockerfile/docker compose files
* CI/CD integration using GitHub & Docker

## Additional test scenarios for Order Placement

### For this particular website, most payment error handling is ignored

1. Place a successful order with different types of skus (for example, bundles, gears, clothing)
2. Place an order with free shipping
3. Place an order with an expired cart
4. Place an order with an expired user session
5. Place an order with billing address different from shipping
6. Place an order with an applied coupon
7. PLace orders with all shipping methods
8. Sign in from checkout and place an order
9. Checkout navigation - Click on shipping step from Review & Payments step
10. Reorder feature from my account
11. Take order through all statuses - first is pending, all the way to completed/reject order

### If we consider creating a shipping address as part of the oder placement feature:

1. Create negative scenarios to trigger fields validations, like required/invalid field errors
2. Edit shipping method
3. Edit shipping address


