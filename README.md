# Biwanger Fantasy

Biwanger Fantasy pretende ser un videojuego en el que cada jugador se convierte en el gestor
de un equipo virtual, pero enlazando con las actuaciones de los jugadores en la realidad.

### Comandos a realizar para conectar cliente con servidor 📋

_Ejecutar siguientes comandos (después de haber puesto en marcha el servidor):_

```
mvn compiler:compile
```

```
mvn exec:java -Pclient
```
_*¡Truco del almendruco!* ¡Se pueden juntar todos los comandos en una línea!_

```
mvn compiler:compile exec:java -Pclient
```