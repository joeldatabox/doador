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
        }
        for (int i = 0; i < 5; i++) {
            Agenda agenda = new Agenda();
            agenda.setIdHemocentro(1L);
            agenda.setDiasAtendimento();
        }
        return agendas;
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
        //return dias;
    }
}
