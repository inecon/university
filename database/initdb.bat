@echo off
SET PGOPTIONS=-c client_min_messages=error
SET PGPASSWORD=postgres
chcp 1251
psql -h localhost -p 5433 -d test -U university_app -w -f .\initdb.sql