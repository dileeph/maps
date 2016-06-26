
CREATE TABLE IF NOT EXISTS survey (
	id BIGSERIAL PRIMARY KEY,
	request_number VARCHAR(10),
	requested_by VARCHAR(200),
	requested_date_utc BIGINT,
	requested_date_tz VARCHAR(20),
	latitude VARCHAR(10),
	longitude VARCHAR(10),
	geom GEOMETRY

);





INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('A0001', 'Walmart Hartford CT', 1424977200556, 'America/New_York', 41.7637, -72.6851, ST_GeomFromText('POINT(-72.6851 41.7637)', 4326)); 

INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('A0001', 'Walmart Manchester CT', 1424977200558, 'America/New_York',41.7759, -72.5215, ST_GeomFromText('POINT(-72.5215 41.7759)', 4326)); 

INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('A0001', 'Walmart Bedford NH', 1424977200565, 'America/New_York',42.9463, -71.5132, ST_GeomFromText('POINT(-71.5132 42.9463)', 4326)); 

INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('A3041', 'Walmart Sunnyvale CA', 1435341600096, 'America/Los_Angeles', 37.368, -122.0363, ST_GeomFromText('POINT(-122.0363 37.368)', 4326));


INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('A3041', 'Walmart Phoenix AZ', 1423977204656, 'America/Denver', 33.4484, -112.0740, ST_GeomFromText('POINT(-112.0740 33.4484)', 4326));


INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('T0891', 'Target Hartford CT', 1434996000096, 'America/New_York', 41.7637, -72.6851, ST_GeomFromText('POINT(-72.6851 41.7637)', 4326)); 

INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('T0897', 'Target Manchester CT', 1431777600096, 'America/New_York',41.7759, -72.5215, ST_GeomFromText('POINT(-72.5215 41.7759)', 4326)); 

INSERT INTO survey (request_number, requested_by, requested_date_utc, requested_date_tz, latitude, longitude, geom)
VALUES ('C55551', 'Costco Sunnyvale CA', 1455285600096, 'America/Los_Angeles', 37.368, -122.0363, ST_GeomFromText('POINT(-122.0363 37.368)', 4326));


ALTER TABLE survey ADD COLUMN geog geography(Point,4326);

UPDATE survey SET geog = ST_MakePoint(longitude::float, latitude::float);