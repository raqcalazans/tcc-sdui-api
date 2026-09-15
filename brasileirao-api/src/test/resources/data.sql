-- Times
INSERT INTO teams (name, acronym, logo_url, description) VALUES ('Flamengo', 'FLA', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/flamengo_60x60.png', 'Clube de maior torcida do Brasil.');
INSERT INTO teams (name, acronym, logo_url, description) VALUES ('Palmeiras', 'PAL', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/palmeiras_60x60.png', 'Um dos maiores clubes de São Paulo.');
INSERT INTO teams (name, acronym, logo_url, description) VALUES ('Vasco da Gama', 'VAS', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/vasco_60x60.png', 'Clube de futebol carioca fundado em 1898.');
INSERT INTO teams (name, acronym, logo_url, description) VALUES ('Corinthians', 'COR', 'http://s.glbimg.com/es/sde/f/equipes/2014/04/14/corinthians_60x60.png', 'O Timão, um dos clubes mais populares do Brasil.');

-- Grupos de Filtro
INSERT INTO filter_groups (title, display_order) VALUES ('AO VIVO', 1);
INSERT INTO filter_groups (title, display_order) VALUES ('ENCERRADOS', 2);

-- Jogos 
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES ((SELECT id FROM teams WHERE acronym = 'FLA'), (SELECT id FROM teams WHERE acronym = 'PAL'), 1, 0, '2025-07-29T18:30:00Z', 'Maracanã', (SELECT id FROM filter_groups WHERE title = 'AO VIVO'), TRUE);
INSERT INTO games (home_team_id, away_team_id, home_goals, away_goals, game_date_time, stadium, filter_group_id, is_live) VALUES ((SELECT id FROM teams WHERE acronym = 'VAS'), (SELECT id FROM teams WHERE acronym = 'COR'), 2, 2, '2025-07-28T22:00:00Z', 'São Januário', (SELECT id FROM filter_groups WHERE title = 'ENCERRADOS'), FALSE);

-- Eventos
INSERT INTO game_events (description, event_time, game_id) VALUES ('Início do jogo', '2025-07-29T18:30:00Z', (SELECT g.id FROM games g WHERE g.is_live = TRUE));