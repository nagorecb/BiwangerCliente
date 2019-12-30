# Biwanger Fantasy

Biwanger Fantasy pretende ser un videojuego en el que cada jugador se convierte en el gestor
de un equipo virtual, pero enlazando con las actuaciones de los jugadores en la realidad.

Este repositorio contiene la parte de cliente del juego en cuestión. La parte servidor está en el [repositorio de servidor](https://github.com/nagorecb/BiwangerServidor).

### Comandos a realizar para conectar cliente con servidor 🚀

_Ejecutar siguientes comandos (después de haber puesto en marcha el servidor):_
```sh
$ mvn clean
$ mvn compile
$ mvn exec:java -Pclient
```

_*¡Truco del almendruco!* ¡Se pueden juntar todos los comandos en una línea!_

```sh
$ mvn clean compile exec:java -Pclient
```
 ### Test 🔎

Se han realizado testeos de todo el proyecto. Para ello, se han utilizado las siguientes herramientas:

| Herramienta | Uso |
| --- | --- |
| [JUnit 4](https://junit.org/junit4/) | Realización de test unitarios |
| [Mockito](https://site.mockito.org/) | Simulación de la parte servidora en los test |
| [Contiperf](https://mvnrepository.com/artifact/org.databene/contiperf) | Evaluación del rendimiento de las partes críticas |
| [JaCoCo](https://www.eclemma.org/jacoco/) |Comprobación de la cobertura de código de los test |
| [Travis CI](https://travis-ci.org/) |Para integración continua |


Para ejecutar dichos test, utilizamos el comando:
```sh
$ mvn test
```
Para la ejecución de JaCoCo según el límite establecido en el POM.xml:
```sh
$ mvn test jacoco:check
```
Para ver los resultados de los test de una marea más visual, se puede utilizar:
```sh
$ mvn site
```
Este comando se explicará a continuación en el punto de documentación.

### Documentación 📋

Para la realización de la documentación, se ha utilizado el plugin de maven [Doxygen](http://doxygen.nl/) y comentarios [JavaDoc](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html). Para ello, se cuenta de el archivo de configuración Doxyfile en la carpeta de [resources](https://github.com/nagorecb/BiwangerServidor/tree/master/src/main/resources), con la configuración de la documentación a generar.

Para generar dicha documentación, se debe ejecutar el comando de site:
```sh
$ mvn site
```
Este comando hará lo siguiente:
* Genera, en la carpeta target, dashboards para los plugins añadidos en la parte de reporting del POM (en la carpeta site): 
        - JaCoCo
        
        - Doxygen
        
        - Contiperf

* Genera la documentación completa en la carpeta doc, de manera que pueda ser accedida desde el repositorio de GitHb, y desde la página http://nagorecb.github.io/BiwangerCliente.

### Equipo 💻

Se ha desarrollado el proyecto mediante la metodología ágil SCRUM, utilizando la herramienta de planificación de [YouTrack](https://www.jetbrains.com/youtrack/promo/?gclid=CjwKCAiA3abwBRBqEiwAKwICAx-iax3CyO-rPM1nMA33Wd8NCXAqCKvi4mXVZ0AnIu7QBkPE68GhqxoCLIkQAvD_BwE). Los colaboradores han sido:

* Nagore Beltrán, [nagorecb](https://github.com/nagorecb)
* Maider Calzada Aláez, [MaiderC](https://github.com/MaiderC)
* Sara Olaizola Alcántara, [saraolaizola](https://github.com/saraolaizola)
* Hodei Olaskoaga Apezetxea, [HodeiOA](https://github.com/HodeiOA)

