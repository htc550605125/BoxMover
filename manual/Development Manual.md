Development Manual
=====================
This is the development manual of the BoxMover game written by htc550605125.

Project Struct
=====================
The code of the project is written in java, developed under [Intellij Idea 
IDE](http://www.jetbrains.com/idea/), managed by 
[github](https://github.com/htc550605125/BoxMover). The target jar is generated by 
[maven](http://maven.apache.org/). The manual is written in 
[markdown](http://zh.wikipedia.org/wiki/Markdown), HTML version is generated by 
[stackedit](https://stackedit.io). 

Howto Build
====================
Run 

    mvn package

or
    
    mvn clean compile assembly:single
    
under the top directory, the target jar is under __target/__ .

Package Struct Overview
====================
There are three main packages of the project:  

#### htc550605125.boxmover.common  
    Element, Dimension, Vector, Stage, Config

#### htc550605125.boxmover.server  
    Save slot management, server management, game map management 

#### htc550605125.boxmover.client
    The client that interact with the user and the server.
    
Implement Detail
===================
See [javadoc](../doc/index.html). Special notes are written in 
[ElementSet](../doc/htc550605125/boxmover/common/element/ElementSet.html), 
[Stage](../doc/htc550605125/boxmover/common/stage/Stage.html),
[CheckDead](../doc/htc550605125/boxmover/common/stage/algorithm/CheckDead.html),
[Config](../doc/htc550605125/boxmover/common/Config.html)

TODO list
==================
* 2D GUI client.
* More flexible command-handle framework.
* Advanced Element framework.
* 3D client in [Minecraft](https://minecraft.net/) by creating a forge mod
