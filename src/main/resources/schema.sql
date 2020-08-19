DROP TABLE appointments IF EXISTS;
DROP TABLE vets IF EXISTS;
DROP TABLE pets IF EXISTS;
DROP TABLE types IF EXISTS;

CREATE TABLE vets (
  id         INTEGER IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vets_last_name ON vets (last_name);

CREATE TABLE types (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(80)
);
CREATE INDEX types_name ON types (name);

CREATE TABLE pets (
  id         INTEGER IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type_id    INTEGER NOT NULL
);
ALTER TABLE pets ADD CONSTRAINT fk_pets_types FOREIGN KEY (type_id) REFERENCES types (id);
CREATE INDEX pets_name ON pets (name);

CREATE TABLE appointments (
  id          INTEGER IDENTITY PRIMARY KEY,  
  vet_id      INTEGER NOT NULL,
  pet_id      INTEGER NOT NULL,
  appt_date  DATE,
  appt_time  TIME,
  description VARCHAR(255)
);
ALTER TABLE appointments ADD CONSTRAINT fk_appointments_pets FOREIGN KEY (pet_id) REFERENCES pets (id);
ALTER TABLE appointments ADD CONSTRAINT fk_appointments_vets FOREIGN KEY (vet_id) REFERENCES vets (id);
ALTER TABLE appointments ADD CONSTRAINT uc_appointments_vets UNIQUE(vet_id, appt_date, appt_time);
CREATE INDEX appointments_pet_id ON appointments (pet_id);
