import java.util.*;

public class OrganizadorTareas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> tareas = new LinkedHashMap<>();
        tareas.put("Alta", new ArrayList<>());
        tareas.put("Media", new ArrayList<>());
        tareas.put("Baja", new ArrayList<>());

        while (true) {
            System.out.println("\nMENÚ");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Ver tareas");
            System.out.println("3. Completar tarea");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            if (opcion == 1) {
                System.out.print("\nDescribe tu tarea: ");
                String tarea = scanner.nextLine();
                System.out.print("Prioridad (Alta, Media, Baja): ");
                String prioridad = scanner.nextLine().toLowerCase();
                
                String clave = prioridad.substring(0, 1).toUpperCase() + prioridad.substring(1);
                if (tareas.containsKey(clave)) {
                    tareas.get(clave).add(tarea);
                } else {
                    System.out.println("Prioridad no válida.");
                }
            } 
            else if (opcion == 2) {
                System.out.println("\nTAREAS:");
                tareas.forEach((prioridad, lista) -> lista.forEach(t -> System.out.println(prioridad + ": " + t)));
                if (tareas.values().stream().allMatch(List::isEmpty)) {
                    System.out.println("No hay tareas.");
                }
            } 
            else if (opcion == 3) {
                System.out.println("\nTAREAS DISPONIBLES:");
                List<String> todasTareas = new ArrayList<>();
                tareas.forEach((prioridad, lista) -> lista.forEach(t -> todasTareas.add(prioridad + ": " + t)));
                
                if (todasTareas.isEmpty()) {
                    System.out.println("No hay tareas pendientes.");
                    continue;
                }
                
                for (int i = 0; i < todasTareas.size(); i++) {
                    System.out.println((i + 1) + ". " + todasTareas.get(i));
                }
                
                System.out.print("Selecciona el número de la tarea completada: ");
                int index = scanner.nextInt();
                if (index > 0 && index <= todasTareas.size()) {
                    String tareaCompletada = todasTareas.get(index - 1);
                    tareas.forEach((prioridad, lista) -> lista.removeIf(t -> (prioridad + ": " + t).equals(tareaCompletada)));
                    System.out.println("Tarea completada.");
                } else {
                    System.out.println("Número inválido.");
                }
            } 
            else if (opcion == 4) {
                System.out.println("\nHasta luego.");
                break;
            } 
            else {
                System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}


