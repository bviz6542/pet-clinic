CREATE TABLE IF NOT EXISTS vet (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    INDEX (last_name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS specialty (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    INDEX (name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS vet_specialty (
    vet_id INT UNSIGNED NOT NULL,
    specialty_id INT UNSIGNED NOT NULL,
    FOREIGN KEY (vet_id) REFERENCES vet(id),
    FOREIGN KEY (specialty_id) REFERENCES specialty(id),
    UNIQUE (vet_id, specialty_id)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS pet_type (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(80),
    INDEX (name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS owner (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(30),
    last_name VARCHAR(30),
    address VARCHAR(255),
    city VARCHAR(80),
    telephone VARCHAR(20),
    INDEX (last_name)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS pet (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30),
    birth_date DATE,
    type_id INT UNSIGNED NOT NULL,
    owner_id INT UNSIGNED NOT NULL,
    INDEX (name),
    FOREIGN KEY (owner_id) REFERENCES owner(id),
    FOREIGN KEY (type_id) REFERENCES pet_type(id)
    ) DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS visit (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    pet_id INT UNSIGNED NOT NULL,
    date DATE,
    description VARCHAR(255),
    FOREIGN KEY (pet_id) REFERENCES pet(id)
    ) DEFAULT CHARSET=utf8mb4;
