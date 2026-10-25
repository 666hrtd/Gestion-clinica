import java.time.LocalDateTime;

public class Cita {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private Consultorio consultorio;
    private LocalDateTime fechaHora;
    private String estado;

    public Cita(int id, Paciente paciente, Medico medico, Consultorio consultorio, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.consultorio = consultorio;
        this.fechaHora = fechaHora;
        this.estado = "PROGRAMADA";
    }

    public int getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public Medico getMedico() { return medico; }
    public Consultorio getConsultorio() { return consultorio; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public String getEstado() { return estado; }
    
    public void setEstado(String estado) { this.estado = estado; }
}