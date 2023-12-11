

CREATE SEQUENCE IF NOT EXISTS "pracloom-1001".organization_tb_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

ALTER SEQUENCE "pracloom-1001".organization_tb_id_seq
    OWNER TO postgres;

CREATE TABLE IF NOT EXISTS "pracloom-1001".organization_tb
(
    id integer NOT NULL DEFAULT nextval('"pracloom-1001".organization_tb_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    name character varying(255) COLLATE pg_catalog."default",
    tenant_id character varying(255) COLLATE pg_catalog."default",
    address character varying(255) COLLATE pg_catalog."default",
    creation_date timestamp(6) without time zone,
    logo_secret character varying(255) COLLATE pg_catalog."default",
    logo_url character varying(255) COLLATE pg_catalog."default",
    "number" character varying(255) COLLATE pg_catalog."default",
    paid boolean,

    initiated_by_id integer,
    CONSTRAINT organization_tb_pkey PRIMARY KEY (id),
    CONSTRAINT fkmj6ejv76vjlbw6wdb73boakai FOREIGN KEY (initiated_by_id)
    REFERENCES "pracloom-1001".customer (id) MATCH SIMPLE
                               ON UPDATE NO ACTION
                               ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS "pracloom-1001".organization_tb
    OWNER to postgres;