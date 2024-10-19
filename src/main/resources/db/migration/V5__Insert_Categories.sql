INSERT INTO public.tbl_category(
    is_deleted, canteen_id, created_at, id, updated_at, created_by, type, updated_by)
VALUES
    (false, 1, now(), 1, now(), 'user1', 'Breakfast', 'user1'),
    (false, 1, now(), 2, now(), 'user1', 'Lunch', 'user1'),
    (false, 1, now(), 3, now(), 'user1', 'Dinner', 'user1');

