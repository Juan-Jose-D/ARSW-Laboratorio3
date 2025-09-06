
# ARSW-Laboratorio3: Gestión de Planos Arquitectónicos

## Descripción General

Este proyecto es una aplicación full-stack para la gestión de planos arquitectónicos, desarrollada con **Spring Boot**, **Java 17+**, **Maven**, y un frontend web moderno con **HTML, CSS y JavaScript (jQuery)**. Permite registrar, consultar, filtrar y visualizar planos, así como crear nuevos autores y planos desde la interfaz web.

---

## Tecnologías y dependencias

- **Backend:** Spring Boot 2.7.18, Java 17+, Maven
- **Frontend:** HTML5, CSS3, Bootstrap, jQuery
- **Pruebas:** JUnit 4
- **Persistencia:** En memoria (sin base de datos)

---

## Estructura del Proyecto

```
├── src/
│   ├── main/
│   │   ├── java/edu/eci/arsw/blueprints/
│   │   │   ├── controllers/           # Controladores REST
│   │   │   ├── model/                 # Entidades: Blueprint, Point
│   │   │   ├── persistence/           # Interfaces y persistencia en memoria
│   │   │   ├── services/              # Servicios y filtros
│   │   │   └── ui/                    # Programa de prueba
│   │   └── resources/
│   │       ├── public/                # Frontend web (index.html, js, css)
│   │       └── applicationContext.xml # Configuración Spring
│   └── test/
│       └── java/edu/eci/arsw/blueprints/test/persistence/impl/
│           ├── InMemoryPersistenceTest.java
│           └── BlueprintFilterTest.java
├── pom.xml
├── README.md
├── img/                              # Diagramas y capturas
└── model.uml
```

---

## Instalación y Ejecución

### Requisitos
- Java 17 o superior
- Maven

### Backend (Spring Boot)
1. Instala dependencias y compila:
	```bash
	mvn clean package
	```
2. Ejecuta el backend:
	```bash
	mvn spring-boot:run
	```
	El backend estará disponible en `http://localhost:8080`.

### Frontend (Web)
1. Abre `src/main/resources/public/index.html` en tu navegador.
2. La web se conecta automáticamente al backend local.

---

## Funcionalidades

- **Registrar planos:** Desde la web, puedes crear nuevos planos y autores.
- **Consultar planos:** Buscar planos por autor y nombre.
- **Visualizar planos:** Ver los puntos del plano en un canvas interactivo.
- **Listar autores:** Ver todos los autores registrados.
- **Filtrar planos:** El backend aplica filtros (redundancia o submuestreo) antes de retornar los datos.
- **Persistencia en memoria:** Los datos se almacenan en memoria, sin base de datos.

---

## Endpoints REST principales

### GET /blueprints
Retorna todos los planos.

### GET /blueprints/{author}
Retorna todos los planos de un autor.

### GET /blueprints/{author}/{bpname}
Retorna un plano específico por autor y nombre.

### POST /blueprints
Crea un nuevo plano. Ejemplo de JSON:
```json
{
  "author": "Juan",
  "name": "Casa",
  "points": [ {"x":10, "y":20}, {"x":30, "y":40} ]
}
```

### GET /blueprints/authors
Retorna todos los autores registrados.

---

## Uso de la Interfaz Web

1. **Listar autores:** Haz clic en "List all authors" para ver los autores disponibles.
2. **Consultar planos:** Ingresa el nombre de un autor y haz clic en "Get blueprints" para ver sus planos.
3. **Visualizar plano:** Haz clic en "Open" en la tabla para ver el plano en el canvas.
4. **Crear plano:** Completa el formulario y haz clic en "Create Blueprint". El formato de puntos es `x1,y1;x2,y2;...`.

---

## Filtros de Planos

El backend aplica uno de dos filtros configurables:
- **Redundancia:** Elimina puntos consecutivos repetidos.
- **Submuestreo:** Elimina 1 de cada 2 puntos.
Puedes alternar el filtro cambiando la configuración en el backend.

---

## Pruebas

Ejecuta las pruebas unitarias con:
```bash
mvn test
```
Incluyen pruebas de persistencia y de los filtros.

---

## Ejemplo de uso en código

```java
BlueprintsServices bs = new BlueprintsServices(...);
Blueprint bp = new Blueprint("Juan", "Casa", new Point[]{new Point(0,0), new Point(1,1), new Point(1,1), new Point(2,2)});
bs.addNewBlueprint(bp);
Blueprint result = bs.getBlueprint("Juan", "Casa");
System.out.println("Plano filtrado: " + result.getPoints());
```

---

## Créditos y Autor

**Autor:** Juan José Díaz ([github](https://github.com/Juan-Jose-D))
**Institución:** Escuela Colombiana de Ingeniería Julio Garavito
