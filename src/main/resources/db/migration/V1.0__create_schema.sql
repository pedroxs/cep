CREATE TABLE cep
(
--     id BIGINT PRIMARY KEY NOT NULL,
    cep VARCHAR(8) PRIMARY KEY NOT NULL,
    uf VARCHAR(2),
    bairro VARCHAR(72),
    cidade VARCHAR(50),
    logradouro VARCHAR(70),
    tipo_logradouro VARCHAR(20),
    CONSTRAINT cep_un UNIQUE (cep)
);

-- CREATE UNIQUE INDEX cep_cep_uindex ON public.cep (cep);

CREATE TABLE address
(
    id BIGINT PRIMARY KEY NOT NULL,
    cep VARCHAR(8),
    numero INTEGER,
    complemento VARCHAR(70),
    preferencial BOOLEAN,
    CONSTRAINT cep_address_fk FOREIGN KEY (cep) REFERENCES cep (cep)
);

CREATE TABLE t_user
(
    email VARCHAR(70) PRIMARY KEY NOT NULL,
    password VARCHAR(60),
    name VARCHAR(70),
    CONSTRAINT user_email_un UNIQUE (email)
);

CREATE TABLE t_user_address
(
    user_email VARCHAR(70),
    address_id BIGINT,
    PRIMARY KEY (user_email, address_id),
    CONSTRAINT user_address_email_fk FOREIGN KEY (user_email) REFERENCES t_user (email),
    CONSTRAINT user_address_id_fk FOREIGN KEY (address_id) REFERENCES address (id)
);

CREATE SEQUENCE address_seq MAXVALUE 9223372036854775807 NO CYCLE;
