
CREATE TABLE usuarios(

    id bigint not null auto_increment,
    login VARCHAR(100) not null,
    clave VARCHAR(300) not null,

    PRIMARY KEY(id)

);