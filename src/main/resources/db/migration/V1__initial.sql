CREATE TABLE public.t_observatory(
    id varchar(2) not null,
    temperature varchar(25) not null,
    distance varchar(25) not null,
    primary key(id)
);

-- Initial observatory data
INSERT INTO t_observatory(id, temperature, distance)
	VALUES('AU', 'CELSIUS', 'KILOMETER');
INSERT INTO t_observatory(id, temperature, distance)
	VALUES('US', 'FAHRENHEIT', 'MILE');
INSERT INTO t_observatory(id, temperature, distance)
	VALUES('FR', 'KELVIN', 'METER');

CREATE TABLE public.t_balloon(
    id bigint not null,
    min_temperature double precision,
    max_temperature double precision,
    mean_temperature double precision,
    total_distance double precision not null,
    observation_count bigint not null,
    version bigint not null,
    PRIMARY KEY(id)
);

-- Default balloon
INSERT INTO t_balloon(id, total_distance, observation_count, version)
	VALUES(1, 0, 0, 0);

CREATE SEQUENCE seq_balloon_id
	INCREMENT BY 1
	START 2
	OWNED BY t_balloon.id;

CREATE TABLE public.t_observation(
    id bigint not null,
    observation_time timestamp without time zone not null,
    x double precision not null,
    y double precision not null,
    temperature double precision not null,
    balloon_id bigint not null,
    observatory_id varchar(2) not null,
    PRIMARY KEY(id)
);

CREATE SEQUENCE seq_observation_id
	INCREMENT BY 1
	START 1
	OWNED BY t_observation.id;

CREATE INDEX idx_timestamp_balloon
    ON t_observation
    (observation_time, balloon_id);