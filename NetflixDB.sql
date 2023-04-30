CREATE database netflix;
DROP database netflix;
use netflix;
select * from user;

-- Movie data insertion queries-- 
USE netflix;
INSERT INTO video_data (file_name, genre, thumbnail_id, title, release_year) VALUES
	('iron_man', 'sci-fi', 'iron_man', 'Iron Man', 2008),
	('avengers_infinity', 'sci-fi', 'avengers_infinity', 'Avengers: Infinity War', 2018),
    ('the_batman', 'action', 'the_batman', 'The Batman', 2022),
    ('inception', 'sci-fi', 'inception', 'Inception', 2010),
    ('interstellar', 'sci-fi', 'interstellar', 'Interstellar', 2014),
    ('wolf_wallstreet', 'drama', 'wolf_wallstreet', 'The Wolf of Wall Street', 2013),
    ('titanic', 'romance', 'titanic', 'Titanic', 1997),
    ('the_intern', 'comedy', 'the_intern', 'The Intern', 2015),
    ('sivaji_the_boss', 'drama', 'sivaji_the_boss', 'Sivaji: The Boss', 2007),
    ('ponniyin_selvan_1', 'drama', 'ponniyin_selvan_1', 'Ponniyin Selvan: I', 2022),
    ('baahubali_2', 'adventure', 'baahubali_2', 'Baahubali 2: The Conclusion', 2017),
    ('cars_2', 'comedy', 'cars_2', 'Cars 2', 2011),
    ('furious_7', 'action', 'furious_7', 'Furious 7', 2015),
    ('indiana_jones', 'adventure', 'indiana_jones', 'Indiana Jones And the Raiders of the Lost Ark', 1981),
    ('mi_fallout', 'action', 'mi_fallout', 'Mission: Impossible - Fallout', 2018),
    ('rrr', 'action', 'rrr', 'RRR', 2022),
    ('top_gun_maverick', 'adventure', 'top_gun_maverick', 'Top Gun: Maverick', 2022),
    ('avatar_2', 'sci-fi', 'avatar_2', 'Avatar: The Way of Water', 2022);
    
-- Check available movies
SELECT * from video_data;


-- Fetch all registered users
SELECT * from user;


