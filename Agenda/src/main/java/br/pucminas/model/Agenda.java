package br.pucminas.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Joel Rodrigues on 31/10/2016.
 */
@Entity
public class Agenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "id_hemocentro", nullable = false)
    private Long idHemocentro;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "id_agenda")
    private List<DiaAtendimento> diasAtendimento;
    @Column(name = "qtde_leito", nullable = false)
    private Integer qtdeLeito;
    @Lob
    private String observacao;

    public Agenda() {
        this.diasAtendimento = new ArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHemocentro() {
        return idHemocentro;
    }

    public void setIdHemocentro(Long idHemocentro) {
        this.idHemocentro = idHemocentro;
    }

    public Integer getQtdeLeito() {
        return qtdeLeito;
    }

    public void setQtdeLeito(Integer qtdeLeito) {
        this.qtdeLeito = qtdeLeito;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public List<DiaAtendimento> getDiasAtendimento() {
        return diasAtendimento;
    }

    public void setDiasAtendimento(List<DiaAtendimento> diasAtendimento) {
        this.diasAtendimento = diasAtendimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Agenda)) return false;
        Agenda agenda = (Agenda) o;
        return Objects.equals(id, agenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
