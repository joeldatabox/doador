package br.pucminas.model;

import br.pucminas.model.enumeration.Dia;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Joel Rodrigues on 31/10/2016.
 */
@Entity
@Table(name = "dia_atendimento")
public class DiaAtendimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_agenda", nullable = false)
    @JsonBackReference
    private Agenda agenda;
    @Column(nullable = false, columnDefinition = "enum('DOMINGO','SEGUNDA','TERCA','QUARTA','QUINTA','SEXTA','SABADO')")
    @Enumerated(EnumType.STRING)
    private Dia dia;
    @Column(name = "hr_ini_atendimento", nullable = false)
    private String hrInicioAtendimento;
    @Column(name = "hr_end_atendimento", nullable = false)
    private String hrFimAtendimento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Dia getDia() {
        return dia;
    }

    public void setDia(Dia dia) {
        this.dia = dia;
    }

    public String getHrInicioAtendimento() {
        return hrInicioAtendimento;
    }

    public void setHrInicioAtendimento(String hrInicioAtendimento) {
        this.hrInicioAtendimento = hrInicioAtendimento;
    }

    public String getHrFimAtendimento() {
        return hrFimAtendimento;
    }

    public void setHrFimAtendimento(String hrFimAtendimento) {
        this.hrFimAtendimento = hrFimAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiaAtendimento)) return false;
        DiaAtendimento that = (DiaAtendimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
