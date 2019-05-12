-- recreate the user --
REVOKE ALL PRIVILEGES ON DATABASE "university" from postgres;
REASSIGN OWNED BY postgres TO postgres;
DROP DATABASE university;
DROP USER postgres;
CREATE USER postgres WITH PASSWORD 'postgres';
CREATE USER university_app WITH password 'postgres';

-- recreate the database --
CREATE DATABASE university OWNER postgres;
GRANT ALL PRIVILEGES ON DATABASE "university" TO postgres;

-- fulfill the database --
-- \connect sec postgres
\connect "dbname=university user=postgres password=postgres port=5432"

BEGIN;
\i ./migration/V1__Initialize.sql
\i ./migration/V1_2__createDemoStudents.sql
\i ./migration/V1_3__createDemoGroups.sql
\i ./migration/V1_4__createDemoTeachers.sql
\i ./migration/V1_5__createDemoLectures.sql
COMMIT;
