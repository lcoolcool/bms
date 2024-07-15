# 创建author 表
create table author
(
    id                      int                    not null auto_increment comment '主键自增id'
        primary key,
    name                    varchar(50)            not null comment '名字',
    email                   varchar(50)            not null comment '邮箱'
        unique,
    description             varchar(500)            not null comment '作者简介',
    add_time                datetime               not null comment '创建时间',
    update_time             datetime               not null comment '修改时间',
    index author_name (`name`)
) comment '作者表' engine = InnoDB;

# 创建book 表
create table book
(
    id                      int                    not null auto_increment comment '主键自增id'
        primary key,
    name                    varchar(50)            not null comment '书名',
    author_id               int                    not null comment '作者id',
    description             varchar(500)            not null comment '书简介',
    add_time                datetime               not null comment '创建时间',
    update_time             datetime               not null comment '修改时间',
    index book_name (`name`),
    index book_author_id (`author_id`),
    foreign key (author_id) references author (id)
) comment '书表' engine = InnoDB;