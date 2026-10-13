package U1;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando conexión con la base de datos PostgreSQL...");
        
        // --- CONFIGURACIÓN DE JPA / HIBERNATE ---
        // Utilizamos un bloque try-with-resources para asegurar que tanto el EntityManagerFactory,
        // el EntityManager como el Scanner se cierren automáticamente al terminar.
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("postgres");
             EntityManager em = emf.createEntityManager();
             Scanner sc = new Scanner(System.in)) {
             
            // Bucle infinito para mantener el menú del programa activo hasta que el usuario elija salir
            while (true) {
                // --- MENÚ PRINCIPAL ---
                System.out.println("\n--- MENÚ DE GESTIÓN DE PRODUCTOS ---");
                System.out.println("1. Ver Tabla Productos");
                System.out.println("2. Creación de Nombre, Precio, Cantidad, Categoría");
                System.out.println("3. Actualización de Precio, Cantidad, Categoría");
                System.out.println("4. Borrar un Producto");
                System.out.println("5. Salir de la Tabla");
                System.out.print("Escoger una opción: ");
                
                int opcion = sc.nextInt();
                sc.nextLine(); // Limpiamos el buffer del Scanner para evitar saltos de línea indeseados
                
                // OPCIÓN 1: LEER (SELECT)
                if (opcion == 1) {
                    em.getTransaction().begin(); // Toda operación requiere una transacción activa
                    
                    // JPQL: Usamos el nombre de la CLASE Java (Productos), no de la tabla SQL
                    List<Productos> lista = em.createQuery("from Productos", Productos.class).getResultList();
                    
                    System.out.println("\n--- LISTA DE PRODUCTOS EN INVENTARIO ---");
                    for (Productos p : lista) {
                        System.out.println(p.toString());
                    }
                    
                    em.getTransaction().commit(); // Confirmamos la lectura y cerramos transacción
                    
                // OPCIÓN 2: CREAR (INSERT)
                } else if (opcion == 2) {
                    System.out.println("Escribir Nombre del Producto:");
                    String nombre = sc.nextLine();
                    
                    System.out.println("Escribir el Precio del Producto (ej: 2.50):");
                    double precio = sc.nextDouble();
                    sc.nextLine(); // Limpieza del buffer
                    
                    System.out.println("Escribir la Cantidad en Stock (UNIDADES):");
                    int cantidad = sc.nextInt();
                    sc.nextLine(); // Limpieza del buffer
                    
                    System.out.println("Escribir la Categoría (LACTEOS, CARNES, FRUTAS, VERDURAS, GRANOS, PANADERIA, BEBIDAS, ENLATADOS, SNACKS):");
                    String categoria = sc.nextLine();

                    // Instanciamos el objeto con ID 0 (PostgreSQL generará el ID automáticamente mediante SERIAL)
                    Productos nuevoProducto = new Productos(0, nombre, precio, cantidad, categoria);
                    
                    em.getTransaction().begin();
                    em.persist(nuevoProducto); // persist() guarda el objeto nuevo en la BD
                    em.getTransaction().commit();
                    
                    System.out.println("Producto registrado con éxito en el sistema.");
                    
                // OPCIÓN 3: ACTUALIZAR (UPDATE)
                } else if (opcion == 3) {
                    System.out.println("Escribir el ID del Producto:");
                    int id = sc.nextInt();
                    
                    System.out.println("Escribir el NUEVO Precio del Producto:");
                    double nuevoPrecio = sc.nextDouble();
                    sc.nextLine(); // Limpieza del buffer
                    
                    System.out.println("Escribir la NUEVA Cantidad en Stock (UNIDADES):");
                    int nuevaCantidad = sc.nextInt();
                    sc.nextLine(); // Limpieza del buffer
                    
                    System.out.println("Escribir la NUEVA Categoría (LACTEOS, CARNES, FRUTAS, VERDURAS, GRANOS, PANADERIA, BEBIDAS, ENLATADOS, SNACKS):");
                    String nuevaCategoria = sc.nextLine();

                    em.getTransaction().begin();

                    // find() busca en la BD por ID. Si el ID no existe, devuelve "null".
                    Productos actualizar = em.find(Productos.class, id);
                    
                    // Verificamos que el producto exista antes de intentar cambiarle los datos
                    if (actualizar != null) {
                        // Modificamos los datos del objeto usando sus Setters generados por Lombok
                        actualizar.setPrecio(nuevoPrecio);
                        actualizar.setCantidad(nuevaCantidad);
                        actualizar.setCategoria(nuevaCategoria);
                        
                        em.merge(actualizar); // merge() aplica los cambios en la Base de Datos
                        em.getTransaction().commit();
                        System.out.println("Actualización de Precio, Cantidad y Categoría exitosa.");
                    } else {
                        System.out.println("No se encontró ningún producto con ese ID.");
                        em.getTransaction().commit(); // Cerramos transacción incluso si falló la búsqueda
                    }
                    
                // OPCIÓN 4: BORRAR (DELETE)
                } else if (opcion == 4) {
                    System.out.println("Escribir el ID del Producto a borrar:");
                    int id = sc.nextInt();
                    sc.nextLine();

                    em.getTransaction().begin();

                    Productos eliminar = em.find(Productos.class, id);
                    if (eliminar != null) {
                        em.remove(eliminar); // remove() borra el registro de la BD
                        em.getTransaction().commit();
                        System.out.println("Producto borrado con éxito.");
                    } else {
                        System.out.println("No se encontró ningún producto con ese ID.");
                        em.getTransaction().commit();
                    }
                    
                // OPCIÓN 5: SALIR
                } else if (opcion == 5) {
                    System.out.println("Desconectando la conexión con la base de datos de PostgreSQL...");
                    break; // Rompe el ciclo while(true) y permite que finalice el programa limpiamente
                }
            }

        } catch (Exception e) {
            // Manejo de errores en caso de falla de conexión o consulta
            throw new RuntimeException(e);
        }

    }
}