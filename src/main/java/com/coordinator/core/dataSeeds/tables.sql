CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users
(
  id uuid DEFAULT uuid_generate_v4 (),
  first_name varchar(30) NOT NULL,
  last_name varchar(30) NOT NULL
);