package com.biz.rent.persistence;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder

public class BookDTO {
	private String b_code;//	varchar2(6)	not null	primary key,
	private String b_name;//	nvarchar2(125)	not null	,
	private String b_auther;//	nvarchar2(125)	not null	,
	private String b_comp;//	nvarchar2(125)		,
	private int b_year;//	number	not null	,
	private int b_iprice;//	number		,
	private int b_rprice;//	number	not null	
}
