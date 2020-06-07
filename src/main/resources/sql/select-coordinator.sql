SELECT C.id as id
    , C.title as title
    , C.office_state as officeState
    , C.office_address as officeAddress
    , C.office_postal_code as officePostalCode
    , C.contact_email as contactEmail
    , C.maximum_distance_to_client as maxDistanceToClient
    , C.level_one_default_bid as levelOneDefaultBid
    , C.level_two_default_bid as levelTwoDefaultBid
    , C.level_three_default_bid as levelThreeDefaultBid
FROM
    coordinators C
WHERE C.id = :coordinator_id