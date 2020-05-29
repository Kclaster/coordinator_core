CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS coordinators CASCADE;
DROP TABLE IF EXISTS venues CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS desired_services CASCADE;
DROP TABLE IF EXISTS event_types CASCADE;

CREATE TABLE event_types
(
    id integer PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL,
    description varchar(300)
);

CREATE TABLE desired_services
(
    id integer PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL,
    description varchar(300)
);

CREATE TABLE venues
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    title varchar(30) NOT NULL,
    state varchar(2) NOT NULL,
    city varchar(30) NOT NULL,
    street_address varchar(30) NOT NULL,
    postal_code varchar(9) NOT NULL,
    is_meal_provided bool
);

CREATE TABLE events
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    event_date timestamptz,
    event_size integer,
    event_type_id integer NOT NULL REFERENCES event_types (id),
    desired_service_id integer REFERENCES desired_services (id),
    additional_user_comments varchar(300),
    venue_id uuid REFERENCES venues (id),
    desired_state varchar(2),
    desired_city varchar(30),
    desired_postal_code varchar(9)
);

CREATE TABLE users
(
    id uuid PRIMARY KEY DEFAULT uuid_generate_v4 (),
    first_name varchar(30) NOT NULL,
    last_name varchar(30) NOT NULL,
    role_id integer NOT NULL,
    contact_email varchar(30) NOT NULL,
    contact_phone_number varchar(11) NOT NULL,
    event_id uuid REFERENCES events (id)
);

CREATE TABLE roles
(
    id integer PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);
