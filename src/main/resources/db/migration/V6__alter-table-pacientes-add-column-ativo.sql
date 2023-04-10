alter table pacientes add ativo tinyint;
UPDATE pacientes SET ativo = 1;
