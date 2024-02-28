CREATE TABLE IF NOT EXISTS facilities
(
    uuid          UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    pincode       BIGINT       NOT NULL,
    phone         VARCHAR(255) NOT NULL,
    ward_id       BIGINT REFERENCES wards (id) ON DELETE CASCADE,
    kind          VARCHAR(255) NOT NULL,
    created_by_uuid UUID REFERENCES users (uuid) ON DELETE CASCADE
);