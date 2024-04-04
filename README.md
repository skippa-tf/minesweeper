![java_muPnFBE3is](https://github.com/skippa-tf/minesweeper/assets/13388340/483a6d82-0f4e-4953-9381-cd348805994a)
## Summary
This is a fully functioning clone of Microsoft's Minesweeper I created for my programming II class.
There is a minor difference in appearance but I did my best to make it function / look the exact same way. To create the UI I used Gluon's Scenebuilder, and then made my Main class my controller class for the FXMLLoader.

## Setup
- Use the attached Maven `pom.xml` to set up the required dependencies.
- Ensure the `FXMLLoader` can locate the `ui.fxml` file under the resources folder; otherwise, the UI will not be built.
- Pay attention to where you place the `ui.fxml` file and the images within the resources folder to ensure they are properly loaded by the application.

First clean and package: `mvn clean package`

Then run: `mvn javafx:run`
## Folder Structure

The project's folder structure should be as follows:

```
src
└── main
    ├── java
    │   ├── module-info.java
    │   └── ca
    │       └── jasoncodes
    │           └── minesweeper
    │               └── Main.java
    └── resources
        ├── ca
        │   └── jasoncodes
        │       └── minesweeper
        │           └── ui.fxml
        └── images

```
