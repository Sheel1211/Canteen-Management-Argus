INSERT INTO public.tbl_authorities (
    id, name, created_at, created_by, is_deleted, updated_at, updated_by
) VALUES
    (1, 'ROLE_ADMIN', NOW(), 'SYSTEM', false, NOW(), 'SYSTEM'),
    (2, 'ROLE_CANTEEN_MANAGER', NOW(), 'SYSTEM', false, NOW(), 'SYSTEM'),
    (3, 'ROLE_EMPLOYEE', NOW(), 'SYSTEM', false, NOW(), 'SYSTEM');
