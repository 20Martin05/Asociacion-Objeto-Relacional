# Sistema de Gestión de Productos (Asociación Objeto-Relacional)

Proyecto de Persistencia de Datos (U1) que implementa un sistema CRUD de gestión de inventario de productos de supermercado con Java, Maven, interfaz gráfica Swing y persistencia de datos relacional en **PostgreSQL** mediante **Jakarta Persistence (JPA)** y el framework **Hibernate ORM**.

El desarrollo aplica el Mapeo Objeto-Relacional (ORM) mediante anotaciones estándar de JPA y gestiona sesiones y transacciones seguras a través de `EntityManager` para garantizar la integridad de los datos en cada operación de lectura y escritura.

## ⚙️ Características Técnicas

* **CRUD Completo:** Creación, lectura, actualización y eliminación de registros (`persist`, `createQuery`, `find`, `merge`, `remove`).
* **Mapeo Objeto-Relacional (ORM):** Uso de anotaciones estándar (`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`) para vincular la clase Java con la tabla SQL sin escribir consultas manuales.
* **Modelo de entidad Java:** La entidad `Productos` incluye constructor vacío, constructor completo, *getters*, *setters* y `toString()`.
* **Pool de Conexiones (HikariCP):** Integración con `HikariCP` para una gestión rápida y eficiente de las conexiones hacia PostgreSQL.
* **Identificadores Únicos (IDs):** Generación automática de llaves primarias autoincrementales (`SERIAL` / `GenerationType.IDENTITY`) en PostgreSQL.
* **Gestión de Transacciones (JPA):** Control estricto de transacciones mediante `EntityManager` (`em.getTransaction().begin()`, `em.getTransaction().commit()`).
* **Gestión de persistencia:** Uso de `EntityManagerFactory` y `EntityManager` para realizar consultas y transacciones JPA.
* **Validación de Existencia:** Comprobación de registros nulos (`!= null`) antes de ejecutar actualizaciones o eliminaciones por ID.

## 🛠️ Requisitos Previos

Para compilar y ejecutar este proyecto correctamente, necesitas tener instalado:

* **Java Development Kit (JDK)** versión 21 o superior.
* **Apache Maven** (gestor de dependencias y construcción).
* **PostgreSQL** (servidor de base de datos activo en el puerto `5432`).
* Un entorno de desarrollo (IDE) como **IntelliJ IDEA** (recomendado), Eclipse o NetBeans.

## 📂 Estructura del Proyecto

El código está organizado bajo la estructura estándar de Maven de la siguiente manera:

```text
src/main/
 ├── java/
 │   └── U1/
 │       ├── entity/
 │       │   └── Productos.java          # Entidad JPA que define la tabla productos en PostgreSQL
 │       ├── gui/
 │       │   ├── base/
 │       │   │   ├── VentanaProductosUI.java # Componentes visuales y setupUI generado por IntelliJ
 │       │   │   └── VentanaProductos.form   # Formulario de diseño visual de IntelliJ IDEA
 │       │   └── VentanaProductos.java   # Controlador de la ventana (CRUD limpio y eventos)
 │       └── Main.java                   # Punto de entrada que inicializa y muestra la ventana gráfica
 └── resources/
     ├── META-INF/
     │   └── persistence.xml             # Unidad de persistencia (postgres), credenciales y propiedades de Hibernate
     ├── sql/
     │   └── Productos.sql               # Script SQL DDL para creación de tabla en PostgreSQL
     ├── images/
     │   ├── Diagrama de Clase -- Corporacion Favorita.jpg  # Captura visual del diagrama de clases UML
     │   └── Aplicacion en Ejecución .jpg                   # Captura de la aplicación en ejecución
     └── uml/
         └── Empresa_ Corporacion Favorita.vpp  # Diagrama de clases UML en Visual Paradigm
```

## 📊 Diagrama de Clases UML

A continuación se muestra el modelo de clases diseñado en Visual Paradigm para la entidad `Productos` de la Empresa Corporación Favorita:

<p align="center">
  <img src="src/main/resources/images/Diagrama%20de%20Clase%20--%20Corporacion%20Favorita.jpg" alt="Diagrama de Clases UML - Corporación Favorita" width="300"/>
</p>

* **Entidad / Clase:** `Productos`
* **Atributos:**
  * `- id : int` (Clave primaria autoincremental `SERIAL` / `IDENTITY`)
  * `- nombre : String` (Nombre comercial del producto)
  * `- precio : double` (Precio de venta unitario)
  * `- cantidad : int` (Stock físico disponible)
  * `- categoria : String` (Sección o categoría de supermercado)
* **Métodos / Operaciones CRUD:**
  * `+ Informacion()` : Consulta y visualización del inventario de productos.
  * `+ Añadir()` : Registro y persistencia de un nuevo producto.
  * `+ Actualizar()` : Modificación de precio, cantidad y categoría de un producto existente.
  * `+ Eliminar()` : Supresión de un producto por su identificador.

## 🖥️ Evidencia de ejecución

La siguiente imagen muestra la aplicación gráfica en ejecución:

<p align="center">
  <img src="src/main/resources/images/Aplicacion%20en%20Ejecuci%C3%B3n%20.jpg" alt="Aplicación de gestión de productos en ejecución" width="700"/>
</p>

## 🚀 Instrucciones de Uso

### 1. Clonar el proyecto

Desde una terminal, ejecuta:

```bash
git clone https://github.com/20Martin05/Asociacion-Objeto-Relacional.git
cd Asociacion-Objeto-Relacional
```

### 2. Preparar PostgreSQL

1. Inicia el servidor PostgreSQL en el puerto `5432`.
2. Verifica que exista la base de datos `postgres`.
3. Asegúrate de conocer la contraseña del usuario `postgres`.

La aplicación crea o actualiza automáticamente la tabla `productos` mediante Hibernate. El script opcional `src/main/resources/sql/Productos.sql` permite crear la tabla manualmente, pero contiene `DROP TABLE IF EXISTS` y debe ejecutarse con precaución.

### 3. Configurar y ejecutar el programa

1. Abre la carpeta clonada en IntelliJ IDEA, Eclipse, NetBeans o VS Code como proyecto Maven.
2. Dirígete a `src/main/resources/META-INF/persistence.xml` y reemplaza `PON_AQUI_TU_CONTRASEÑA` por la contraseña local de tu usuario `postgres`.
3. Recarga las dependencias Maven.
4. Ejecuta la clase principal `U1.Main`.

También puedes compilar el proyecto desde una terminal:

```bash
mvn clean package
```

Después de compilar, ejecuta `U1.Main` desde el IDE con el JDK 21 o superior configurado.

### ¿Cómo interactuar con la Interfaz Gráfica (UI)?

* **Tabla de Productos (`JTable`):** Al abrir la aplicación, carga automáticamente todo el inventario desde PostgreSQL en una tabla inferior con barras de desplazamiento.
* **Selección con Clic:** Al hacer clic sobre cualquier fila de la tabla, los datos de ese producto se cargan automáticamente en las cajas de texto y el selector de categoría.
* **Guardar (`btnGuardar`):** Ingresa Nombre, Precio, Cantidad y selecciona la Categoría deseada en el desplegable. Al pulsar **Guardar**, el producto se almacena en PostgreSQL y se refresca la tabla al instante.
* **Actualizar (`btnActualizar`):** Selecciona un producto de la tabla, modifica su precio, cantidad o categoría en el formulario y pulsa **Actualizar** para guardar los cambios en la base de datos.
* **Eliminar (`btnEliminar`):** Selecciona el producto que deseas quitar y pulsa **Eliminar**. El sistema solicitará confirmación antes de borrar el registro de PostgreSQL.
* **Limpiar (`btnLimpiar`):** Vacía las cajas de texto del formulario para permitir el ingreso de un nuevo registro desde cero.

### ¿Dónde se guardan los datos?

Toda la información se almacena de forma persistente en el motor de base de datos **PostgreSQL**:

* **Base de datos:** `postgres` (`jdbc:postgresql://localhost:5432/postgres`)
* **Tabla:** `productos` (creada y actualizada automáticamente gracias a la propiedad `hibernate.hbm2ddl.auto = update`).

## 👤 Autor

* **Desarrollado por:** Martin Rody Rosero Játiva
* **Materia:** Persistencia de Datos
* **Universidad:** Universidad Politécnica Salesiana
* **Bloque:** Bloque I - Unidad 1 (Asociación Objeto-Relacional)