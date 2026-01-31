-- 分布式数据库 订单库
CREATE DATABASE seata_order CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `seata_order`.`t_order`
(
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `user_id` BIGINT(11) DEFAULT NULL,
    `product_id` BIGINT(11) DEFAULT NULL,
    `count` INT(11) DEFAULT NULL,
    `money` DECIMAL(11,0) DEFAULT NULL,
    `status` INT(1) DEFAULT NULL COMMENT '订单状态: 0创建中；1已完结'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4;

-- seata 在 client 端的 undo_log
CREATE TABLE IF NOT EXISTS `seata_order`.`undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
ALTER TABLE `seata_order`.`undo_log` ADD INDEX `ix_log_created` (`log_created`);


-- 分布式数据库 库存库
CREATE DATABASE seata_storage CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `seata_storage`.`t_storage`
(
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `product_id` BIGINT(11) DEFAULT NULL,
    `total` INT(11) DEFAULT NULL,
    `used` INT(11) DEFAULT NULL,
    `residue` INT(11) DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4;

INSERT INTO `seata_storage`.`t_storage` values (1,1,100,0,100);

-- seata 在 client 端的 undo_log
CREATE TABLE IF NOT EXISTS `seata_storage`.`undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
ALTER TABLE `seata_storage`.`undo_log` ADD INDEX `ix_log_created` (`log_created`);

-- 分布式数据库 账户库
CREATE DATABASE seata_account CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `seata_account`.`t_account`
(
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY ,
    `user_id` BIGINT(11) DEFAULT NULL,
    `total` DECIMAL(11,0) DEFAULT NULL,
    `used` DECIMAL(11,0) DEFAULT NULL,
    `residue` DECIMAL(11,0) DEFAULT NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4;

INSERT INTO `seata_account`.`t_account` values (1,1, 1000,0, 1000);

-- seata 在 client 端的 undo_log
CREATE TABLE IF NOT EXISTS `seata_account`.`undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
ALTER TABLE `seata_account`.`undo_log` ADD INDEX `ix_log_created` (`log_created`);