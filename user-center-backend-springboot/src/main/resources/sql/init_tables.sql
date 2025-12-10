DROP TABLE IF EXISTS `user`;

CREATE TABLE `user`
(
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT 'Primary Key ID',
    user_name VARCHAR(256) NULL COMMENT 'Users Name',
    user_account VARCHAR(256) NOT NULL COMMENT 'Users account number',
    avatar_url VARCHAR(1024) NULL COMMENT 'Users profile picture',
    gender tinyint NULL COMMENT 'Gender',
    user_password VARCHAR(512) NOT NULL COMMENT 'Login password',
    phone VARCHAR(128) NULL COMMENT 'Users phone number',
    email VARCHAR(50) NULL COMMENT 'Email',
    user_status INT NOT NULL DEFAULT 0 COMMENT 'Status 0 - normal status',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT 'Current time',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'Time to update the user',
    is_delete TINYINT DEFAULT 0 NOT NULL COMMENT 'If it is required to delete',
    role INT DEFAULT 0 NOT NULL COMMENT  '0 normal user, 1 admin',
    PRIMARY KEY (id),
    CONSTRAINT `uk_user_account` UNIQUE (`user_account`),
    CONSTRAINT `uk_phone` UNIQUE (`phone`),
    CONSTRAINT `uk_email` UNIQUE (`email`)
) COMMENT 'Users info table';
