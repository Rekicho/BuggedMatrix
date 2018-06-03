# Bugged-Matrix
1 vs 1 Arena Shooter Game - but with bad physics and bad controls

## Setup/Installation

### Game

To run the game on Desktop, just download BuggedMatrix-Desktop.jar and execute it (be sure to have JRE installed).
To run the game on Android, download BuggedMatrix-Android.apk, execute it and it will install the game (you may need to allow "Install from other sources" on your Android definitions if you haven't done it already) and then just execute the app called BuggedMatrix.
To run the HTML version, put all the files in BuggedMatrix-HTML on a web server (or localhost) and access it.

### Development enviroment

1. [Install Android Studio](https://developer.android.com/studio/)
2. Clone the repository
3. Open Android Studio
4. Select "Import Project (Gradle, Eclipse ADT, etc.)"
5. On path select PATH_WHERE_YOU_CLONED_THE_REPOSITORY_TO\BuggedMatrix (it will look someting like ...\BuggedMatrix\BuggedMatrix\) and hit ok
6. To run the android version, just go to Run->Run.. , select Android, connect an Android (where developer mode and run degub mode are active) or create a Virtual device, and the game will run.
7. To run the desktop version, select DesktopLauncher class, go to Run->Edit Configurations.., change Working Directory to ...\BuggedMatrix\BuggedMatrix\android\assets and the Run->Run.. and select DesktopLauncher.
8. To run the test, just go to a test class, a green circle should appear on the left of each function, click it and then click tun test. (Altough it is possible to run multiple tests at the same time, some will fail if done so (as we used SINGLETON, some test function will interfere with the others, so to get correct test results, only one funcion should be run at a time)).


## Development documentation

### Class diagram

![UML](https://i.imgur.com/xdM2srC.png)

### State diagram

![Game Cycle](https://i.imgur.com/IygnOsc.png)

### Design Patterns

Singleton:

![Singleton](https://upload.wikimedia.org/wikipedia/commons/thumb/f/fb/Singleton_UML_class_diagram.svg/1200px-Singleton_UML_class_diagram.svg.png)

Used in: GameController, GameModel

We can only have on game happening at each time, so it makes sense to use a singleton for each Game class

Game Loop:

![GameLoop](http://gameprogrammingpatterns.com/images/game-loop-fixed.png)

Easier to develop and understand how the game runs.

Model-View-Controller:

![MVC](https://koenig-media.raywenderlich.com/uploads/2016/04/diagram-mvc-480x241.png)

- Model: Represents the game logic and rules.
- View: Outputs the game represantion for the user to see.
- Controller: Reads and interprets the user commands and linkes them with Box2D world.

Allows us to separate different concerns, making it easier to make changes to each one of them without collapsing the others.

Factory:

![Factory](https://upload.wikimedia.org/wikipedia/commons/4/43/W3sDesign_Factory_Method_Design_Pattern_UML.jpg)

Used in: View Entity Package

Code has higer abstraction and allows to easily create and implement new views, as well as change those already created.

### User Manual

1. 
Touch screen to begin.
![MainScren](https://imgur.com/oAn6Olm.png)

2.
On Desktop:

Press W, A, S and D to move player 1 and C to shoot. Use the arrows to move player 2 and L to shoot.
![DesktopGame](https://imgur.com/OIbI70z.png)

On Android:

Use the joysticks and the buttons to move and shoot.
![AndroidGame](https://imgur.com/kThH4xA.png)

3.
Shoot the other player 3 times to win.
![RedWon](https://i.imgur.com/5FAcGo7.png)
![BlueWon](https://imgur.com/cF6eqXz.png)

4. 
Touch screen again to go back to Main Menu.
