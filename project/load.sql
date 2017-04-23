-- Drop the previous table declarations.
@drop.sql
commit;
-- Reload the table declarations.
@schema.sql
commit;
-- Load the table data.
@data.sql
commit;

