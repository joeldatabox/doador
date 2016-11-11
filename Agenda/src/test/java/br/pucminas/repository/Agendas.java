package br.pucminas.repository;

import br.pucminas.model.Agenda;
import br.pucminas.model.DiaAtendimento;
import br.pucminas.model.enumeration.Dia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by master on 10/11/16.
 */
public class Agendas {
    private static List<Agenda> agendas;

    public static List<Agenda> getAgendas() {
        if (agendas == null) {
            agendas = new ArrayList<>();
            Agenda agenda = new Agenda();
            agenda.setIdHemocentro(1L);
            agenda.addDiasAtendimento(getDiasATendimento(Dia.SEGUNDA));
            agenda.addDiasAtendimento(getDiasATendimento(Dia.TERCA));
            agenda.addDiasAtendimento(getDiasATendimento(Dia.QUARTA));
            agenda.addDiasAtendimento(getDiasATendimento(Dia.QUINTA));
            agenda.addDiasAtendimento(getDiasATendimento(Dia.SEXTA));
            agenda.setQtdeLeito(20);

            Agenda agenda1 = new Agenda();
            agenda1.setIdHemocentro(2L);
            agenda1.addDiasAtendimento(getDiasATendimento(Dia.SEGUNDA));
            agenda1.addDiasAtendimento(getDiasATendimento(Dia.TERCA));
            agenda1.addDiasAtendimento(getDiasATendimento(Dia.QUARTA));
            agenda1.addDiasAtendimento(getDiasATendimento(Dia.QUINTA));
            agenda1.addDiasAtendimento(getDiasATendimento(Dia.SEXTA));
            agenda1.setQtdeLeito(10);
            agendas.add(agenda);
            agendas.add(agenda1);
        }

        return agendas;
    }

    public static Agenda agendaBuilder(Agenda agenda) {
        agenda.setIdHemocentro(1L);
        agenda.addDiasAtendimento(getDiasATendimento(Dia.SEGUNDA));
        agenda.addDiasAtendimento(getDiasATendimento(Dia.TERCA));
        agenda.addDiasAtendimento(getDiasATendimento(Dia.QUARTA));
        agenda.addDiasAtendimento(getDiasATendimento(Dia.QUINTA));
        agenda.addDiasAtendimento(getDiasATendimento(Dia.SEXTA));
        agenda.setQtdeLeito(20);
        return agenda;
    }

    private static List<DiaAtendimento> getDiasATendimento(Dia dia) {
        return Arrays.asList(
                new DiaAtendimento(dia, "00:08", "09:00"),
                new DiaAtendimento(dia, "09:00", "10:00"),
                new DiaAtendimento(dia, "10:00", "11:00"),
                new DiaAtendimento(dia, "11:00", "12:00"),
                new DiaAtendimento(dia, "12:00", "13:00"),
                new DiaAtendimento(dia, "13:00", "14:00"),
                new DiaAtendimento(dia, "14:00", "15:00"),
                new DiaAtendimento(dia, "15:00", "16:00"),
                new DiaAtendimento(dia, "16:00", "17:00"),
                new DiaAtendimento(dia, "17:00", "18:00")
        );
    }
}
