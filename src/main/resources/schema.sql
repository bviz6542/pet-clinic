CREATE TABLE IF NOT EXISTS vet (
    vet_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    INDEX (last_name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS specialty (
    specialty_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    INDEX (name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS vet_specialty (
    vet_id INT UNSIGNED NOT NULL,
    specialty_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (vet_id) REFERENCES vet(vet_id),
    FOREIGN KEY (specialty_id) REFERENCES specialty(specialty_id),
    UNIQUE (vet_id, specialty_id)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS pet_type (
    pet_type_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    INDEX (name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS owner (
    owner_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    address VARCHAR(255),
    city VARCHAR(80),
    telephone VARCHAR(20),
    INDEX (last_name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS pet (
    pet_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    birth_date DATE,
    pet_type_id INT UNSIGNED NOT NULL,
    owner_id INT UNSIGNED NOT NULL,
    INDEX (name),
    FOREIGN KEY (owner_id) REFERENCES owner(owner_id),
    FOREIGN KEY (pet_type_id) REFERENCES pet_type(pet_type_id)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS visit (
    visit_id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pet_id INT UNSIGNED NOT NULL,
    date DATE,
    description VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES pet(pet_id)
    ) DEFAULT CHARSET=utf8mb4;
