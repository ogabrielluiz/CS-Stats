CREATE TABLE tb_equipes (
  id_equipe SERIAL NOT NULL,
  nome VARCHAR(60),
  imagem BYTEA,
  origem VARCHAR(60),
  ativo BOOL DEFAULT TRUE,
  CONSTRAINT pk_tb_equipes_id_equipe PRIMARY KEY(id_equipe)
);

CREATE TABLE tb_jogador_equipe (
  id_jogador SERIAL NOT NULL,
  id_equipe INTEGER NOT NULL,
  nome VARCHAR(60),
  condenome VARCHAR(60),  
  ativo BOOL DEFAULT TRUE,  
  CONSTRAINT fk_tb_equipes_id_equipe FOREIGN KEY(id_equipe) REFERENCES tb_equipes (id_equipe)
);

CREATE TABLE tb_campeonato (
  id_campeonato SERIAL NOT NULL,
  nome VARCHAR(100),
  dt_inicio DATE,
  dt_fim DATE,
  imagem BYTEA,
  valor DECIMAL,
  localizacao VARCHAR(60) NULL,
  ativo BOOL DEFAULT TRUE,
  CONSTRAINT pk_tb_campeonato_id_campeonato PRIMARY KEY(id_campeonato)
);


CREATE TABLE tb_campeonato_equipes_status (
  id_campeonato INTEGER NOT NULL,
  id_equipe INTEGER NOT NULL,
  classificacao INTEGER,
  qtd_vitorias INTEGER,
  qtd_empates INTEGER,
  qtd_derrotas INTEGER,
  CONSTRAINT pk_tb_campeonato_equipes_status_id_campeonato_id_equipe PRIMARY KEY(id_campeonato, id_equipe),
  CONSTRAINT fk_tb_campeonato_id_campeonato FOREIGN KEY(id_campeonato) REFERENCES tb_campeonato (id_campeonato),
  CONSTRAINT fk_tb_equipes_id_equipe FOREIGN KEY(id_equipe) REFERENCES tb_equipes (id_equipe)
);