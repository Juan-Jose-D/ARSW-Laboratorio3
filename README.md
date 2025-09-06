## Escuela Colombiana de Ingeniería

## Arquitecturas de Software

# Componentes y conectores - Parte I.

El ejercicio se debe traer terminado para el siguiente laboratorio (Parte II).

#### Middleware- gestión de planos.


## Antes de hacer este ejercicio, realice [el ejercicio introductorio al manejo de Spring y la configuración basada en anotaciones](https://github.com/ARSW-ECI/Spring_LightweightCont_Annotation-DI_Example).

En este ejercicio se va a construír un modelo de clases para la capa lógica de una aplicación que permita gestionar planos arquitectónicos de una prestigiosa compañia de diseño. 

![](img/ClassDiagram1.png)

1. Configure la aplicación para que funcione bajo un esquema de inyección de dependencias, tal como se muestra en el diagrama anterior.


	Lo anterior requiere:

	* Agregar las dependencias de Spring.
	* Agregar la configuración de Spring.
	* Configurar la aplicación -mediante anotaciones- para que el esquema de persistencia sea inyectado al momento de ser creado el bean 'BlueprintServices'.


2. Complete los operaciones getBluePrint() y getBlueprintsByAuthor(). Implemente todo lo requerido de las capas inferiores (por ahora, el esquema de persistencia disponible 'InMemoryBlueprintPersistence') agregando las pruebas correspondientes en 'InMemoryPersistenceTest'.

3. Haga un programa en el que cree (mediante Spring) una instancia de BlueprintServices, y rectifique la funcionalidad del mismo: registrar planos, consultar planos, registrar planos específicos, etc.

4. Se quiere que las operaciones de consulta de planos realicen un proceso de filtrado, antes de retornar los planos consultados. Dichos filtros lo que buscan es reducir el tamaño de los planos, removiendo datos redundantes o simplemente submuestrando, antes de retornarlos. Ajuste la aplicación (agregando las abstracciones e implementaciones que considere) para que a la clase BlueprintServices se le inyecte uno de dos posibles 'filtros' (o eventuales futuros filtros). No se contempla el uso de más de uno a la vez:
	* (A) Filtrado de redundancias: suprime del plano los puntos consecutivos que sean repetidos.
	* (B) Filtrado de submuestreo: suprime 1 de cada 2 puntos del plano, de manera intercalada.

5. Agrege las pruebas correspondientes a cada uno de estos filtros, y pruebe su funcionamiento en el programa de prueba, comprobando que sólo cambiando la posición de las anotaciones -sin cambiar nada más-, el programa retorne los planos filtrados de la manera (A) o de la manera (B).

# Arquitecturas de Software (ARSW) - Laboratorio: Gestión de Planos Arquitectónicos

#### Juan José Díaz

## Middleware – Inyección de dependencias, persistencia y filtrado de planos arquitectónicos

[![Java](https://img.shields.io/badge/Java-17%2B-blue.svg)](https://www.oracle.com/java/)
[![Maven](https://img.shields.io/badge/Maven-Build-brightgreen.svg)](https://maven.apache.org/)

---

Este laboratorio explora el diseño de una capa lógica para la gestión de planos arquitectónicos, aplicando principios de inyección de dependencias con Spring, persistencia en memoria y filtrado flexible de datos. El objetivo es construir una aplicación modular y extensible, capaz de registrar, consultar y filtrar planos de manera eficiente.

---

### Estructura de archivos del proyecto

```bash
.
├── img/                       # Diagramas y capturas
│   ├── ClassDiagram1.png      # Diagrama de clases principal
│   └── BeansModel.png         # Diagrama de configuración de beans
├── model.uml                  # Diagrama UML
├── pom.xml                    # Configuración de Maven
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── edu/eci/arsw/blueprints/
│   │   │       ├── model/         # Entidades: Blueprint, Point
│   │   │       ├── persistence/   # Interfaces y persistencia en memoria
│   │   │       ├── services/      # Servicios y filtros
│   │   │       └── ui/            # Programa de prueba
│   │   └── resources/
│   │       └── applicationContext.xml # Configuración de Spring
│   └── test/
│       └── java/
│           └── edu/eci/arsw/blueprints/test/persistence/impl/
│               ├── InMemoryPersistenceTest.java   # Pruebas de persistencia
│               └── BlueprintFilterTest.java       # Pruebas de filtros y servicios
├── README.md                   # Documentación del laboratorio
```

---

### Ejecutar el Proyecto

Este laboratorio es un proyecto Maven. Para compilar y ejecutar, asegúrate de tener **Java 17+** y **Maven** instalados y configurados en el `PATH`.

```bash
mvn clean package
mvn test
```

Para ejecutar el programa de prueba principal:

```bash
mvn exec:java -Dexec.mainClass="edu.eci.arsw.blueprints.ui.Main"
```

---

## Descripción y pasos del laboratorio

### 1. Modelo de clases y persistencia

Se diseñó un modelo de clases para la gestión de planos arquitectónicos, siguiendo el diagrama:

![](img/ClassDiagram1.png)

- **Blueprint**: representa un plano, compuesto por una lista de puntos (`Point`).
- **BlueprintPersistence**: interfaz para la persistencia de planos.
- **InMemoryBlueprintPersistence**: implementación en memoria de la persistencia.
- **BlueprintsServices**: servicio principal, inyecta la persistencia y el filtro de planos.

### 2. Inyección de dependencias con Spring

La aplicación utiliza Spring para la gestión de dependencias:

- Se agregaron las dependencias de Spring en `pom.xml`.
- Se configuró el escaneo de componentes en `applicationContext.xml`.
- Las clases de persistencia y servicios usan anotaciones `@Component`, `@Service`, `@Autowired` y `@Qualifier` para la inyección.

### 3. Operaciones principales y pruebas

- Se implementaron los métodos `getBlueprint()` y `getBlueprintsByAuthor()` en el servicio.
- Se crearon pruebas unitarias para la persistencia (`InMemoryPersistenceTest`) y para los filtros (`BlueprintFilterTest`).
- El programa de prueba (`Main.java`) permite registrar y consultar planos, verificando el funcionamiento de la inyección y el filtrado.

### 4. Filtros de planos arquitectónicos

Las operaciones de consulta aplican un filtro antes de retornar los planos:

- **Filtrado de redundancias**: elimina puntos consecutivos repetidos.
- **Filtrado de submuestreo**: elimina 1 de cada 2 puntos de manera intercalada.

La clase `BlueprintsServices` recibe el filtro a través de inyección, permitiendo alternar entre filtros simplemente cambiando la anotación o el bean configurado.

### 5. Pruebas y validación

Las pruebas unitarias verifican:

- Registro y consulta de planos en memoria.
- Funcionamiento correcto de ambos filtros.
- Integración de los filtros con el servicio principal.

---

## Ejemplo de uso

```java
ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
BlueprintsServices bs = ctx.getBean(BlueprintsServices.class);
Blueprint bp = new Blueprint("Juan", "Casa", new Point[]{new Point(0,0), new Point(1,1), new Point(1,1), new Point(2,2)});
bs.addNewBlueprint(bp);
Blueprint result = bs.getBlueprint("Juan", "Casa");
System.out.println("Plano filtrado: " + result.getPoints());
```

---

## Autores

Juan José Díaz - [github](https://github.com/Juan-Jose-D)
Escuela Colombiana de Ingeniería Julio Garavito
