# 자바 MyBatis 매입매출 프로젝트

## 2019-10-25

* 오라클의 iolist2 Schema에 설정된 Table을 사용하여 매입매출 프로젝트 수행

* tbl_iolist
* tbl_product
* tbl_dept
* view_iolist

### com.biz.iolist.persistence
* IolistDTO
* ProductDTO
* DeptDTO
* IolistVO(view)

### com.biz.iolist.dao
* IolistDao
* ProDao
* DeptDao

### com.biz.iolist.config
* MyBatis-config.xml 생성, MyBatis 초기설정
* 맵퍼들 생성 및 세팅
* iolist-mapper.xml
* pro-mapper.xml
* dpt-mapper