USE `PA_TP` ;

DROP VIEW IF EXISTS `PA_TP`.`get_users` ;
CREATE VIEW get_users AS
SELECT id, name, username, active, deleted, role_id as "roleId"
FROM users;

DROP VIEW IF EXISTS `PA_TP`.`get_managers` ;
CREATE VIEW get_managers AS
SELECT users.id as "id", 
		users.name as "name", 
        users.username as "username", 
        users.email as "email",
        users.active as "active", 
        users.deleted as "deleted", 
        users.role_id as "role_id"
FROM users
INNER JOIN managers
ON managers.user_id = users.id;

DROP VIEW IF EXISTS `PA_TP`.`get_reviewers` ;
CREATE VIEW get_reviewers AS
SELECT users.id as "id", 
		users.name "name", 
        users.username as "username", 
        users.email as "email",
        users.active as "active", 
        users.deleted as "deleted", 
        users.role_id as "role_id", 
        reviewers.nif as "nif", 
        reviewers.phone as "phone", 
        reviewers.address as "address", 
        reviewers.graduation as "graduation", 
        reviewers.specialization as "specialization"
FROM users
INNER JOIN reviewers
ON reviewers.user_id = users.id;

DROP VIEW IF EXISTS `PA_TP`.`get_authors` ;
CREATE VIEW get_authors AS
SELECT users.id as "id", 
		users.name as "name", 
        users.username as "username", 
        users.email as "email",
        users.active as "active", 
        users.deleted as "deleted", 
        users.role_id as "role_id", 
        authors.nif as "nif", 
        authors.phone as "phone", 
        authors.address as "address", 
        authors.activity_begin_date as "activity_begin_date", 
        authors.literary_style_id as "literary_style_id"
FROM users
INNER JOIN authors
ON authors.user_id = users.id;

DROP VIEW IF EXISTS `PA_TP`.`total_managers` ;
CREATE VIEW total_managers AS
SELECT COUNT(*) as "total_managers"
FROM managers;

DROP VIEW IF EXISTS `PA_TP`.`get_literary_styles` ;
CREATE VIEW get_literary_styles AS
SELECT *
FROM literarystyles;

DROP VIEW IF EXISTS `PA_TP`.`get_licenses` ;
CREATE VIEW get_licenses AS
SELECT *
FROM licenses;