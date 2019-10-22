package com.biz.addr.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddrDTO {
	private int id;//	NUMBER
	private String name;//	NVARCHAR2(50 CHAR)
	private String tel;//	VARCHAR2(20 BYTE)
	private String addr;//	NVARCHAR2(125 CHAR)
	private String relat;//	NVARCHAR2(10 CHAR)
}
