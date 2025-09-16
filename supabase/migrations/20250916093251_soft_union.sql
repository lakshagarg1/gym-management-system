CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS health_metric;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS trainer;
DROP TABLE IF EXISTS membership_plan;

-- Create membership_plan table
CREATE TABLE membership_plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    duration_months INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    benefits TEXT
);

-- Create member table
CREATE TABLE member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    address VARCHAR(255),
    membership_plan_id BIGINT,
    CONSTRAINT fk_member_membership_plan FOREIGN KEY (membership_plan_id) REFERENCES membership_plan(id)
);

-- Create equipment table
CREATE TABLE equipment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(100),
    purchase_date DATE,
    status VARCHAR(50)
);

-- Create health_metric table
CREATE TABLE health_metric (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    date DATE,
    weight DECIMAL(5,2),
    height DECIMAL(5,2),
    bmi DECIMAL(5,2),
    notes TEXT,
    CONSTRAINT fk_health_metric_member FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create payment table
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    amount DECIMAL(10,2),
    status VARCHAR(50),
    CONSTRAINT fk_payment_member FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create trainer table
CREATE TABLE trainer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    specialization VARCHAR(100)
);

-- Create schedule table
CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trainer_id BIGINT,
    start_time DATETIME,
    end_time DATETIME,
    CONSTRAINT fk_schedule_trainer FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);

-- Sample data for membership_plan
INSERT INTO membership_plan (name, description, duration_months, price, benefits) VALUES
('Basic', 'Basic gym access', 1, 50.00, 'Gym access, locker'),
('Standard', 'Standard membership with classes', 3, 135.00, 'Gym access, group classes, locker'),
('Premium', 'Premium membership with personal training', 6, 240.00, 'Gym access, group classes, personal training, locker');

-- Sample data for member
INSERT INTO member (name, phone, email, address, membership_plan_id) VALUES
('John Doe', '9876543210', 'john.doe@example.com', '123 Main St, City', 1),
('Jane Smith', '9123456789', 'jane.smith@example.com', '456 Park Ave, Town', 2),
('Mike Johnson', '9988776655', 'mike.johnson@example.com', '789 Elm St, Village', 3);

-- Sample data for equipment
INSERT INTO equipment (name, type, purchase_date, status) VALUES
('Treadmill Model X', 'Cardio', '2024-01-15', 'Active'),
('Dumbbells Set', 'Weight Training', '2024-02-10', 'Active'),
('Exercise Bike Pro', 'Cardio', '2024-03-05', 'Maintenance');

-- Sample data for trainer
INSERT INTO trainer (name, specialization) VALUES
('Alice Brown', 'Yoga'),
('Bob Green', 'Weightlifting');

-- Sample data for health_metric
INSERT INTO health_metric (member_id, date, weight, height, bmi, notes) VALUES
(1, '2025-01-01', 70.5, 175.0, 23.0, 'Good progress'),
(2, '2025-01-02', 65.0, 160.0, 25.4, 'Needs improvement');

-- Sample data for payment
INSERT INTO payment (member_id, amount, status) VALUES
(1, 50.00, 'Completed'),
(2, 135.00, 'Pending');

-- Sample data for schedule
INSERT INTO schedule (trainer_id, start_time, end_time) VALUES
(1, '2025-01-20 10:00:00', '2025-01-20 11:00:00'),
(2, '2025-01-21 15:00:00', '2025-01-21 16:00:00');