package map;

import java.util.Random;
import java.util.Scanner;

public class Mission {
	public boolean performMission() {
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		int correctNumber = rand.nextInt(10) + 1;
		System.out.println("레벨업 미션: 1부터 10 사이의 숫자 중 하나를 맞춰보세요!");
		System.out.print("숫자를 입력하세요: ");
		int userGuess = in.nextInt();
		return userGuess == correctNumber;
	}
}
