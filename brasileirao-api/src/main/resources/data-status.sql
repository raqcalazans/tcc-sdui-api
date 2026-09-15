-- Inserção dos Times
INSERT INTO teams (name, acronym, logo_url, description) VALUES
('Vasco da Gama', 'VAS', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/vasco_60x60.png', 'Clube de futebol carioca fundado em 1898, conhecido por sua tradição e torcida apaixonada.'),
('Avaí', 'AVA', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/avai_60x60_.png', 'Clube catarinense da capital Florianópolis, conhecido como Leão da Ilha.'),
('Sport', 'SPO', 'http://s.glbimg.com/es/sde/f/equipes/2014/09/15/sport_60x60.png', 'Tradicional clube pernambucano, com grande história no futebol nordestino.'),
('Internacional', 'INT', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/internacional_60x60.png', 'Gigante do futebol gaúcho, conhecido como Colorado, com grandes conquistas internacionais.'),
('Palmeiras', 'PAL', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/palmeiras_60x60.png', 'Um dos maiores clubes de São Paulo, com rica história e muitos títulos nacionais e internacionais.'),
('Chapecoense', 'CHA', 'http://s.glbimg.com/es/sde/f/equipes/2015/05/06/chapecoense_60x60.png', 'Clube catarinense que superou adversidades e se tornou um símbolo de resiliência.'),
('Atlético-MG', 'CAM', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/atletico_mg_60x60.png', 'O Galo de Minas, um dos clubes mais populares de Minas Gerais, com torcida fanática.'),
('Coritiba', 'CFC', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/coritiba_60x60.png', 'Time tradicional do Paraná, conhecido como Coxa, com uma das maiores torcidas do estado.'),
('Grêmio', 'GRE', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/gremio_60x60.png', 'Tradição e glória no Rio Grande do Sul, o Tricolor Gaúcho é um dos mais vitoriosos do Brasil.'),
('Cruzeiro', 'CRU', 'http://s.glbimg.com/es/sde/f/equipes/2015/04/29/cruzeiro_65.png', 'Maior campeão da Copa do Brasil, a Raposa de Minas é um gigante do futebol nacional.'),
('Athletico-PR', 'CAP', 'http://s.glbimg.com/es/sde/f/equipes/2015/06/24/atletico-pr_2015_65.png', 'O Furacão paranaense, conhecido por sua Arena moderna e futebol ousado.'),
('São Paulo', 'SAO', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/sao_paulo_60x60.png', 'Um dos clubes mais vitoriosos do Brasil, conhecido como Tricolor Paulista.'),
('Flamengo', 'FLA', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/flamengo_60x60.png', 'Clube de maior torcida do Brasil, com uma história rica e muitos títulos.'),
('Corinthians', 'COR', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/corinthians_60x60.png', 'O Timão, um dos clubes mais populares do Brasil, com uma torcida fiel e apaixonada.'),
('Ponte Preta', 'PON', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/ponte_preta_60x60.png', 'A Macaca, um dos times mais tradicionais do interior de São Paulo.'),
('Figueirense', 'FIG', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/figueirense_60x60.png', 'O Furacão do Estreito, clube catarinense com grande história no futebol regional.'),
('Goiás', 'GOI', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/goias_60x60.png', 'O Esmeraldino, tradicional clube de Goiás, com forte presença no Centro-Oeste.'),
('Fluminense', 'FLU', 'http://s.glbimg.com/es/sde/f/equipes/2015/05/05/fluminense-escudo-65x65.png', 'O Tricolor das Laranjeiras, um dos mais antigos e tradicionais clubes do Rio de Janeiro.'),
('Santos', 'SAN', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/santos_60x60.png', 'O Peixe, um dos clubes mais gloriosos do futebol brasileiro, conhecido por revelar grandes craques.'),
('Botafogo', 'BOT', 'https://s.glbimg.com/es/sde/f/equipes/2014/04/14/botafogo_60x60.png', 'O Glorioso, um dos clubes mais tradicionais do Rio de Janeiro, conhecido por sua icônica estrela solitária no escudo.');

--- INSERÇÃO DOS JOGOS ---
--- DEFINIÇÃO DOS GRUPOS DA UI ---
INSERT INTO filter_groups (title, display_order) VALUES
('AO VIVO', 1),
('AGENDADOS', 2),
('ENCERRADOS', 3);

--- INSERÇÃO DOS JOGOS ---
-- Jogo Ao Vivo (is_live = TRUE)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'CAM'), (SELECT id FROM teams WHERE acronym = 'INT'), 0, 0, '2025-07-29T00:00:00Z', 'Arena MRV, Belo Horizonte', (SELECT id FROM filter_groups WHERE title = 'AO VIVO'), TRUE);

-- Jogo Agendado (is_live = FALSE)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'SAO'), (SELECT id FROM teams WHERE acronym = 'CRU'), NULL, NULL, '2025-07-30T00:00:00Z', 'Morumbi, São Paulo', (SELECT id FROM filter_groups WHERE title = 'AGENDADOS'), FALSE);

-- Jogo Finalizado (is_live = FALSE)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'FLA'), (SELECT id FROM teams WHERE acronym = 'PAL'), 3, 1, '2025-07-26T19:00:00Z', 'Maracanã, Rio de Janeiro', (SELECT id FROM filter_groups WHERE title = 'ENCERRADOS'), FALSE);


--- INSERÇÃO DOS EVENTOS DE JOGO ---

-- Eventos Jogo Ao Vivo: CAM 0x0 INT
INSERT INTO game_events (description, event_time, game_id) VALUES
('Começa o jogo na Arena MRV!', '2025-07-29T00:00:00Z', (SELECT g.id FROM games g WHERE g.is_live = TRUE)),
('Cartão amarelo para o Internacional.', '2025-07-29T00:18:00Z', (SELECT g.id FROM games g WHERE g.is_live = TRUE));

-- Eventos Jogo Finalizado: FLA 3x1 PAL
INSERT INTO game_events (description, event_time, game_id) VALUES
('Início do jogo', '2025-07-26T19:00:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL')),
('Gol do Flamengo', '2025-07-26T19:22:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL')),
('Gol do Palmeiras', '2025-07-26T19:41:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL')),
('Gol do Flamengo', '2025-07-26T20:10:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL')),
('Gol do Flamengo', '2025-07-26T20:35:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL')),
('Fim de jogo', '2025-07-26T20:50:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'PAL'));