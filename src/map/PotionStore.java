package map;

import java.util.Scanner;

import character.Hero;

public class PotionStore {
	public void enterPotionStore(Hero hero, Scanner in) {
		System.out.println("포션 상점에 입장하였습니다.");
		System.out.println(
				"1. 힘 증강 포션 (30원)\n2. 방어력 증강 포션 (30원)\n3. 경험치 증강 포션 (100원)\n4. HP 증강 포션 (10원)\n5. MP 증강 포션 (10원)");
		System.out.print("원하시는 물건을 입력하세요 : ");
		int potionNum = in.nextInt();
		int oriMoney = hero.getMoney();
		hero.reduceMoney(buyPotion(hero, potionNum, oriMoney));
		if (hero.getMoney() < oriMoney) {
			System.out.println("포션 상점에서 물건을 구매 시도하는 중입니다.\n구입 완료되었습니다.");
			System.out.printf("%d원 남았습니다.\n", hero.getMoney());
		} else {
			System.out.println("돈이 부족하여 포션을 구매할 수 없습니다.");
		}
	}

	private int buyPotion(Hero hero, int num, int money) {
		if (num == 1 && money >= 30) {
			hero.increasePower(3);
			return 30;
		} else if (num == 2 && money >= 30) {
			hero.increaseDefense(3);
			return 30;
		} else if (num == 3 && money >= 100) {
			hero.increaseExperience(50);
			return 100;
		} else if (num == 4 && money >= 10) {
			hero.increaseHp(50);
			return 10;
		} else if (num == 5 && money >= 10) {
			hero.increaseMp(50);
			return 10;
		}
		return 0;
	}
}
