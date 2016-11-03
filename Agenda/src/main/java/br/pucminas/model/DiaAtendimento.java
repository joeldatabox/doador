package br.pucminas.model;

import br.pucminas.model.enumeration.Dia;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
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
    private Agenda agenda;
    @Column(nullable = false, columnDefinition = "enum('DOMINGO','SEGUNDA','TERCA','QUINTA','SEXTA','SABADO')")
    @Enumerated(EnumType.STRING)
    private Dia dia;
    @Temporal(TemporalType.TIME)
    @Column(name = "hr_ini_atendimento", nullable = false)
    private Date hrInicioAtendimento;
    @Temporal(TemporalType.TIME)
    @Column(name = "hr_end_atendimento", nullable = false)
    private Date hrFimAtendimento;

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

    public Date getHrInicioAtendimento() {
        return hrInicioAtendimento;
    }

    public void setHrInicioAtendimento(Date hrInicioAtendimento) {
        this.hrInicioAtendimento = hrInicioAtendimento;
    }

    public Date getHrFimAtendimento() {
        return hrFimAtendimento;
    }

    public void setHrFimAtendimento(Date hrFimAtendimento) {
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
