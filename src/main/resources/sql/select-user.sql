SELECT U.id as id
    , U.name as name
    , U.contact_email as contactEmail
    , U.contact_phone_number as contactPhoneNumber
 from users U
WHERE U.id = :userId