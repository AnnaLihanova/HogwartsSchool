-- liquibase formatted sql

-- changeset annaLihanova:1
CREATE INDEX IF NOT EXISTS student_name_index ON student (name);

CREATE INDEX IF NOT EXISTS faculty_name_and_color_index ON faculty (name, color);

-- changeset annaLihanova:2
DROP INDEX IF EXISTS student_age_index;


