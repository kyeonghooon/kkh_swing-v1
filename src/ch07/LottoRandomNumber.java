package ch07;

import java.util.Arrays;
import java.util.Random;

// 클래스 --> 단일 책임 원칙을 설정하는 것이 좋다
public class LottoRandomNumber {

	final int LOTTO_NUMBER_COUNT = 6;

	// 6개의 랜덤 번호를 생성하는 메서드 필요하다.
	public int[] createNumber() {
		int[] lottoWinNum = new int[LOTTO_NUMBER_COUNT];
		Random random = new Random();

		for (int i = 0; i < lottoWinNum.length; i++) {
			lottoWinNum[i] = random.nextInt(45) + 1;
			// 중복 방지 코드
			for (int j = 0; j < i; j++) {
				if (lottoWinNum[i] == lottoWinNum[j]) {
					i--;
				}
			}
			// 중복 제거 완료
		}
		// 오름차순 정렬 코드
		Arrays.sort(lottoWinNum);
		return lottoWinNum;
	}

	// 코드 테스트
	public static void main(String[] args) {
		LottoRandomNumber randomNumber = new LottoRandomNumber();

		int[] resultArray = randomNumber.createNumber();
		for (int i = 0; i < resultArray.length; i++) {
			System.out.print(resultArray[i] + " ");
		}
	}
}
