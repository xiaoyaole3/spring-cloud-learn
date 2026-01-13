CREATE DATABASE db2023 CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

drop table if exists `t_pay`;

create table `t_pay`(
    `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
    `pay_no` varchar(50) NOT NULL COMMENT '支付流水号',
    `order_no` varchar(50) NOT NULL COMMENT '订单流水号',
    `user_id` INT(10) DEFAULT '1' COMMENT '用户账号ID',
    `amount` DECIMAL(8,2) NOT NULL NULL DEFAULT '9.9' COMMENT '交易金额',
    `deleted` TINYINT(4) UNSIGNED NOT NULL DEFAULT '0' COMMENT '删除标志，默认0不删除，1删除',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARACTER SET=utf8mb4 COMMENT='支付交易表';

INSERT INTO `t_pay`(pay_no, order_no) VALUES ('pay17203699', '6544bafb424a');

