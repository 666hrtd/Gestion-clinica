import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Cita> listaCitas = new ArrayList<>();

        Especialidad cardiologia = new Especialidad(1, "Cardiologia");
        Medico medico1 = new Medico(1, "Dr. Perez", cardiologia, true);
        Consultorio consultorio1 = new Consultorio(1, "Consultorio 101");
        Paciente paciente1 = new Paciente(1, "Carlos Ruiz", "555-1234");

        int opcion = 0;

        do {
            System.out.println("\n    SISTEMA DE GESTION CLINICA    ");
            System.out.println("1. Programar cita");
            System.out.println("2. Consultar citas programadas");
            System.out.println("3. Modificar cita");
            System.out.println("4. Cancelar cita");
            System.out.println("5. Historial por paciente");
            System.out.println("6. Citas por medico y total de consultas");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opcion: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    LocalDateTime fechaCita = LocalDateTime.of(2026, 11, 10, 10, 0);
                    boolean ocupado = false;

                    for (Cita c : listaCitas) {
                        if (c.getFechaHora().equals(fechaCita) && c.getEstado().equals("PROGRAMADA")) {
                            if (c.getMedico().getId() == medico1.getId() || c.getConsultorio().getId() == consultorio1.getId()) {
                                ocupado = true;
                                break;
                            }
                        }
                    }

                    if (!medico1.isActivo()) {
                        System.out.println("Error: El medico no esta activo.");
                    } else if (ocupado) {
                        System.out.println("Error: El medico o el consultorio ya estan ocupados en ese horario.");
                    } else {
                        Cita nuevaCita = new Cita(listaCitas.size() + 1, paciente1, medico1, consultorio1, fechaCita);
                        listaCitas.add(nuevaCita);
                        System.out.println("Cita programada con exito.");
                    }
                    break;

                case 2:
                    System.out.println("\n    CITAS PROGRAMADAS    ");
                    if (listaCitas.isEmpty()) {
                        System.out.println("No hay citas registradas.");
                    } else {
                        for (Cita c : listaCitas) {
                            System.out.println("ID: " + c.getId() + " | Paciente: " + c.getPaciente().getNombre() + " | Medico: " + c.getMedico().getNombre() + " | Fecha: " + c.getFechaHora() + " | Estado: " + c.getEstado());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID de la cita a modificar: ");
                    int idMod = scanner.nextInt();
                    scanner.nextLine();
                    boolean encontradaMod = false;

                    for (Cita c : listaCitas) {
                        if (c.getId() == idMod) {
                            encontradaMod = true;
                            c.setFechaHora(LocalDateTime.of(2026, 11, 12, 11, 0));
                            System.out.println("Cita modificada con exito para el 12/11/2026 a las 11:00.");
                        }
                    }
                    if (!encontradaMod) {
                        System.out.println("Cita no encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID de la cita a cancelar: ");
                    int idCan = scanner.nextInt();
                    scanner.nextLine();
                    boolean encontradaCan = false;

                    for (Cita c : listaCitas) {
                        if (c.getId() == idCan) {
                            encontradaCan = true;
                            c.setEstado("CANCELADA");
                            System.out.println("Cita cancelada con exito.");
                        }
                    }
                    if (!encontradaCan) {
                        System.out.println("Cita no encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("\n    HISTORIAL DEL PACIENTE: " + paciente1.getNombre() + "    ");
                    boolean tieneCitasPac = false;
                    for (Cita c : listaCitas) {
                        if (c.getPaciente().getId() == paciente1.getId()) {
                            tieneCitasPac = true;
                            System.out.println("ID: " + c.getId() + " | Medico: " + c.getMedico().getNombre() + " | Fecha: " + c.getFechaHora() + " | Estado: " + c.getEstado());
                        }
                    }
                    if (!tieneCitasPac) {
                        System.out.println("El paciente no tiene historial de citas.");
                    }
                    break;

                case 6:
                    System.out.println("\n    REPORTE POR MEDICO: " + medico1.getNombre() + "    ");
                    int totalConsultas = 0;
                    for (Cita c : listaCitas) {
                        if (c.getMedico().getId() == medico1.getId()) {
                            System.out.println("ID: " + c.getId() + " | Paciente: " + c.getPaciente().getNombre() + " | Fecha: " + c.getFechaHora() + " | Estado: " + c.getEstado());
                            if (c.getEstado().equals("PROGRAMADA") || c.getEstado().equals("REALIZADA")) {
                                totalConsultas++;
                            }
                        }
                    }
                    System.out.println("Cantidad de consultas/citas activas para este medico: " + totalConsultas);
                    break;

                case 7:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        } while (opcion != 7);

        scanner.close();
    }
}