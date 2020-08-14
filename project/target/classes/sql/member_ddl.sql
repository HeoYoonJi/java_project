--?öå?õê ?Öå?ù¥Î∏?
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
('java1234', '¿⁄πŸ∏«', '»´±Êµø', 'm', 'abcd@abcd.com',
'010-1234-5678', '1995-05-01', '06128','º≠øÔ∆Ø∫∞Ω√ ∞≠≥≤±∏ ∞≠≥≤¥Î∑Œ102±Ê 14(ø™ªÔµø)*¿Âø¨∫Ùµ˘ 4√˛ 421»£', sysdate);

ALTER TABLE member_tbl MODIFY (member_nickname CONSTRAINT member_tbl_nickname_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_name CONSTRAINT member_tbl_name_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_email CONSTRAINT member_tbl_email_nn NOT NULL);
ALTER TABLE member_tbl MODIFY (member_phone CONSTRAINT member_tbl_phone_nn NOT NULL);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_id_pk PRIMARY KEY(member_id);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_email_un UNIQUE(member_email);
ALTER TABLE member_tbl ADD CONSTRAINT member_tbl_phone_un UNIQUE(member_phone);

COMMENT ON COLUMN member_tbl.member_id IS '?ïÑ?ù¥?îî';
COMMENT ON COLUMN member_tbl.member_nickname IS 'Î≥ÑÎ™Ö';
COMMENT ON COLUMN member_tbl.member_name IS '?ù¥Î¶?';
COMMENT ON COLUMN member_tbl.member_gender IS '?Ñ±Î≥?';
COMMENT ON COLUMN member_tbl.member_email IS 'Î©îÏùº';
COMMENT ON COLUMN member_tbl.member_phone IS '?ó∞?ùΩÏ≤?';
COMMENT ON COLUMN member_tbl.member_birth IS '?Éù?ÖÑ?õî?ùº';
COMMENT ON COLUMN member_tbl.member_zip IS '?ö∞?é∏Î≤àÌò∏';
COMMENT ON COLUMN member_tbl.member_address IS 'Ï£ºÏÜå';
COMMENT ON COLUMN member_tbl.member_joindate IS 'Í∞??ûÖ?ùº';