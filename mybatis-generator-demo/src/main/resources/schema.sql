create table t_problem (
    id bigint not null auto_increment,
    pbm_num varchar(255),
    pbm_desc varchar(512),
    pbm_owner varchar(255),
    pbm_creater varchar(255),
    create_time timestamp,
    update_time timestamp,
    primary key (id)
) ENGINE=InnoDB;