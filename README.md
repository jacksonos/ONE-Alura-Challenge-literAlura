# ONE-Alura-Challenge-literAlura 📚✨﻿

Este repositorio contiene una solución para el desafío "**LiterAlura**" del programa **ONE - Oracle Next Education** de Alura Latam. El proyecto es una **aplicación de consola** desarrollada en Java que permite consultar libros y autores utilizando la **API de Gutendex** y almacenar la información en una base de datos. Adicionalmente, integra la **API de Gemini** para generar resúmenes concisos de las descripciones de los libros.

## Descripción del Proyecto 📖

LiterAlura es una aplicación de gestión de biblioteca personal que interactúa con la **API de Gutendex** para obtener datos de libros de dominio público en varios idiomas y de diferentes autores. La aplicación permite a los usuarios buscar libros, registrar nueva información en una base de datos local y realizar diversas consultas sobre los libros y autores almacenados. La integración con la **API de Gemini** enriquece la experiencia al proporcionar resúmenes automáticos de las descripciones de los libros, facilitando una visión rápida de su contenido.

## Funcionalidades Principales 🚀

La aplicación de consola LiterAlura permite las siguientes operaciones:

* **Buscar y Registrar Libro por Título** 🔍➕: Permite al usuario buscar un libro por su título en la API de Gutendex y, si se encuentra, registrarlo en la base de datos local.
* **Generar Resumen con Gemini** ✍️✨: Para cada libro registrado, se puede generar un resumen conciso de su descripción utilizando la API de Gemini.
* **Listar Libros Registrados** 📖📜: Muestra una lista de todos los libros que han sido guardados en la base de datos.
* **Listar Autores Registrados** 🧑‍💻📚: Muestra una lista de todos los autores cuyos libros están registrados en la base de datos.
* **Listar Autores Vivos en un Determinado Año** ⏳👨‍🏫: Permite al usuario ingresar un año y ver una lista de autores que estaban vivos en ese período.
* **Listar Libros por Idioma** 🌐🗣️: Filtra y muestra libros según el idioma especificado (por ejemplo, ES para español, EN para inglés, FR para francés, PT para portugués).
* **Top 10 Libros Más Descargados** 🏆⬇️: Muestra los diez libros con más descargas almacenados en la base de datos.
* **Estadísticas de Descargas** 📊📉: Genera estadísticas sobre las descargas de libros. (Soon ⚒️)

---

## Tecnologías Utilizadas 🛠️

* **Java** ☕: Lenguaje de programación principal (se recomienda Java 11 o superior, o Java 17).
* **Spring Boot** 🍃: Framework para facilitar el desarrollo de aplicaciones Java.
* **Spring Data JPA** 💾: Para la persistencia de datos y la interacción con la base de datos.
* **PostgreSQL** 🐘: Sistema de gestión de base de datos relacional (compatible también con MySQL, MariaDB, SQL Server, H2).
* **API Gutendex** 📚🔗: API externa utilizada para obtener información de libros y autores (`https://gutendex.com/`).
* **API de Gemini (Google AI)** ♊✨: Utilizada para generar resúmenes de descripciones de libros.
* **Maven** 📦: Herramienta de automatización de construcción de proyectos.
* **Jackson** (Jackson-databind) 🔗: Librería para el manejo de JSON.
* **Dotenv-java** (o similar) 🔐: Para la gestión segura de variables de entorno, como la clave de la API de Gemini.

---

## Cómo Ejecutar el Proyecto ▶️

### Prerrequisitos ✅

* **Java 11+** (o Java 17) instalado.
* **Maven 3.6.0+** instalado.
* **PostgreSQL** (u otra base de datos SQL compatible) instalada y funcionando.
* **Clave de API de Google Gemini** (puedes obtenerla en [Google AI Studio](https://aistudio.google.com/)).

### Configuración del Proyecto ⚙️

1.  **Clonar el Repositorio** ⬇️:
    ```bash
    git clone [https://github.com/jacksonos/ONE-Alura-Challenge-literAlura.git](https://github.com/jacksonos/ONE-Alura-Challenge-literAlura.git)
    cd ONE-Alura-Challenge-literAlura
    ```

2.  **Configurar la Base de Datos** 🗄️:
    * Asegúrate de tener PostgreSQL instalado y en ejecución.
    * Crea una base de datos con el nombre `literalura`.
    * Configura las credenciales de tu base de datos en el archivo `src/main/resources/application.properties`:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
        spring.datasource.username=tu_usuario
        spring.datasource.password=tu_contraseña
        spring.jpa.hibernate.ddl-auto=update
        ```
    * Reemplaza `tu_usuario` y `tu_contraseña` con tus credenciales de PostgreSQL.

3.  **Configurar la API de Gemini** 🔑:
    * Crea un archivo `.env` en la **raíz de tu proyecto** (al mismo nivel que `pom.xml`).
    * Dentro de este archivo, agrega tu clave de API de Gemini de la siguiente manera:
        ```properties
        GEMINI_API_KEY=TU_CLAVE_DE_API_DE_GEMINI
        ```
    * Asegúrate de reemplazar `TU_CLAVE_DE_API_DE_GEMINI` con la clave que obtuviste de Google AI Studio.

### Ejecutar la Aplicación 🚀

Puedes ejecutar la aplicación de las siguientes maneras:

* **Desde un IDE (como IntelliJ IDEA)**:
    1.  Abre el proyecto en tu IDE.
    2.  Navega hasta la clase principal (por ejemplo, `LiterAluraApplication.java` en `src/main/java/com/alurachallenge/literalura`).
    3.  Haz clic derecho y selecciona "Run 'LiterAluraApplication.main()'".

* **Desde la Línea de Comandos**:
    1.  Navega al directorio raíz del proyecto (donde se encuentra el archivo `pom.xml`).
    2.  Ejecuta el siguiente comando Maven:
        ```bash
        mvn spring-boot:run
        ```

Una vez que la aplicación se inicie, verás un menú en la consola que te permitirá interactuar con las diferentes funcionalidades.

---

### Nota sobre Dependencias 💡

Las dependencias como `jackson-databind` y `google-genai` (si están configuradas en el `pom.xml` del repositorio) **no necesitan ser agregadas manualmente** si clonas el repositorio. Maven se encargará automáticamente de descargar y gestionar estas dependencias cuando construyas o ejecutes el proyecto. Solo necesitarías agregarlas si el `pom.xml` inicial no las incluyera.

---

## Licencia 📄

Este proyecto es de uso libre y educativo como parte del challenge del programa ONE de Oracle + Alura.
