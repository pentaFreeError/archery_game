[<img src="https://www.ensicaen.fr/wp-content/uploads/2017/02/LogoEnsicaen.gif" width="256" >](https://www.ensicaen.fr)

Game project: Archery
================

## Project's description 

Archery is an archery game where the aim is to shoot as close as possible to a target from a distance that can vary.

The project is developed on java (SDK 21) with Intellij on Ubuntu 24.04.1 LTS, it is managed by the ‘gradle’ production engine and several extern libraries 
like JavaFX (version 22.0.2), gson (2.8.9), junit (version 5.11.3) and mockito (4.11.0).

## Deliverable

Once you have obtained *Archery.jar* by downloading it or by building it with gradle, issue this command in the terminal:
```
java -jar /path/to/Archery.jar
```
## Structure of project

The project has the following structure:

      .
      ├── build.gradle
      ├── settings.gradle
      ├── LICENSE
      ├── README.md
      └── src
          ├── main
          │   ├── java
          │   │   └── fr
          │   │       └── ensicaen
          │   │           └── ecole
          │   │               └── archery
          │   │                   ├── Main.java
          │   │                   ├── MainApplication.java
          │   │                   ├── app
          │   │                   │   └── *.java
          │   │                   ├── data
          │   │                   │   └── *.java
          │   │                   ├── model
          │   │                   │   └── *.java
          │   │                   ├── presenter
          │   │                   │   └── *.java
          │   │                   └── view
          │   │                       └── *.java
          │   └── resources
          │       └── fr
          │           └── ensicaen
          │               └── ecole
          │                   └── archery
          │                       ├── fonts
          │                       │   └── MinecraftRegular-Bmg3.otf
          │                       ├── images
          │                       │   └── *.png
          │                       └── maps
          │                           └── maps.json
          └── test
              └── java
                  └── fr
                      └── ensicaen
                          └── ecole
                              └── archery
                                  ├── app
                                  │   └── *.java
                                  ├── data
                                  │   └── *.java
                                  ├── model
                                  │   └── *.java
                                  └── presenter
                                      └── *.java

The src folder is the source code folder and is divided into two subfolders:
- main, containing all java classes and resources useful to the project.
- test, containing all tests required for java classes.

The gradle files ensure that project construction runs properly.
