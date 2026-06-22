    package com.example.GestionAmbientes.dto;

    import java.time.LocalDateTime;

    public class ReservaDto {

        private Long ambienteId;
        private String instructor;
        private LocalDateTime inicio;
        private LocalDateTime fin;
        private int aprendices;
        private String estado;

        public ReservaDto() {
        }

        public Long getAmbienteId() {
            return ambienteId;
        }

        public void setAmbienteId(Long ambienteId) {
            this.ambienteId = ambienteId;
        }

        public String getInstructor() {
            return instructor;
        }

        public void setInstructor(String instructor) {
            this.instructor = instructor;
        }

        public LocalDateTime getInicio() {
            return inicio;
        }

        public void setInicio(LocalDateTime inicio) {
            this.inicio = inicio;
        }

        public LocalDateTime getFin() {
            return fin;
        }

        public void setFin(LocalDateTime fin) {
            this.fin = fin;
        }

        public int getAprendices() {
            return aprendices;
        }

        public void setAprendices(int aprendices) {
            this.aprendices = aprendices;
        }

        public String getEstado() {
            return estado;
        }

        public void setEstado(String estado) {
            this.estado = estado;
        }
    }