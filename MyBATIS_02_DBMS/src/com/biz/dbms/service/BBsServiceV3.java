package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV3 {
	SqlSession sqlSession;
	Scanner s;

	public BBsServiceV3() {
		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		s = new Scanner(System.in);
	}

	public void writeBBS() {
		// TODO Auto-generated method stub
		// 작성자, 제목, 내용을 입력하지 않으면 메시지를 보여주고 다시 입력을 받도록
		// 왜? => bs_writer, bs_subject, bs_text가 DBMS에서 not null이므로
		while (true) {
			System.out.print("작성자(-Q : 작성중단) >> ");
			String strName = s.nextLine();
			if (strName.equals("-Q"))
				break;
			if (strName.trim().length() < 1) { // 빈칸을 제외한 길이가 1 미만, 즉, null
				System.out.println("작성자는 반드시 입력해야합니다");
				continue;
			}
			System.out.print("제목(-Q : 작성중단) >> ");
			String strSubject = s.nextLine();
			if (strSubject.equals("-Q"))
				break;
			if (strSubject.trim().length() < 1) { // 빈칸을 제외한 길이가 1 미만, 즉, null
				System.out.println("제목은 반드시 입력해야합니다");
				continue;
			}
			System.out.print("내용 >> ");
			String strText = s.nextLine();
			if (strText.equals("-Q"))
				break;
			if (strText.trim().length() < 1) { // 빈칸을 제외한 길이가 1 미만, 즉, null
				System.out.println("내용은 반드시 입력해야합니다");
				continue;
			}
			/*
			 * 작성일자와 작성시각은 컴퓨터 시간을 참조하여 자동생성
			 */
			// java 1.7 이하
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 2019-10-24 형으로 변환
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS"); // 14:17:22 형으로 변환
			String curDate = df.format(date);
			String curTime = tf.format(date);
			// 입력받은 데이터와 날짜, 시각을 DTO에 담기
			BBsDTO bDTO = BBsDTO.builder().bs_date(curDate).bs_time(curTime).bs_writer(strName).bs_subject(strSubject)
					.bs_text(strText).build();
			BBsDao bDao = sqlSession.getMapper(BBsDao.class);
			int ret = bDao.insert(bDTO);
			if (ret > 0)
				System.out.println("게시판에 글 작성 성공");
			else
				System.out.println("게시판에 글 작성 실패");
			break;
		}
	}

	public void updateBBS() {
		/*
		 * 수정할 ID를 입력받고 내용을 보여주고, 각 항목을 입력받고 Enter를 입력하면 원래 데이터를 유지,
		 * 새로운 값을 입력하면 새로운 값으로 교체
		 */
		System.out.print("수정할 ID(-Q : 중단) >> ");
		String strId = s.nextLine();
		BBsDao bDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bDTO;
		try {
			long longId = Long.valueOf(strId);
			bDTO = bDao.findById(longId);
			System.out.print("변경할 제목을 입력(-Q : 중단) >> ");
			String strSubject = s.nextLine();
			if(strSubject.equals("-Q")) return;
			else if(strSubject.trim().length() > 0) {
				bDTO.setBs_subject(strSubject);
			}
			System.out.print("변경할 작성자를 입력(-Q : 중단) >> ");
			String strWriter = s.nextLine();
			if(strWriter.equals("-Q")) return;
			else if(strWriter.trim().length() > 0) {
				bDTO.setBs_writer(strWriter);
			}
			System.out.print("변경할 내용을 입력(-Q : 중단) >> ");
			String strText = s.nextLine();
			if(strText.equals("-Q")) return;
			else if(strText.trim().length() > 0) {
				bDTO.setBs_text(strText);
			}
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd"); // 2019-10-24 형으로 변환
			SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS"); // 14:17:22 형으로 변환
			String curDate = df.format(date);
			String curTime = tf.format(date);
			bDTO.setBs_date(curDate);
			bDTO.setBs_time(curTime);
			int ret = bDao.update(bDTO);
			if(ret > 0)
				System.out.println("업데이트 성공");
			else
				System.out.println("업데이트 실패");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void bbsMenu() {
		while (true) {
			System.out.println("내용보기(SQ입력)     W.작성     U.수정     D.삭제     Q.종료");
			String strMenu = s.nextLine();
			// int intMenu = 0;
			/* try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("0 또는 1~4까지 선택하세요");
				continue;
			}
			*/
			if(strMenu.equalsIgnoreCase("Q")) return;
			else if(strMenu.equalsIgnoreCase("W")) {
				writeBBS();
				viewBBsList();
			}
			else if(strMenu.equalsIgnoreCase("U")) {
				updateBBS();
				viewBBsList();
			}
			else if(strMenu.equalsIgnoreCase("D")) {
				deleteBBS();
				viewBBsList();
			}
			else {
				try {
					long longSEQ = Long.valueOf(strMenu);
					viewText(longSEQ);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} // end if문
		} // end while
	}
	
	public void viewText(long id) {
		BBsDao bDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bDTO = bDao.findById(id);
		if(bDTO == null) {
			System.out.println("내용이 없습니다");
		}
		else {
			System.out.println("제목 : " + bDTO.getBs_subject());
			System.out.println("작성자 : " + bDTO.getBs_writer());
			System.out.println("작성일 : " + bDTO.getBs_date());
			System.out.println("작성시각 : " + bDTO.getBs_time());
			System.out.println(bDTO.getBs_text());
		}
	}
	
	public void deleteBBS() {
		// 삭제할 게시판 ID를 입력받고
		// 해당 ID의 내용을 보여주고 정말로 삭제할지 물어본 후 진행
		System.out.print("삭제를 원하는 게시판 ID(-Q : quit) >> ");
		String strId = s.nextLine();
		try {
			long longId = Long.valueOf(strId);
			viewText(longId);
			System.out.println("--------------------------------");
			System.out.print("정말로 삭제하길 원하십니까?(YES/no");
			String yesNo = s.nextLine();
			if(yesNo.equals("YES")) {
				BBsDao bDao = sqlSession.getMapper(BBsDao.class);
				bDao.delete(longId);
			}				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void viewBBsList() {
		// TODO Auto-generated method stub
		BBsDao bDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bDao.selectAll();
		System.out.println("슈퍼 BBS v1");
		System.out.println("SQ\t작성일\t시각\t작성자\t제목");
		for (BBsDTO a : bbsList) {
			System.out.print(a.getBs_id() + "\t");
			System.out.print(a.getBs_date() + "\t");
			System.out.print(a.getBs_time() + "\t");
			System.out.print(a.getBs_writer() + "\t");
			System.out.print(a.getBs_subject() + "\n");
		}
	}
}
