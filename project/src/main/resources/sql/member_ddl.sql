--?��?�� ?��?���?
CREATE TABLE member_tbl (
  member_id VARCHAR2(20),
  member_nickname VARCHAR2(50),
  member_name VARCHAR2(50),
  member_gender char(1),
  member_email VARCHAR2(50),
  member_phone VARCHAR2(13),
  member_birth date,
  member_zip VARCHAR2(5),
  member_address VARCHAR2(300),
  member_joindate DATE default sysdate
);

INSERT INTO member_tbl VALUES
('java1234', '�ڹٸ�', 'ȫ�浿', 'm', 'abcd@abcd.com',
'010-1234-5678', '1995-05-01', '06128','����Ư���� ������ �������102�� 14(���ﵿ)*�忬���� 4�� 421ȣ', sysdate);

ALTER TABLE member_tbl MODIFY (member_nickname CONSTRAINT member_tbl_nickname_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_name CONSTRAINT member_tbl_name_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_email CONSTRAINT member_tbl_email_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_phone CONSTRAINT member_tbl_phone_nn NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_id_pk PRIMARY KEY(member_id);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_email_un UNIQUE(member_email);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_phone_un UNIQUE(member_phone);

COMMENT ON COLUMN member_tbl.member_id IS '?��?��?��';
COMMENT ON COLUMN member_tbl.member_nickname IS '별명';
COMMENT ON COLUMN member_tbl.member_name IS '?���?';
COMMENT ON COLUMN member_tbl.member_gender IS '?���?';
COMMENT ON COLUMN member_tbl.member_email IS '메일';
COMMENT ON COLUMN member_tbl.member_phone IS '?��?���?';
COMMENT ON COLUMN member_tbl.member_birth IS '?��?��?��?��';
COMMENT ON COLUMN member_tbl.member_zip IS '?��?��번호';
COMMENT ON COLUMN member_tbl.member_address IS '주소';
COMMENT ON COLUMN member_tbl.member_joindate IS '�??��?��';