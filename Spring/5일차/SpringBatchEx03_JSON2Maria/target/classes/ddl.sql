DROP SEQUENCE hanjatest_idx_seq;
CREATE SEQUENCE hanjatest_idx_seq;
/*
 * 	private String d;
	private String s;
	private String n;
	private String q;
	private String e1;
	private String e2;
	private String e3;
	private String e4;
	private String a;
 */
DROP TABLE hanjatest;
CREATE TABLE hanjatest(
	idx NUMBER PRIMARY KEY,
	d   varchar2(200) NOT NULL,
	s   varchar2(200) NOT NULL,
	n   varchar2(200) NOT NULL,
	q   varchar2(200) NOT NULL,
	e1   varchar2(200),
	e2   varchar2(200),
	e3   varchar2(200),
	e4   varchar2(200),
	a    varchar2(200) NOT NULL
);

COMMIT;

SELECT * FROM hanjatest;
SELECT count(*) FROM hanjatest;

