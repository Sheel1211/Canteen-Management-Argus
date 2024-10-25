INSERT INTO public.tbl_users (
    balance, is_active, created_at, id, password, user_name, is_deleted, updated_at
) VALUES
    (0, true, NOW(), 1, '$2a$10$nTb2uZNr/aNGFEn.B032GOXuGTTDVVoJIgCtoVfjwI1av80eIX7PG', 'user1', false, NOW()),

    (0, true, NOW(), 2, '$2a$10$dYI2m8MKCBQ4zFlrCFGKbeLD3aQ1HSOMyhtiYTnxkkErLiYf51rrO', 'user2', false, NOW()),

    (0, true, NOW(), 3, '$2a$10$r9PDSQZbL06y7w0X22KrDedresnS/OxCpwOvjffbszZFXOtBJF0we', 'user3', false, NOW());
