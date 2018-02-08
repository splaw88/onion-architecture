# Onion architecture
This is the example of Onion Architecture described by Jeffrey Palermo implemented in Java.

# What's going on?
I'll try to explain some basics.

Commonly used architecture is Layerd Architecture and pretty often (too often as I saw it) is poorly implemented as layers overlaps each other creating stiff monolyths (for example enitity object running all the way from database to web UI).
![Layer diagram](http://jeffreypalermo.com/files/media/image/WindowsLiveWriter/TheOnionArchitecturepart1_70A9/image%7B0%7D%5B61%5D.png)
This architecture basicly cut application most commonly to 3 layers: UI, business logic and data storage. Frameworks and other infrastruction elements are mixed in all layers.
So what's wrong with that? Basicly app is built on data store with often is strictly coupled to the way and infrastruction standing behind it. Also building app strongly coupled to frameworks makes app strong dependant to it.
This is NOT GOOD. Why? Well if app is going to live for just couple ears it propably wont be a problem but in long term apps you have to be aware that 3 party dependencies keep changing, they sometimes 'die' or there are released beter alternatives.
And it's all hapening without our control. If you build your app on fundamentals like for exampe some database vendor and it loosing support you will have to rewrite very big portion of your app to adapt it to new solution (or even entire app).
This is very expensive and it may be cheaper to write new app abandonig current one. 

### So lets look at the Onion architecture.
![Onion diagram](http://jeffreypalermo.com/files/media/image/WindowsLiveWriter/TheOnionArchitecturepart1_70A9/image%7B0%7D%5B59%5D.png)
 It basic concept is to entirely decouple our application from everything that is beyound our control like database vendors, IoC container, framework etc.
It's keepeng our bussiness model in the center (not database model!) then some domain services and on top of it is bussiness logic. Everything else is kept out side of our core application making it more like plugins or exchangable modules. 
This approche allow us to focus on WHAT our application DO not HOW it is DONE. So as you can see if 3 party dependencies changes is more like deatach a module and connect new one without painfull refactor of busines logic. 
This allows even to have multiple versions of enviroments for the same bussiness purposes: for example you can have one implementation for fat jar in embeded spring tomcat, other for Jboss server, another as library for others to implement thair enviroment.


To read more visit Jeffrey's blog where he described it in more detail
[Onion Architecture part 1](http://jeffreypalermo.com/blog/the-onion-architecture-part-1/) 
[Onion Architecture part 2](http://jeffreypalermo.com/blog/the-onion-architecture-part-2/) 
[Onion Architecture part 3](http://jeffreypalermo.com/blog/the-onion-architecture-part-3/) 
[Onion Architecture part 4](http://jeffreypalermo.com/blog/onion-architecture-part-4-after-four-years/) 

# This project
In this project I tried to implement some simple app to log work time. I tried to make it not too complex so it's behaviour is not perfect but should be good example from architectural point.

As you can see in tree structur there are some core modules containing app logic and currently one infrastructural implementation based on springboot, h2 in memory database, embeded Tomcat, Rest services and Angular2 web ui.
In the future ill write other infrastructure stack to prove point of strong decoupling.

### Building and running

App is build using maven. simple type in root folder
```
mvn install
```

it will download npm to get angular and other web dependencies. standard maven clean wont clean frontend dependencies so if you wont to clean them as well simply run clean with profile 'node_clean' like this
```
mvn clean -Pnode_clean
```

To run app simply run from spring-based-app module generated jar file, like
```
java -jar infrastructure/spring-based-app/spring-application/target/spring-application-1.0-SNAPSHOT.jar
```
it will run Tomcat on adress localhost:8080
More description soon...