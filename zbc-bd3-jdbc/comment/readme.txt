spring-jdbc 3.1.1.RELEASE 추가


[sp16-oracle-01]
[오라클 DB 설정]
1. oracle 11g express db 설치
2. Oracle SQL Developer 설치
3. SQL 설정(사용자생성, 테이블생성)
4. 오라클용 jdbc 파일 복사 "C:\oraclexe\app\oracle\product\11.2.0\server\jdbc\lib\ojdbc6_g.jar" 을 
  "C:\Program Files\Java\jre1.8.0_172\lib\ext" 디렉토리로 복사
5. 서버설정수정


[DOS & SQL명령]
MSDOS> sqlplus system/oracle
SQL> create user scott identified by tiger; 
SQL> grant connect, resource to scott;
SQL> exit
MSDOS>> sqlplus scott/tiger
SQL> CREATE SEQUENCE mvc_board_seq;

DROP TABLE mvc_board;
CREATE table mvc_board(
    bId NUMBER(4) PRIMARY KEY,
    bName VARCHAR2(20),
    bTitle VARCHAR2(100),
    bContent VARCHAR2(300),
    bDate DATE DEFAULT SYSDATE,
    bHit NUMBER(4) DEFAULT 0,
    bGroup NUMBER(4),
    bStep NUMBER(4),
    bIndent NUMBER(4)
    );
    
DROP SEQUENCE mvc_board_seq;
CREATE SEQUENCE mvc_board_seq;

INSERT INTO mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) 
    values (mvc_board_seq.nextval, 'abcd', 'is title', 'is content', 0, mvc_board_seq.currval, 0, 0);
INSERT INTO mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) 
    values (mvc_board_seq.nextval, 'joy', 'Happy man', '그는 항상 행복합니다.', 0, mvc_board_seq.currval, 0, 0);


[server 설정]
1. Tomcat 7.0 의 context.xml 설정

<Resource auth="Container"
	driverClassName = "oracle.jdbc.driver.OracleDriver"
	maxActive="50"
	maxWait="1000"
	name="jdbc/Oracle11g"
	username="scott"
	password="tiger"
	type="javax.sql.DataSource"
	url="jdbc:oracle:thin:@localhost:1521:xe"
/>
	
	
  <beans:bean name="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
    <beans:property name="driverClassName" value="oracle.jdbc.driver.OracleDriver" />
    <beans:property name="url" value="jdbc:oracle:thin:@localhost:1521:xe" />
    <beans:property name="username" value="scott" />
    <beans:property name="password" value="tiger" />
  </beans:bean>

  <beans:bean name="template" class="org.springframework.jdbc.core.JdbcTemplate">
    <beans:property name="dataSource" ref="dataSource"/>
  </beans:bean> 	