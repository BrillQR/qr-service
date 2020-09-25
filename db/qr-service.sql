#DB name will be "qr_service"
#username postgres 
#wherever you SET password SET it AS "admin" only

CREATE TABLE public.qr_matadata
(
    qr_id character varying(30) NOT NULL,
    created_by character varying(30) NOT NULL,
    date_created date,
    date_updated date,
    qr_location character varying(100) NOT NULL,
    qr_scan_count bigint,
    PRIMARY KEY (qr_id)
);

CREATE TABLE public.qr_data
(
    qr_id character varying(30) NOT NULL,
    first_name character(15) NOT NULL,
    last_name character(15),
    surname character(15),
    contact bigint,
    address character varying(100),
    profession character varying(20),
    experience character varying(10),
    education character varying(20),
    profile_photo character varying(50),
    email character varying(30),
    website character varying(30),
    field_projects character varying(150),
    business_established_date date,
    PRIMARY KEY (qr_id)
);

ALTER TABLE public.qr_data
    ADD COLUMN id bigint NOT NULL;
    
  ALTER TABLE public.qr_data
    ADD COLUMN user_code character varying(15) NOT NULL;