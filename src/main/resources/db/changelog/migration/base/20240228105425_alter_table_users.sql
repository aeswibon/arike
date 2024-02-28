ALTER TABLE users
    ADD COLUMN district_id BIGINT REFERENCES districts(id) ON DELETE SET NULL,
    ADD COLUMN deleted BOOLEAN NOT NULL DEFAULT false;

CREATE TABLE IF NOT EXISTS linked_facilities (
    facilities_uuid UUID REFERENCES facilities(uuid) ON DELETE CASCADE,
    users_uuid UUID REFERENCES users(uuid) ON DELETE CASCADE,
    PRIMARY KEY (facilities_uuid, users_uuid)
);