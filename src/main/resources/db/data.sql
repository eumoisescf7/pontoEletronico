insert into usuario(cpf, nome) values ('11111111111', 'DIOGO')
insert into usuario(cpf, nome) values ('22222222222', 'CARLOS')

insert into projeto(descricao) values ('KPI')
insert into projeto(descricao) values ('Dashboard')

insert into ponto(cpf, dia, entrada1, entrada2, saida1, saida2, usuario) values ('11111111111', '2021-01-05', '2021-01-05 08:15:30', '2021-01-05 12:15:30', '2021-01-05 13:15:31', '2021-01-05 19:15:30', 'DIOGO')

insert into relatorio_ponto(dia_trabalhado, horas_devidas, horas_excedentes, horas_trabalhadas, usuario, ponto_id) values ('2021-01-05', 0, 2, 10, 11111111111, 1)

insert into projeto_ponto(dia, horas_atribuidas, projeto_id, relatorio_ponto_id) values ('2021-01-05', 5, 1, 1)
