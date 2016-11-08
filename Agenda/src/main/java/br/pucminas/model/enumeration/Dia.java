package br.pucminas.model.enumeration;

import java.time.DayOfWeek;

/**
 * Created by Joel Rodrigues on 31/10/2016.
 */
public enum Dia {
    DOMINGO,
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO;

    public static Dia getDiaOfWeek(DayOfWeek day) {
        switch (day) {
            case SUNDAY:
                return DOMINGO;
            case MONDAY:
                return SEGUNDA;
            case TUESDAY:
                return TERCA;
            case WEDNESDAY:
                return QUARTA;
            case THURSDAY:
                return QUINTA;
            case FRIDAY:
                return SEXTA;
            case SATURDAY:
                return SABADO;
            default:
                throw new IllegalStateException("Dia não encontrado!");
        }
    }
}
