-- liquibase formatted sql

-- changeset annaLihanova:1
CREATE INDEX student_name_index ON student (name);

CREATE INDEX faculty_name_and_color_index ON faculty (name, color);

-- changeset annaLihanova:2
CREATE INDEX student_age_index ON student (age);

DROP INDEX student_age_index;