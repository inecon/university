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
\i ../src/main/resources/db/migration/V1__Initialize.sql
\i ../src/main/resources/db/migration/V1_2__createDemoStudents.sql
\i ../src/main/resources/db/migration/V1_3__createDemoGroups.sql
\i ../src/main/resources/db/migration/V1_4__createDemoTeachers.sql
\i ../src/main/resources/db/migration/V1_5__createDemoLectures.sql
COMMIT;
