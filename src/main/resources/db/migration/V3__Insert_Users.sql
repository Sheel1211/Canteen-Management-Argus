INSERT INTO public.tbl_users (
    balance, is_active, created_at, id, password, user_name, is_deleted, updated_at,role_id
) VALUES
    (0, true, NOW(), 1, '$2a$10$nTb2uZNr/aNGFEn.B032GOXuGTTDVVoJIgCtoVfjwI1av80eIX7PG', 'user1', false, NOW(),1),

    (0, true, NOW(), 2, '$2a$10$dYI2m8MKCBQ4zFlrCFGKbeLD3aQ1HSOMyhtiYTnxkkErLiYf51rrO', 'user2', false, NOW(),2),

    (0, true, NOW(), 3, '$2a$10$r9PDSQZbL06y7w0X22KrDedresnS/OxCpwOvjffbszZFXOtBJF0we', 'user3', false, NOW(),3),

    (0, true, NOW(), 4, '$2a$10$sS69cHtaNqUQBIk39nh2weHyxa7aIxeftziFgrWxKaiVxVi0hb0wa', 'user4', false, NOW(),4),

    (0, true, NOW(), 5, '$2a$10$EtBkkkKIZ9X8HynlatHn9OAgejhWS6fSc3xI9oK1rgajFKwbMnuyG', 'user5', false, NOW(),3);