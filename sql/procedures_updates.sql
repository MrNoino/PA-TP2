USE `PA_TP` ;

DROP PROCEDURE IF EXISTS `PA_TP`.`update_user` ;
DELIMITER $$
CREATE PROCEDURE update_user(IN a_id BIGINT, IN a_name VARCHAR(128), IN a_username VARCHAR(100), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT)
BEGIN
	UPDATE users 
    SET 
    name = a_name,
    username = a_username,
    password = IFNULL(MD5(a_password), password), 
    email = a_email,
    role_id = a_role_id
    WHERE id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`update_reviewer` ;
DELIMITER $$
CREATE PROCEDURE update_reviewer(IN a_id BIGINT,  IN a_name VARCHAR(128), IN a_username VARCHAR(100), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT, IN a_nif VARCHAR(9), IN a_phone VARCHAR(9), IN a_address VARCHAR(256), IN a_graduation VARCHAR(128), IN  a_specialization VARCHAR(128))
BEGIN
    CALL update_user(a_id, a_name, a_username, a_password, a_email, a_role_id);
    
    UPDATE reviewers
    SET nif= a_nif,
    phone= a_phone,
    address= a_address,
    graduation= a_graduation,
    specialization= a_specialization
	WHERE user_id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`update_author` ;
DELIMITER $$
CREATE PROCEDURE update_author(IN a_id BIGINT, IN a_name VARCHAR(128), IN a_username VARCHAR(100), IN a_password VARCHAR(256), IN a_email VARCHAR(256), IN a_role_id INT, IN a_nif VARCHAR(9), IN a_phone VARCHAR(9), IN a_address VARCHAR(256), IN  a_literary_style_id INT)
BEGIN
    CALL update_user(a_id, a_name, a_username, a_password, a_email, a_role_id);
    
	UPDATE authors
    SET nif= a_nif,
    phone= a_phone,
    address= a_address,
    literary_style_id= a_literary_style_id
    WHERE user_id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`change_deleted_user` ;
DELIMITER $$
CREATE PROCEDURE change_deleted_user(IN a_id BIGINT, IN a_deleted TINYINT)
BEGIN
    UPDATE users
    SET 
    deleted = a_deleted
    WHERE id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`change_user_status` ;
DELIMITER $$
CREATE PROCEDURE change_user_status(IN a_id BIGINT, IN a_active TINYINT)
BEGIN
    UPDATE users
    SET 
    active = a_active
    WHERE id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`update_book` ;
DELIMITER $$
CREATE PROCEDURE update_book(IN a_id BIGINT, IN a_title VARCHAR(256), IN a_subtitle VARCHAR(256), IN a_pages INT, IN a_words INT, IN a_isbn VARCHAR(13), IN a_edition VARCHAR(128), IN a_literary_style_id INT, IN a_publication_type VARCHAR(128), IN  a_author_id BIGINT)
BEGIN
	UPDATE books
    SET 
    title= a_title,
    subtitle= a_subtitle,
    pages= a_pages,
    words= a_words,
    isbn= a_isbn,
    edition= a_edition,
    literary_style_id= a_literary_style_id,
    publication_type= a_publication_type,
    author_id= a_author_id
    WHERE id = a_id;
END $$
DELIMITER ;

DROP PROCEDURE IF EXISTS `PA_TP`.`update_license_quantity`;
DELIMITER $$
CREATE PROCEDURE `update_license_quantity`(IN a_id INT, IN a_quantity INT)
BEGIN
	UPDATE licenses
    SET quantity = IF((quantity + a_quantity) >= 0, (quantity + a_quantity), quantity)
    WHERE id = a_id;
END$$
DELIMITER ;