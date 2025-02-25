import java.util.*; // Importamos las librerías necesarias para manejar listas, mapas y entrada de datos.

public class OrganizadorTareas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Creamos el objeto Scanner para leer datos del usuario.
        
        // Usamos un Map para organizar las tareas por prioridad. 
        // Cada clave es un nivel de prioridad y el valor es una lista con las tareas de ese nivel.
        Map<String, List<String>> tareas = new LinkedHashMap<>();
        tareas.put("Alta", new ArrayList<>());  
        tareas.put("Media", new ArrayList<>()); 
        tareas.put("Baja", new ArrayList<>());  

        while (true) { // Bucle infinito para mostrar el menú hasta que el usuario elija salir.
            System.out.println("\nMENÚ");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt(); // Leemos la opción elegida por el usuario.
            scanner.nextLine(); // Limpiamos el buffer después de leer el número.

            if (opcion == 1) { // Agregar una nueva tarea.
                System.out.print("\nDescribe tu tarea: ");
                String tarea = scanner.nextLine(); // Guardamos la descripción de la tarea.
                
                System.out.print("Prioridad (Alta, Media, Baja): ");
                String prioridad = scanner.nextLine().toLowerCase(); // Convertimos la prioridad a minúsculas.

                // Convertimos la prioridad a formato correcto (primera letra en mayúscula).
                String clave = prioridad.substring(0, 1).toUpperCase() + prioridad.substring(1);

                // Verificamos si la prioridad ingresada es válida.
                if (tareas.containsKey(clave)) {
                    tareas.get(clave).add(tarea); // Agregamos la tarea a la lista correspondiente.
                } else {
                    System.out.println("Prioridad no válida."); // Si no existe esa prioridad, mostramos un mensaje de error.
                }
            } 
            else if (opcion == 2) { // Mostrar todas las tareas organizadas por prioridad.
                System.out.println("\nTAREAS:");
                tareas.forEach((prioridad, lista) -> lista.forEach(t -> System.out.println(prioridad + ": " + t)));

                // Si no hay tareas en ninguna lista, mostramos un mensaje.
                if (tareas.values().stream().allMatch(List::isEmpty)) {
                    System.out.println("No hay tareas.");
                }
            } 
            else if (opcion == 3) { // Completar una tarea.
                System.out.println("\nTAREAS DISPONIBLES:");
                List<String> todasTareas = new ArrayList<>(); // Lista temporal con todas las tareas numeradas.

                // Recorremos el mapa y agregamos todas las tareas con su prioridad.
                tareas.forEach((prioridad, lista) -> lista.forEach(t -> todasTareas.add(prioridad + ": " + t)));

                // Si la lista está vacía, no hay tareas para completar.
                if (todasTareas.isEmpty()) {
                    System.out.println("No hay tareas pendientes.");
                    continue; // Volvemos al menú sin hacer nada más.
                }

                // Mostramos la lista numerada de tareas.
                for (int i = 0; i < todasTareas.size(); i++) {
                    System.out.println((i + 1) + ". " + todasTareas.get(i));
                }

                System.out.print("Selecciona el número de la tarea completada: ");
                int index = scanner.nextInt(); // Leemos la selección del usuario.

                // Validamos que el número ingresado sea correcto.
                if (index > 0 && index <= todasTareas.size()) {
                    String tareaCompletada = todasTareas.get(index - 1); // Obtenemos la tarea seleccionada.

                    // Eliminamos la tarea de la lista correspondiente.
                    tareas.forEach((prioridad, lista) -> lista.removeIf(t -> (prioridad + ": " + t).equals(tareaCompletada)));
                    System.out.println("Tarea completada.");
                } else {
                    System.out.println("Número inválido."); // Si el número no es válido, mostramos un mensaje de error.
                }
            } 
            else if (opcion == 4) { // Salir del programa.
                System.out.println("\nHasta luego.");
                break; // Rompemos el bucle y el programa finaliza.
            } 
            else { // Si el usuario ingresa una opción inválida.
                System.out.println("Opción no válida.");
            }
        }
        scanner.close(); // Cerramos el Scanner para evitar fugas de memoria.
    }
}



