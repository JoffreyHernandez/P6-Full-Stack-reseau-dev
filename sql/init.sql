CREATE TABLE `POSTS`
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `title`      varchar(60),
    `content`    varchar(2000),
    `user_id`    int,
    `topic_id`   int,
    `created_at` timestamp,
    `updated_at` timestamp
);

CREATE TABLE `TOPICS`
(
    `id`          int PRIMARY KEY AUTO_INCREMENT,
    `name`        varchar(50) UNIQUE,
    `description` varchar(2000),
    `created_at`  timestamp,
    `updated_at`  timestamp
);

CREATE TABLE `COMMENTS`
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `comment`    varchar(500),
    `user_id`    int,
    `post_id`    int,
    `created_at` timestamp,
    `updated_at` timestamp
);

CREATE TABLE `USERS`
(
    `id`         int PRIMARY KEY AUTO_INCREMENT,
    `username`   varchar(40),
    `email`      varchar(40) UNIQUE,
    `password`   varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp
);

CREATE TABLE `SUBSCRIPTION`
(
    `user_id`  int,
    `topic_id` int
);

ALTER TABLE `POSTS`
    ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `POSTS`
    ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);

ALTER TABLE `COMMENTS`
    ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `COMMENTS`
    ADD FOREIGN KEY (`post_id`) REFERENCES `POSTS` (`id`);

ALTER TABLE `SUBSCRIPTION`
    ADD FOREIGN KEY (`user_id`) REFERENCES `USERS` (`id`);

ALTER TABLE `SUBSCRIPTION`
    ADD FOREIGN KEY (`topic_id`) REFERENCES `TOPICS` (`id`);
