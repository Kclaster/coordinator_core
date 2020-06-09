SET is_archived = TRUE
WHERE e.id = :element_id

-- [Kyle 6/7/2020] to be used with SqlHelper.sqlUpdateTable