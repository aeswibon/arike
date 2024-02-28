DROP PROCEDURE IF EXISTS seed_db;

CREATE OR REPLACE PROCEDURE public.seed_db() EXTERNAL
SECURITY DEFINER LANGUAGE plpgsql AS
$functions$
BEGIN
    INSERT INTO public.states (id, name) VALUES (1, 'Uttar Pradesh');
    PERFORM setval('states_id_seq', (SELECT MAX(id) from states)+1);

    INSERT INTO public.districts(id, name, state_id) VALUES (1, 'Lucknow', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (2, 'Kanpur', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (3, 'Bareilly', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (4, 'Jhansi', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (5, 'Prayagraj', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (6, 'Varanasi', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (7, 'Agra', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (8, 'Meerut', 1);
    INSERT INTO public.districts(id, name, state_id) VALUES (9, 'Ayodhya', 1);
    PERFORM setval('districts_id_seq', (SELECT MAX(id) from districts)+1);

    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (1, 'Lucknow', 'OTHERS', 'LKO', 1);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (2, 'Kanpur', 'OTHERS', 'KNP', 2);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (3, 'Bareilly', 'OTHERS', 'BRY', 3);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (4, 'Jhansi', 'OTHERS', 'JHK', 4);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (5, 'Prayagraj', 'OTHERS', 'ALH', 5);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (6, 'Varanasi', 'OTHERS', 'BNS', 6);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (7, 'Agra', 'OTHERS', 'AGR', 7);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (8, 'Meerut', 'OTHERS', 'MET', 8);
    INSERT INTO public.lsg_bodies(id, name, kind, code, district_id) VALUES (9, 'Ayodhya', 'OTHERS', 'AYA', 9);
    PERFORM setval('lsg_bodies_id_seq', (SELECT MAX(id) from lsg_bodies)+1);

    INSERT INTO public.wards(id, name, lsg_bodies_id) VALUES (1, 'Cant', 1);
    INSERT INTO public.wards(id, name, lsg_bodies_id) VALUES (2, 'Indiranagar', 1);
    INSERT INTO public.wards(id, name, lsg_bodies_id) VALUES (3, 'Mohanlalganj', 1);
    PERFORM setval('wards_id_seq', (SELECT MAX(id) from wards)+1);
END
$functions$;


