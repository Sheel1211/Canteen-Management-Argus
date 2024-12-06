INSERT INTO public.tbl_category(
    is_deleted, canteen_id, created_at, id, updated_at, created_by, type, updated_by)
VALUES
    (false, 1, now(), 1, now(), 0, 'Breakfast', 0),
    (false, 1, now(), 2, now(), 0, 'Lunch', 0),
    (false, 1, now(), 3, now(), 0, 'Dinner', 0);

