INSERT INTO service_date_place
(
    id
    , address
    , zip_code
    , title
    , contact_phone_number
    , service_date
    , is_selected
) VALUES (
    :serviceDatePlaceId
    , :address
    , :zipCode
    , :title
    , :contactPhoneNumber
    , :serviceDate
    , :isSelected
)