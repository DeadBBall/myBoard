CREATE DATABASE `nts_board`;
USE `nts_board`;

CREATE TABLE `hashtags`(
	`hashtag_id` BIGINT AUTO_INCREMENT,
    `name` VARCHAR(10) NOT NULL UNIQUE,
    PRIMARY KEY(`hashtag_id`)
);

CREATE TABLE `articles`(
	`article_id` BIGINT AUTO_INCREMENT,
    `title` VARCHAR(30) NOT NULL,
    `writer` VARCHAR(10) NOT NULL,
    `content` TEXT NOT NULL,
    `created_at` DATE NOT NULL DEFAULT(current_date()),
    `edit_delete_password` VARCHAR(64) NOT NULL,
    `view_count` BIGINT NOT NULL DEFAULT(0),
    `is_deleted` BOOL NOT NULL DEFAULT(FALSE),
    PRIMARY KEY(`article_id`)
);

CREATE TABLE `articles_hashtags`(
	`article_hashtag_id` BIGINT AUTO_INCREMENT,
    `article_id` BIGINT NOT NULL,
    `hashtag_id` BIGINT NOT NULL,
    PRIMARY KEY(`article_hashtag_id`),
    FOREIGN KEY(`article_id`)
    REFERENCES `articles`(`article_id`),
    FOREIGN KEY(`hashtag_id`)
    REFERENCES `hashtags`(`hashtag_id`)
);

CREATE TABLE `likes`(
	`like_id` BIGINT AUTO_INCREMENT,
    `guest_id` VARCHAR(36) NOT NULL,
    `article_id` BIGINT NOT NULL,
    PRIMARY KEY(`like_id`),
    FOREIGN KEY(`article_id`)
    REFERENCES `articles`(`article_id`)
);

CREATE TABLE `comments`(
	`comment_id` BIGINT AUTO_INCREMENT,
    `article_id` BIGINT NOT NULL,
    `content` VARCHAR(100) NOT NULL,
    `writer` VARCHAR(10) NOT NULL,
    `created_at` DATE NOT NULL DEFAULT(current_date()),
	`delete_password` VARCHAR(64) NOT NULL,
    `is_deleted` BOOL NOT NULL DEFAULT(FALSE),
    PRIMARY KEY(`comment_id`),
    FOREIGN KEY(`article_id`)
    REFERENCES `articles`(`article_id`)
);