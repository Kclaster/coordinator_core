SELECT c.id as id
    , c.title as title
    , c.office_state as officeState
    , c.office_address as officeAddress
    , c.office_postal_code as officePostalCode
    , c.office_city as officeCity
    , c.contact_email as contactEmail
    , c.maximum_distance_to_client as maxDistanceToClient
    , c.level_one_default_bid as levelOneDefaultBid
    , c.level_two_default_bid as levelTwoDefaultBid
    , c.level_three_default_bid as levelThreeDefaultBid
    , c.is_archived as isArchived
FROM
    coordinators c
WHERE c.id = :coordinator_id