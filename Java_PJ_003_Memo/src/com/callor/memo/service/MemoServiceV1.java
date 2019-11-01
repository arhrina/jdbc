package com.callor.memo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.callor.memo.config.DBConnection;
import com.callor.memo.dao.MemoDao;
import com.callor.memo.persistence.MemoDTO;

public class MemoServiceV1 {
	protected MemoDao mDao;
	protected BufferedReader s;

	public MemoServiceV1() {
		mDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(MemoDao.class);
		s = new BufferedReader(new InputStreamReader(System.in));
	}

	public void menu() throws IOException {
		while (true) {
			System.out.println("메모저장 시스템 v1");
			System.out.println("1.입력\t2.수정\t3.검색\t4.삭제\t0.종료");
			System.out.print("작업선택 >> ");
			String str;
			str = s.readLine();
			int intMenu;
			try {
				intMenu = Integer.valueOf(str);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력하세요");
				continue;
			}
			if (intMenu == 0) {
				break;
			}
			else if(intMenu == 1) {
				insertMemo();
				continue;
			}
			else if(intMenu == 2) {
				updateMemo();
				continue;
			}
			else if(intMenu == 3) {
				System.out.println("1.번호로\t2.글쓴이로\t3.제목으로");
				System.out.print("선택 >> ");
				str = s.readLine();
				int intStr;
				try {
					intStr = Integer.valueOf(str);
					if(intStr == 1) {
						readMemoBySEQ();
						continue;
					}
					else if(intStr == 2) {
						readMemoByWri();
						continue;
					}
					else if(intStr == 3) {
						readMemoBySub();
						continue;
					}
					else {
						System.out.println("잘못입력하였습니다");
						System.out.println("상위메뉴로 되돌아갑니다");
						continue;
					}
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("숫자를 입력하지 않아 상위메뉴로 되돌아갑니다");
					continue;
				}
			}
			else if(intMenu == 4) {
				deleteMemo();
				continue;
			}
		}
		System.out.println("시스템을 종료합니다");
		return;
	}

	public void insertMemo() throws IOException {
		MemoDTO mDTO = new MemoDTO();
		String str;
		while (true) {
			System.out.print("작성자(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			if (str.trim().isEmpty()) {
				System.out.println("작성자는 반드시 입력");
				continue;
			}
			if (str.trim().length() > 30) {
				System.out.println("이름은 30자 이하로 입력");
				continue;
			}
			mDTO.setM_auth(str);
			break;
		} // 글쓴이 입력 while end
		if (str.equals("-Q"))
			return;

		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sf.format(today);
		mDTO.setM_date(strToday);
		// 오늘 날짜 만들고 셋

		while (true) {
			str = null;
			System.out.print("제목(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			if (str.length() > 50) {
				System.out.println("제목은 50자 이하로 입력");
				continue;
			}
			if (str.trim().length() < 1) {
				str = "제목없음";
			}
			mDTO.setM_subject(str); // 제목 세팅
			break;
		}
		if (str.equals("-Q"))
			return;

		while (true) {
			str = null;
			System.out.print("내용(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			if (str.length() > 255) {
				System.out.println("내용은 255자 이하로 입력");
				continue;
			}
			mDTO.setM_text(str); // 내용 세팅
			break;
		}
		if (str.equals("-Q"))
			return;
		/*
		 * while(true) { str = null; System.out.print("사진업로드. 경로 입력(-Q:quit) >> "); str
		 * = s.readLine(); if(str.equals("-Q")) break; mDTO.setM_photo(str); break; }
		 * if(str.equals("-Q")) return;
		 */
		System.out.println(mDTO.getM_auth());
		System.out.println(mDTO.getM_date());
		System.out.println(mDTO.getM_subject());
		System.out.println(mDTO.getM_text());
		System.out.println(mDTO.getM_photo());
		int ret = mDao.insert(mDTO);
		if (ret > 0)
			System.out.println("메모입력 완료");
		else
			System.out.println("메모입력 실패");
	} // end insertMemo

	public void readMemoBySEQ() throws IOException {
		List<MemoDTO> memoList = mDao.viewAllSEQ();
		System.out.println("번호리스트");
		for(MemoDTO d : memoList)
			System.out.println(d.getM_seq());
		String str;
		int intSeq = 0;
		while (true) {
			System.out.print("확인할 메모의 SEQ 입력(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			try {
				intSeq = Integer.valueOf(str);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력하세요");
				continue;
			}
		} // while end
		if (str.equals("-Q"))
			return;
		memoList.clear();
		memoList = mDao.searchBySEQ(intSeq);
		if(memoList.size() < 1) {
			System.out.println("데이터가 없습니다");
			return;
		}
		showList(memoList);
	}

	public void readMemoBySub() throws IOException {
		List<MemoDTO> memoList = mDao.viewAllSub();
		System.out.println("제목리스트");
		for(MemoDTO d : memoList)
			System.out.println(d.getM_subject());
		System.out.print("확인할 메모의 제목(-Q:quit) >> ");
		String str = s.readLine();
		if (str.equals("-Q"))
			return;
		memoList.clear();
		memoList = mDao.searchBySub(str);
		if(memoList.size() < 1) {
			System.out.println("데이터가 없습니다");
			return;
		}
		showList(memoList);
	}

	public void readMemoByWri() throws IOException {
		List<MemoDTO> memoList = mDao.viewAllWri();
		System.out.println("글쓴이리스트");
		for(MemoDTO d : memoList)
			System.out.println(d.getM_auth());
		System.out.print("확인할 메모의 글쓴이(-Q:quit) >> ");
		String str = s.readLine();
		if (str.equals("-Q"))
			return;
		memoList.clear();
		memoList = mDao.searchByWri(str);
		if(memoList.size() < 1) {
			System.out.println("데이터가 없습니다");
			return;
		}
		showList(memoList);
	}

	public void updateMemo() throws IOException {
		List<MemoDTO> memoList = mDao.viewAllList();
		showList(memoList);
		String str;
		int intSeq = 0;
		while (true) {
			System.out.print("수정할 메모의 번호(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			try {
				intSeq = Integer.valueOf(str);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력");
				continue;
			}
		} // end SEQ while
		if (str.equals("-Q"))
			return;
		MemoDTO mDTO = mDao.searchByNum(intSeq);
		if (mDTO == null) {
			System.out.println("번호를 다시 확인하고 입력하세요");
			return;
		}
		str = null;
		System.out.print("수정할 작성자이름(-Q:quit) >> ");
		str = s.readLine();
		if (str.equals("-Q"))
			return;
		if (!str.trim().isEmpty())
			mDTO.setM_auth(str);
		Date today = new Date(System.currentTimeMillis());
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = sf.format(today);
		mDTO.setM_date(strToday);
		// 오늘 날짜 만들고 셋
		str = null;
		System.out.println("수정할 제목(-Q:quit) >> ");
		str = s.readLine();
		if (str.equals("-Q"))
			return;
		if (!str.trim().isEmpty())
			mDTO.setM_subject(str);
		// 제목수정
		str = null;
		System.out.println("수정할 내용(-Q:quit) >> ");
		str = s.readLine();
		if (str.equals("-Q"))
			return;
		if (!str.trim().isEmpty())
			mDTO.setM_text(str);
		int ret = mDao.update(mDTO);
		if (ret > 0)
			System.out.println("수정 완료");
		else
			System.out.println("수정 실패");
	}

	public void deleteMemo() throws IOException {
		List<MemoDTO> memoList = mDao.viewAllList();
		showList(memoList);
		String str;
		int intSeq = 0;
		while (true) {
			System.out.print("삭제할 메모의 번호(-Q:quit) >> ");
			str = s.readLine();
			if (str.equals("-Q"))
				break;
			try {
				intSeq = Integer.valueOf(str);
				break;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("숫자만 입력");
				continue;
			}
		} // end SEQ while
		if (str.equals("-Q"))
			return;
		memoList.clear();
		memoList = mDao.searchBySEQ(intSeq);
		if (memoList.size() < 1) {
			System.out.println("번호를 잘 확인하고 다시 시도해주세요");
			return;
		}
		System.out.printf("정말로 %d번의 메모를 삭제합니까?(Enter:YES)", intSeq);
		str = s.readLine();
		int ret;
		if (str.trim().isEmpty())
			ret = mDao.delete(intSeq);
		else
			return;
		if (ret > 0)
			System.out.println("삭제 완료");
		else
			System.out.println("삭제 실패");
	}

	protected void showList(List<MemoDTO> mList) {
		for (MemoDTO d : mList)
			System.out.println(d.toString());
	}
}
