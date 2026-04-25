# GoldRush 2.0

> An improved version of GoldRush, a JavaFX game for HBV202G.

## Version 2.0
A JavaFX game built on GoldRush where the player collects gold while avoiding enemies.
We decided to add onto this project as it gave us the most room to improve gameplay and user experience. 
The game features character selection, difficulty levels and a timer. 
The project applies software design concepts from HBV202G including Maven, JUnit testing, UML design and design patterns.

## Maven Setup

The project uses:

- **Java** 21
- **JavaFX** 21 (`javafx-controls`, `javafx-fxml`)
- **JUnit Jupiter** 5.10.0

### Plugins

| Plugin | Version | Group ID |
| --- | --- | --- |
| `maven-compiler-plugin` | 3.13.0 | `org.apache.maven.plugins` |
| `maven-surefire-plugin` | 3.3.0 | `org.apache.maven.plugins` |
| `maven-site-plugin` | 3.12.1 | `org.apache.maven.plugins` |
| `javafx-maven-plugin` | 0.0.8 | `org.openjfx` |
| `maven-shade-plugin` | 3.5.2 | `org.apache.maven.plugins` |
| `exec-maven-plugin` | 3.3.0 | `org.codehaus.mojo` |

See more in [`pom.xml`](pom.xml).

## Supported Maven goals

| Goal | Description |
| --- | --- |
| `mvn compile` | Compiles the project |
| `mvn test` | Runs the JUnit tests |
| `mvn exec:java` | Runs the program through `vidmot.goldrush.Launcher` |
| `mvn package` | Packages the program into a single fat jar using `maven-shade-plugin` |
| `mvn site` | Generates the Maven site with Javadoc and design documentation |
| `mvn javafx:run` | Runs the game directly through the JavaFX plugin |

## Running in an IDE

To run the program with Maven, go to **Maven → Plugins → javafx → javafx:run**.

Alternatively, you can run it through `GoldApplication` and click _run current file_.

> `vidmot.goldrush.GoldApplication` is the mainClass for JavaFX, and `vidmot.goldrush.Launcher` is the mainClass for the fat jar.

## Packaging and Running the jar file

To package the program into a single executable `.jar` file:

```bash
mvn package
```

To run the program without an IDE or Maven:

```bash
./run.sh
```

This runs `java -jar target/GoldRush-1.0-SNAPSHOT.jar` (the `Launcher` class is the entry point).

## Game in Action

| Game rules | Character select | Gameplay |
| :---: | :---: | :---: |
| ![Game rules](src/main/resources/goldrush/myndir/leikreglur.png) | ![Character select](src/main/resources/goldrush/myndir/karakter_select.png) | ![Gameplay](src/main/resources/goldrush/myndir/leikur.png) |

## Design Documentation
- [UML Class Diagram](src/site/markdown/uml.md)
- [Design Patterns](src/site/markdown/design-patterns.md)

## API Reference
Javadoc is generated with `mvn site` and can be found at `target/site/apidocs/index.html`.

## Authors
- Ana Margarida Delgado Costa, amd16@hi.is — [@anamargariida](https://github.com/anamargariida)
- Helga Björg Helgadóttir, hbh54@hi.is — [@helgsie](https://github.com/helgsie)

## License

This project is released under the [MIT License](LICENSE).
