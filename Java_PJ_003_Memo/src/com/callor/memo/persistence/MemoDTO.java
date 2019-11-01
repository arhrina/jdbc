package com.callor.memo.persistence;

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
public class MemoDTO {
	private int m_seq;//	number	no
	private String m_auth;//	nvarchar2(30 char)	no
	private String m_date;//	varchar2(10 byte)	no
	private String m_subject;//	nvarchar2(50 char)	yes
	private String m_text;//	nvarchar2(255 char)	yes
	private String m_photo;//	nvarchar2(125 char)	yes
}
