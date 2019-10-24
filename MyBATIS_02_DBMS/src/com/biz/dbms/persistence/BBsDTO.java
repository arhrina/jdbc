package com.biz.dbms.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter // mybatis가 사용
@Setter // mybatis가 사용
@AllArgsConstructor // mybatis가 사용
@NoArgsConstructor
@ToString
@Builder
public class BBsDTO {
	private long bs_id;//	number
	private String bs_date;//	varchar2(20 byte)
	private String bs_time;//	varchar2(10 byte)
	private String bs_writer;//	nvarchar2(20 char)
	private String bs_subject;//	nvarchar2(125 char)
	private String bs_text;//	nvarchar2(1000 char)
	private int bs_count;//	number
	/*
	 * DTO, VO를 생성할 때 필드 이름은 TABLE의 컬럼과 같게 설정하면 MyBATIS 자동 get, set이 제대로 작동
	 */
}
