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

--- DEFINIÇÃO DOS GRUPOS POR RODADA ---
INSERT INTO filter_groups (title, display_order) VALUES
('1ª RODADA', 1),
('2ª RODADA', 2),
('3ª RODADA', 3);

--- INSERÇÃO DOS JOGOS (ASSOCIADOS ÀS RODADAS) ---
-- Jogos da 1ª Rodada (FINALIZADOS)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'FLA'), (SELECT id FROM teams WHERE acronym = 'COR'), 2, 0, '2026-09-06T19:00:00Z', 'Maracanã, Rio de Janeiro', (SELECT id FROM filter_groups WHERE title = '1ª RODADA'), FALSE),
((SELECT id FROM teams WHERE acronym = 'PAL'), (SELECT id FROM teams WHERE acronym = 'VAS'), 3, 1, '2026-09-06T21:30:00Z', 'Allianz Parque, São Paulo', (SELECT id FROM filter_groups WHERE title = '1ª RODADA'), FALSE);

-- Jogos da 2ª Rodada (FINALIZADOS, aconteceram no fim de semana)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'SAO'), (SELECT id FROM teams WHERE acronym = 'GRE'), 1, 1, '2026-09-12T19:00:00Z', 'Morumbi, São Paulo', (SELECT id FROM filter_groups WHERE title = '2ª RODADA'), FALSE),
((SELECT id FROM teams WHERE acronym = 'INT'), (SELECT id FROM teams WHERE acronym = 'BOT'), 0, 2, '2026-09-13T19:00:00Z', 'Beira-Rio, Porto Alegre', (SELECT id FROM filter_groups WHERE title = '2ª RODADA'), FALSE);

-- Jogos da 3ª Rodada (AGENDADOS para o próximo fim de semana)
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES
((SELECT id FROM teams WHERE acronym = 'FLU'), (SELECT id FROM teams WHERE acronym = 'CAP'), NULL, NULL, '2026-09-19T19:00:00Z', 'Maracanã, Rio de Janeiro', (SELECT id FROM filter_groups WHERE title = '3ª RODADA'), FALSE),
((SELECT id FROM teams WHERE acronym = 'CRU'), (SELECT id FROM teams WHERE acronym = 'SAN'), NULL, NULL, '2026-09-19T21:30:00Z', 'Mineirão, Belo Horizonte', (SELECT id FROM filter_groups WHERE title = '3ª RODADA'), FALSE),
((SELECT id FROM teams WHERE acronym = 'CAM'), (SELECT id FROM teams WHERE acronym = 'SPO'), NULL, NULL, '2026-09-20T19:00:00Z', 'Arena MRV, Belo Horizonte', (SELECT id FROM filter_groups WHERE title = '3ª RODADA'), FALSE);

--- INSERÇÃO DOS EVENTOS DE JOGO ---
-- Eventos Jogo 1ª Rodada: FLA 2x0 COR
INSERT INTO game_events (description, event_time, game_id) VALUES
('Começa o jogo!', '2026-09-06T19:00:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'COR')),
('Gol do Flamengo! Arrascaeta abre o placar.', '2026-09-06T19:40:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'COR')),
('Pedro amplia para o Flamengo!', '2026-09-06T20:31:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'COR')),
('Fim de jogo.', '2026-09-06T20:52:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'FLA' AND at.acronym = 'COR'));

-- Eventos Jogo 2ª Rodada: INT 0x2 BOT
INSERT INTO game_events (description, event_time, game_id) VALUES
('Apita o árbitro, bola rolando no Beira-Rio!', '2026-09-13T19:00:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'INT' AND at.acronym = 'BOT')),
('GOL DO BOTAFOGO! Tiquinho Soares abre o placar.', '2026-09-13T19:22:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'INT' AND at.acronym = 'BOT')),
('GOL DO BOTAFOGO! Eduardo amplia para o Glorioso.', '2026-09-13T20:15:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'INT' AND at.acronym = 'BOT')),
('Fim de jogo!', '2026-09-13T20:54:00Z', (SELECT g.id FROM games g JOIN teams ht ON g.home_team_id = ht.id JOIN teams at ON g.away_team_id = at.id WHERE ht.acronym = 'INT' AND at.acronym = 'BOT'));