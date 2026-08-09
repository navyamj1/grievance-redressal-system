-- Replaces the plaintext dev passwords with a BCrypt hash so the existing
-- accounts still work once Spring Security is enforcing BCrypt.
--
--   sqlite3 database/app.db < database/migrations/002_hash_existing_passwords.sql
--
-- The hash below is BCrypt('temp123'). Dev convenience only, these accounts
-- should be re-registered with real passwords before anything ships.

UPDATE users
SET password = '$2a$10$xn.5BJ0opiZYWtb0zjpTlekGxaonzdR8diwfgJk.Y9w/j2V/wqGHK'
WHERE password = 'temp123';
