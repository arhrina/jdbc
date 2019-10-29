package com.biz.iolist.persistence;

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

public class IolistVO {
	private String io_date;//	varchar2(10)
	private String io_inout;//	nvarchar2(2)
	private String io_pcode;//	varchar2(5)
	private String tbl_iolist_pname;//	nvarchar2(50)
	private int tbl_iolist_iprice;//	number
	private int tbl_iolist_oprice;//	number
	private String tbl_iolist_pvat;//	varchar2(1)
	private String io_dcode;//	varchar2(5)
	private String tbl_iolist_dname;//	nvarchar2(50)
	private String tbl_iolist_dceo;//	nvarchar2(50)
	private String tbl_iolist_dtel;//	varchar2(20)
	private String tbl_iolist_daddr;//	nvarchar2(125)
	private int io_qty;//	number
	private int io_price;//	number
	private int io_total;//	number
	private int io_seq;//	number
}
