CREATE DATABASE IF NOT EXISTS mydb;
USE mydb;

-- Drop tables if they exist to avoid conflicts
DROP TABLE IF EXISTS member;
DROP TABLE IF EXISTS equipment;
DROP TABLE IF EXISTS health_metric;
DROP TABLE IF EXISTS membership_plan;
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS schedule;
DROP TABLE IF EXISTS trainer;

-- Create membership_plan table
CREATE TABLE membership_plan (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    duration_months INT NOT NULL,
    price DECIMAL(10,2) NOT NULL
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
    description VARCHAR(255),
    quantity INT
);

-- Create health_metric table
CREATE TABLE health_metric (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    metric_name VARCHAR(100),
    metric_value VARCHAR(100),
    recorded_date DATE,
    CONSTRAINT fk_health_metric_member FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create payment table
CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    member_id BIGINT,
    amount DECIMAL(10,2),
    payment_date DATE,
    CONSTRAINT fk_payment_member FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Create trainer table
CREATE TABLE trainer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    specialty VARCHAR(100),
    phone VARCHAR(20)
);

-- Create schedule table
CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trainer_id BIGINT,
    member_id BIGINT,
    schedule_date DATE,
    schedule_time TIME,
    CONSTRAINT fk_schedule_trainer FOREIGN KEY (trainer_id) REFERENCES trainer(id),
    CONSTRAINT fk_schedule_member FOREIGN KEY (member_id) REFERENCES member(id)
);

-- Sample data for membership_plan
INSERT INTO membership_plan (name, duration_months, price) VALUES
('Basic', 1, 50.00),
('Standard', 3, 135.00),
('Premium', 6, 240.00);

-- Sample data for member
INSERT INTO member (name, phone, email, address, membership_plan_id) VALUES
('John Doe', '9876543210', 'john.doe@example.com', '123 Main St, City', 1),
('Jane Smith', '9123456789', 'jane.smith@example.com', '456 Park Ave, Town', 2),
('Mike Johnson', '9988776655', 'mike.johnson@example.com', '789 Elm St, Village', 3);

-- Sample data for equipment
INSERT INTO equipment (name, description, quantity) VALUES
('Treadmill', 'Cardio equipment', 5),
('Dumbbells', 'Weight training', 20),
('Exercise Bike', 'Cardio equipment', 3);

-- Sample data for trainer
INSERT INTO trainer (name, specialty, phone) VALUES
('Alice Brown', 'Yoga', '9001234567'),
('Bob Green', 'Weightlifting', '9007654321');

-- Sample data for health_metric
INSERT INTO health_metric (member_id, metric_name, metric_value, recorded_date) VALUES
(1, 'Weight', '70kg', '2025-01-01'),
(2, 'BMI', '22', '2025-01-02');

-- Sample data for payment
INSERT INTO payment (member_id, amount, payment_date) VALUES
(1, 50.00, '2025-01-01'),
(2, 135.00, '2025-01-02');

-- Sample data for schedule
INSERT INTO schedule (trainer_id, member_id, schedule_date, schedule_time) VALUES
(1, 1, '2025-09-20', '10:00:00'),
(2, 2, '2025-09-21', '15:00:00');
