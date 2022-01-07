# Onion architecture
This is the example of Onion Architecture described by Jeffrey Palermo implemented in Java.

# What's going on?
I'll try to explain some basics.

Commonly used architecture is Layered Architecture and pretty often (too often as I saw it) is poorly implemented as layers overlaps each other, creating stiff monolith (for example entity object running all the way from database to web UI).

![Layer diagram](http://jeffreypalermo.com/files/media/image/WindowsLiveWriter/TheOnionArchitecturepart1_70A9/image%7B0%7D%5B61%5D.png)

This architecture basically cut application most commonly to 3 layers: UI, business logic and data storage. Frameworks and other infrastructure elements are mixed in all layers.
So what's wrong with that? Basically app is built on data store which often is strictly coupled to the way it's stored and infrastructure  standing behind it. Also building app strongly coupled to frameworks makes app strong dependent to it.
This is NOT GOOD. Why? Well if app is going to live for just couple years it probably won't be a problem but in long term apps you have to be aware that 3 party dependencies keep changing, they sometimes 'die' or there are released better alternatives.
And it's all happening without our control. If you build your app on fundamentals like for example some database vendor and it loose support you will have to rewrite very big portion of your app to adapt it to new solution (or even entire app).
This is very expensive and it may be cheaper to write new app abandoning current one. 

### So lets look at the Onion architecture.

![Onion diagram](http://jeffreypalermo.com/files/media/image/WindowsLiveWriter/TheOnionArchitecturepart1_70A9/image%7B0%7D%5B59%5D.png)

 It basic concept is to entirely decouple our application from everything that is beyond our control like database vendors, IoC container, framework etc.
It's keeping our business model in the center (not database model!) then some domain services and on top of it is business logic. Everything else is kept outside of our core application making it more like plugins or exchangeable modules. 
This approach allow us to focus on WHAT our application DO not HOW it is DONE. So as you can see if 3 party dependencies changes is more like detach a module and connect new one without painful rewriting of business logic. 
This allows even to have multiple versions of environments for the same business purposes: for example you can have one implementation for fat jar in embedded spring tomcat, other for Jboss server, another as library for others to implement their environment.


To read more visit Jeffrey's blog where he described it in more detail

[Onion Architecture part 1](http://jeffreypalermo.com/blog/the-onion-architecture-part-1/) 

[Onion Architecture part 2](http://jeffreypalermo.com/blog/the-onion-architecture-part-2/) 

[Onion Architecture part 3](http://jeffreypalermo.com/blog/the-onion-architecture-part-3/) 

[Onion Architecture part 4](http://jeffreypalermo.com/blog/onion-architecture-part-4-after-four-years/) 


# This project
In this project I tried to implement some simple app to log work time. I tried to make it not too complex so it's behaviour is not perfect but should be good example from architectural point.

Currently there are 2 infrastructural implementations:
1. based on spring boot, h2 in memory database, embedded Tomcat, Rest services and Angular2 web UI.
2. simple runable console application with no DI containers, and simple inmemory repositories

In the future ill write other infrastructure stack to prove point of strong decoupling.

### Building and running

App is build using maven. Simple type in root folder
```
mvn install
```

It will download npm to get angular and other web dependencies. Standard maven clean won't clean frontend dependencies so if you wont to clean them as well simply run clean with profile 'node_clean' like this
```
mvn clean -Pnode_clean
```

To run Spring Boot web app simply run from spring-based-app module generated jar file, like
```
java -jar infrastructure/spring-based-app/spring-application/target/spring-application-1.0-SNAPSHOT.jar
```
it will run Tomcat on address local host:8080


To run console app also just run generated jar, like
```
java -jar infrastructure/console-based-app/console-application/target/console-application-1.0-SNAPSHOT.jar
```
