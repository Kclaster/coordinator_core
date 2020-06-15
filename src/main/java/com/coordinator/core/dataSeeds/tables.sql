DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS coordinators CASCADE;
DROP TABLE IF EXISTS venues CASCADE;
DROP TABLE IF EXISTS events CASCADE;
DROP TABLE IF EXISTS desired_services CASCADE;
DROP TABLE IF EXISTS event_types CASCADE;
DROP TABLE IF EXISTS bid_statuses CASCADE;
DROP TABLE IF EXISTS bids CASCADE;
DROP TABLE IF EXISTS auth_user_roles CASCADE;
DROP TABLE IF EXISTS auth_users CASCADE;

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
    id uuid PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL,
    state varchar(2) NOT NULL,
    city varchar(30) NOT NULL,
    street_address varchar(30) NOT NULL,
    postal_code varchar(9) NOT NULL,
    is_meal_provided boolean,
    is_archived boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE coordinators
(
    id uuid PRIMARY KEY NOT NULL,
    title varchar(30),
    office_state varchar(2),
    office_city varchar(30),
    office_address varchar(30),
    office_postal_code varchar(11),
    contact_email varchar(30),
    maximum_distance_to_client integer,
    level_one_default_bid integer,
    level_two_default_bid integer,
    level_three_default_bid integer,
    is_archived boolean NOT NULL DEFAULT FALSE,
    username varchar(30) NOT NULL
);

CREATE TABLE events
(
    id uuid PRIMARY KEY NOT NULL,
    event_date timestamptz,
    event_size integer,
    event_type_id integer NOT NULL REFERENCES event_types (id),
    desired_service_id integer REFERENCES desired_services (id),
    additional_user_comments varchar(300),
    venue_id uuid REFERENCES venues (id),
    desired_state varchar(2),
    desired_city varchar(30),
    desired_postal_code varchar(9),
    coordinator_id uuid REFERENCES coordinators (id),
    is_archived boolean NOT NULL DEFAULT FALSE
);

CREATE TABLE users
(
    id uuid PRIMARY KEY NOT NULL,
    first_name varchar(30) NOT NULL,
    last_name varchar(30) NOT NULL,
    role_id integer NOT NULL,
    contact_email varchar(30) NOT NULL,
    contact_phone_number varchar(11) NOT NULL,
    event_id uuid REFERENCES events (id),
    is_archived boolean NOT NULL DEFAULT FALSE,
    username varchar(30) NOT NULL

);

CREATE TABLE roles
(
    id integer PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);

CREATE TABLE bid_statuses
(
    id integer PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);

CREATE TABLE bids
(
    id uuid PRIMARY KEY NOT NULL,
    bid_status_id integer REFERENCES bid_statuses (id) NOT NULL,
    bid_amount integer NOT NULL,
    message_to_user varchar(300),
    event_id uuid REFERENCES events (id),
    coordinator_id uuid REFERENCES coordinators (id)
);

CREATE TABLE auth_user_roles
(
    id int PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);

CREATE TABLE auth_users
(
    id uuid PRIMARY KEY NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(30) NOT NULL,
    auth_user_role_id int REFERENCES auth_user_roles (id),
    is_expired boolean NOT NULL DEFAULT FALSE,
    is_locked boolean NOT NULL DEFAULT FALSE,
    is_credentials_expired boolean NOT NULL DEFAULT FALSE,
    is_enabled boolean NOT NULL DEFAULT FALSE
);
