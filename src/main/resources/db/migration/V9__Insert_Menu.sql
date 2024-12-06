INSERT INTO public.tbl_menus(
    is_deleted, menu_date, canteen_id, category_id, created_at, created_by, id, updated_at, updated_by, name)
VALUES
    (false, '2025-01-12', 1, 1, NOW(), 0, 1, NOW(), 0, 'Breakfast Menu'),
    (false, '2025-01-13', 1, 2, NOW(), 0, 2, NOW(), 0, 'Lunch Menu'),
    (false, '2025-01-14', 1, 3, NOW(), 0, 3, NOW(), 0, 'Dinner Menu');
