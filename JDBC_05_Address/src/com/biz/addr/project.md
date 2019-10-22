# 주소록 프로젝트

* 오라클의 tbl_addr 테이블을 참조하여 주소록 프로젝트 수행
* base package : com.biz.addr
* DBConnection
* ~.persistence : AddrDTO
* ~.dao : AddrDao
* selectAll(), findById(long id), findByName(String name), findByTel(String tel), findByRelat(String relat)
* ~.service : AddrServiceV1
* main()에서 호출되는 메소드들 생성


* 오라클 DBMS에 접속하기 위해 ojdbc를 설정
* lombok 설정
* test용 ~.exec 패키지에 AddrEx_** 생성