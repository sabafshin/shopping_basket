#### author: Afshin Sabahi

## sbt project compiled with Scala 3

### Description
A simple implementation of a Shopping Cart in Scala 3. The project is compiled with sbt.
The program can take a list of items as arguments and calculate the total price of the items in the cart.
The items in the cart can have different prices and discounts.

### Requirements
- sbt 1.8.3
- Scala 3.2.2
- Java 17(tem)

### Automated Dev Environment Setup
- Install [VSCode](https://code.visualstudio.com/) and [Docker](https://www.docker.com/products/docker-desktop)
    - Install these extentions in vscode:
        - [Dev containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)
- Using the command palette (Cmd+Shift+P)/(Ctrl+Shift+P), select **Remote-Containers: Open Folder in Container...** and select the folder of this project.
    - The container will be built and VSCode will connect to it. This may take a while the first time.
    - The project will be opened in the container.
- Open the terminal
    - To compile and run
        - `sbt compile`
        - `sbt run [items]`
    - Or `sbt console` to start a Scala 3 REPL.
    - To run the tests `sbt test`

### Running the pre-built jar
- Make sure you have Java 17 installed
- Install Scala using [SDKMAN](https://sdkman.io/)
    - `sdk install scala 3.2.2`
- You can find the pre-built jar in the `releases` folder
    - Run the jar with `scala PriceBasket.jar [items]`
    - Example:
      ```
      $> scala PriceBasket.jar Apples Milk Bread
      Subtotal: £3.10
      Apples 10% off: 10p
      Total price: £3.00
        ```
    - Example with no items:
      ```
      $> scala PriceBasket.jar
      Subtotal: £0.00
      (No offers available)
      Total price: £0.00
      ```
