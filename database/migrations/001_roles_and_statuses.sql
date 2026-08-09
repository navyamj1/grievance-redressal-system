-- Widens the role and status CHECK constraints and remaps existing values.
--
-- Hibernate's ddl-auto=update generated CHECK constraints from the old enum
-- values and will never alter them, and SQLite cannot DROP a CHECK constraint.
-- Both tables therefore have to be rebuilt.
--
-- Run BEFORE booting the app with the new enums:
--   sqlite3 database/app.db < database/migrations/001_roles_and_statuses.sql
--
--   role:   USER -> CITIZEN, plus new OFFICIAL
--   status: OPEN -> SUBMITTED, ASSIGNED -> RECEIVED, plus new UNDER_REVIEW/REJECTED

PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;

-- users -----------------------------------------------------------------
CREATE TABLE users_new (
    id integer,
    created_at timestamp not null,
    password varchar(255) not null,
    role varchar(255) not null check ((role in ('CITIZEN','OFFICIAL','ADMIN'))),
    username varchar(255) not null unique,
    primary key (id)
);

INSERT INTO users_new (id, created_at, password, role, username)
SELECT id,
       created_at,
       password,
       CASE role WHEN 'USER' THEN 'CITIZEN' ELSE role END,
       username
FROM users;

DROP TABLE users;
ALTER TABLE users_new RENAME TO users;

-- issues ----------------------------------------------------------------
CREATE TABLE issues_new (
    id integer,
    created_at timestamp not null,
    description varchar(255) not null,
    image_url varchar(255),
    latitude float not null,
    longitude float not null,
    status varchar(255) not null check ((status in
        ('SUBMITTED','RECEIVED','UNDER_REVIEW','IN_PROGRESS','RESOLVED','CLOSED','REJECTED'))),
    title varchar(255) not null,
    updated_at timestamp,
    department_id bigint,
    user_id bigint not null,
    primary key (id)
);

INSERT INTO issues_new (id, created_at, description, image_url, latitude, longitude,
                        status, title, updated_at, department_id, user_id)
SELECT id,
       created_at,
       description,
       image_url,
       latitude,
       longitude,
       CASE status
           WHEN 'OPEN'     THEN 'SUBMITTED'
           WHEN 'ASSIGNED' THEN 'RECEIVED'
           ELSE status
       END,
       title,
       updated_at,
       department_id,
       user_id
FROM issues;

DROP TABLE issues;
ALTER TABLE issues_new RENAME TO issues;

COMMIT;
PRAGMA foreign_keys=ON;
