/**
  Cria-se uma agenda teste para que possa ser possivel executar todos os testes;
*/

INSERT INTO agenda (id_hemocentro, observacao, qtde_leito, status) VALUES (1, 'Agenda para testes', 10, 1);

INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '08:00', '09:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '09:00', '10:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '10:00', '11:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '11:00', '12:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '12:00', '13:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '13:00', '14:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '14:00', '15:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '15:00', '16:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '16:00', '17:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEGUNDA', '17:00', '18:00');

INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '08:00', '09:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '09:00', '10:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '10:00', '11:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '11:00', '12:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '12:00', '13:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '13:00', '14:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '14:00', '15:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '15:00', '16:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '16:00', '17:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'TERCA', '17:00', '18:00');

INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '08:00', '09:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '09:00', '10:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '10:00', '11:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '11:00', '12:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '12:00', '13:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '13:00', '14:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '14:00', '15:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '15:00', '16:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '16:00', '17:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUARTA', '17:00', '18:00');

INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '08:00', '09:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '09:00', '10:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '10:00', '11:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '11:00', '12:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '12:00', '13:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '13:00', '14:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '14:00', '15:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '15:00', '16:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '16:00', '17:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'QUINTA', '17:00', '18:00');

INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '08:00', '09:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '09:00', '10:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '10:00', '11:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '11:00', '12:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '12:00', '13:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '13:00', '14:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '14:00', '15:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '15:00', '16:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '16:00', '17:00');
INSERT INTO agenda.dia_atendimento (id_agenda, dia, hr_ini_atendimento, hr_end_atendimento) VALUES ((SELECT a.id FROM agenda AS a WHERE a.observacao LIKE 'Agenda para testes' LIMIT 1), 'SEXTA', '17:00', '18:00');