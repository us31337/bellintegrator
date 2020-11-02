CREATE TABLE IF NOT EXISTS country
(
    code    INTEGER NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY,
    version INTEGER NOT NULL COMMENT 'Служебное поле hibernate',
    name    VARCHAR(100) COMMENT 'Имя'
);
COMMENT ON TABLE country IS 'Страна гражданства';

CREATE UNIQUE INDEX UX_country_code ON country (code);

CREATE TABLE IF NOT EXISTS doc_type
(
    version INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    code    VARCHAR(2)   NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY,
    name    VARCHAR(150) NOT NULL COMMENT 'Имя'
);

CREATE UNIQUE INDEX UX_doc_type_code ON doc_type (code);

CREATE TABLE IF NOT EXISTS document
(
    id         BIGINT      NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version    INTEGER     NOT NULL COMMENT 'Служебное поле hibernate',
    doc_date   DATE        NOT NULL COMMENT 'Дата выдачи',
    doc_number VARCHAR(10) NOT NULL COMMENT 'Номер документа',
    doc_type   VARCHAR(2)  NOT NULL COMMENT 'Идентификатор типа документа'
--     user_id    BIGINT      NOT NULL COMMENT 'Идентификатор пользователя' Использовать в случае двунаправленной связи
);
COMMENT ON TABLE document IS 'На основании какого документа работает';

CREATE TABLE IF NOT EXISTS organisation
(
    id        BIGINT       NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    address   VARCHAR(225) NOT NULL COMMENT 'Адрес',
    full_name VARCHAR(225) NOT NULL COMMENT 'Полное наименование',
    inn       VARCHAR(15)  NOT NULL COMMENT 'ИНН',
    kpp       VARCHAR(9)   NOT NULL COMMENT 'КПП',
    is_active boolean DEFAULT false COMMENT 'Активен',
    name      VARCHAR(125) NOT NULL COMMENT 'Сокращенное наименование',
    phone     VARCHAR(25) COMMENT 'Телефон для связи'
);
COMMENT ON TABLE organisation IS 'Фирма';

CREATE UNIQUE INDEX UX_organisation_id ON organisation (id);

CREATE INDEX IX_organisation_is_active ON organisation (is_active);

CREATE INDEX IX_organisation_name ON organisation (name);

CREATE TABLE IF NOT EXISTS office
(
    id        BIGINT       NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    address   VARCHAR(255) NOT NULL COMMENT 'Адрес',
    is_active boolean DEFAULT false COMMENT 'Активен',
    name      VARCHAR(255) NOT NULL COMMENT 'Имя',
    phone     VARCHAR(25) COMMENT 'Номер телефона',
    org_id    BIGINT       NOT NULL COMMENT 'Идентификатор фирмы'
);
COMMENT ON TABLE office IS 'Отдел, подразделение';

CREATE INDEX IX_office_is_active ON office (is_active);

CREATE INDEX IX_office_name ON office (name);

CREATE INDEX IX_office_phone ON office (phone);

CREATE UNIQUE INDEX UX_office_id ON office (id);

CREATE TABLE IF NOT EXISTS user
(
    id               BIGINT       NOT NULL COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER      NOT NULL COMMENT 'Служебное поле hibernate',
    first_name       VARCHAR(100) NOT NULL COMMENT 'Имя',
    is_identified    boolean DEFAULT true COMMENT 'Идентифицирован',
    middle_name      VARCHAR(100) COMMENT 'Отчество',
    phone            VARCHAR(25) COMMENT 'Телефон для свзяи',
    position         VARCHAR(100) NOT NULL COMMENT 'Должность',
    second_name      VARCHAR(100) COMMENT 'Фамилия',
    doc_id           BIGINT       NOT NULL COMMENT 'Идентиифкатор документа',
    citizenship_code INTEGER COMMENT 'Код гражданства',
    office_id        BIGINT       NOT NULL COMMENT 'Индентификатор подразделения'
);
COMMENT ON TABLE user IS 'Пользователь, сотрудник';

CREATE INDEX IX_user_index ON user (first_name);

CREATE INDEX IX_user_citizenship_code ON user (citizenship_code);

CREATE INDEX IX_user_middle_name ON user (middle_name);

CREATE INDEX IX_user_position ON user (position);

CREATE INDEX IX_user_second_name ON user (second_name);

CREATE UNIQUE INDEX UX_user_doc_id ON user (doc_id);

CREATE UNIQUE INDEX UX_user_id ON user (id);

CREATE UNIQUE INDEX UX_user_office_id ON user (office_id);

ALTER TABLE country
    ADD CONSTRAINT UK_country_code_name UNIQUE (code, name);

ALTER TABLE doc_type
    ADD CONSTRAINT UK_document_code_name UNIQUE (code, name);

ALTER TABLE organisation
    ADD CONSTRAINT UK_organisation_inn UNIQUE (inn);

ALTER TABLE office
    ADD FOREIGN KEY (org_id) REFERENCES organisation (id);

ALTER TABLE user
    ADD FOREIGN KEY (citizenship_code) REFERENCES country (code);

ALTER TABLE user
    ADD FOREIGN KEY (doc_id) REFERENCES document (id);

ALTER TABLE user
    ADD FOREIGN KEY (office_id) REFERENCES office (id);

ALTER TABLE user ADD CONSTRAINT UK_document_id_user UNIQUE (id, doc_id);
