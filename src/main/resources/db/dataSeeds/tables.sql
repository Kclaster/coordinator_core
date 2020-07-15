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
DROP TABLE IF EXISTS zip_codes CASCADE;
DROP TABLE IF EXISTS coordinators_zip_codes CASCADE;


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

CREATE TABLE auth_user_roles
(
    id int PRIMARY KEY NOT NULL,
    title varchar(30) NOT NULL
);

CREATE TABLE auth_users
(
    id uuid PRIMARY KEY NOT NULL,
    username varchar(30) NOT NULL,
    password varchar(60) NOT NULL,
    auth_user_role_id int REFERENCES auth_user_roles (id),
    is_expired boolean NOT NULL DEFAULT TRUE,
    is_locked boolean NOT NULL DEFAULT TRUE,
    is_credentials_expired boolean NOT NULL DEFAULT TRUE,
    is_enabled boolean NOT NULL DEFAULT TRUE
);

CREATE TABLE zip_codes (
    id uuid PRIMARY KEY NOT NULL,
    title varchar(9)
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
    auth_user_id uuid REFERENCES auth_users (id)
);

CREATE TABLE coordinators_zip_codes
(
    id uuid PRIMARY KEY NOT NULL,
    coordinator_id uuid REFERENCES coordinators (id),
    zip_code_id uuid REFERENCES zip_codes (id)
);

CREATE TABLE users
(
    id uuid PRIMARY KEY NOT NULL,
    name varchar(30),
    contact_email varchar(30) NOT NULL,
    contact_phone_number varchar(11),
    is_archived boolean NOT NULL DEFAULT FALSE,
    auth_user_id uuid REFERENCES auth_users (id)
);

CREATE TABLE events
(
    id uuid PRIMARY KEY NOT NULL,
    event_end_date timestamptz,
    event_start_date timestamptz,
    event_size integer,
    user_id UUID NOT NULL REFERENCES users (id),
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

