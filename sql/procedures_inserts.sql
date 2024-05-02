USE `PA_TP` ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_user` ;
DELIMITER $$
CREATE PROCEDURE insert_user(IN a_name VARCHAR(128), IN a_username VARCHAR(128), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_active TINYINT, IN a_role_id INT)
BEGIN
	INSERT INTO users (name, username, password, email, active, role_id)
    VALUES
    (a_name, a_username, MD5(a_password), a_email, a_active, a_role_id);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_manager` ;
DELIMITER $$
CREATE PROCEDURE insert_manager(IN a_name VARCHAR(128), IN a_username VARCHAR(128), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT)
BEGIN
    CALL insert_user(a_name, a_username, a_password, a_email, 1, a_role_id);
    
    INSERT INTO managers (user_id)
    VALUES
    (LAST_INSERT_ID());
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_reviewer` ;
DELIMITER $$
CREATE PROCEDURE insert_reviewer(IN a_name VARCHAR(128), IN a_username VARCHAR(128), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT, IN a_nif VARCHAR(9), IN a_phone VARCHAR(9), IN a_address VARCHAR(256), IN a_graduation VARCHAR(128), IN  a_specialization VARCHAR(128))
BEGIN
    CALL insert_user(a_name, a_username, a_password, a_email, 0, a_role_id);
    
    INSERT INTO reviewers (user_id, nif, phone, address, graduation, specialization)
    VALUES
    (LAST_INSERT_ID(), a_nif, a_phone, a_address, a_graduation, a_specialization);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_author` ;
DELIMITER $$
CREATE PROCEDURE insert_author(IN a_name VARCHAR(128), IN a_username VARCHAR(128), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT, IN a_nif VARCHAR(9), IN a_phone VARCHAR(9), IN a_address VARCHAR(256), IN  a_literary_style_id INT)
BEGIN
	CALL insert_user(a_name, a_username, a_password, a_email, 0, a_role_id);
    
    INSERT INTO authors (user_id, nif, phone, address, literary_style_id)
    VALUES
    (LAST_INSERT_ID(), a_nif, a_phone, a_address, a_literary_style_id);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_book` ;
DELIMITER $$
CREATE PROCEDURE insert_book(IN a_title VARCHAR(256), IN a_subtitle VARCHAR(256), IN a_pages INT, IN a_words INT, IN a_isbn VARCHAR(13), IN a_edition VARCHAR(128), IN a_literary_style_id INT, IN a_publication_type VARCHAR(128), IN  a_author_id BIGINT)
BEGIN
	INSERT INTO books(title, subtitle , pages, words, isbn, edition, literary_style_id, publication_type,  author_id)
    VALUES
    (a_title, a_subtitle , a_pages, a_words, a_isbn, a_edition, a_literary_style_id, a_publication_type,  a_author_id);
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_log`;
DELIMITER $$
CREATE PROCEDURE `insert_log`(IN a_user_id BIGINT, IN a_action VARCHAR(512))
BEGIN
	INSERT INTO logs (user_id, action)
    VALUES (a_user_id, a_action);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_review`;
DELIMITER $$
CREATE PROCEDURE `insert_review`(IN a_random_code INT, IN a_serial_number VARCHAR(128), IN a_book_id BIGINT, IN a_author_id BIGINT)
BEGIN
	INSERT INTO reviews (random_code, serial_number, book_id, author_id, status)
    VALUES (a_random_code, a_serial_number, a_book_id, a_author_id, 'iniciada');
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`insert_license`;
DELIMITER $$
CREATE PROCEDURE `insert_license`(IN a_designation VARCHAR(128), IN a_expire_date DATE, IN a_quantity INT)
BEGIN
	INSERT INTO licenses (designation, expire_date, quantity)
    VALUES (a_designation, a_expire_date, a_quantity);
END$$
DELIMITER ;