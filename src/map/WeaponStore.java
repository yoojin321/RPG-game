package map;

import java.util.Scanner;

import character.Hero;

public class WeaponStore {
	public void enterWeaponStore(Hero hero, Scanner in) {
		System.out.println("무기 상점에 입장하였습니다.");
		if (hero instanceof Hero.Warrior) {
			System.out.println("1. 단검 (20원)\n2. 장검 (50원)\n3. 도끼 (100원)");
		} else if (hero instanceof Hero.Mage) {
			System.out.println("1. 나무 지팡이 (20원)\n2. 쇠 지팡이 (50원)\n3. 다이아 지팡이 (100원)");
		} else if (hero instanceof Hero.Archer) {
			System.out.println("1. 작은 활 (20원)\n2. 큰 활 (50원)\n3. 로보틱 활 (100원)");
		}

		System.out.print("원하시는 무기를 입력하세요 : ");
		int weaponNum = in.nextInt();
		int oriMoney = hero.getMoney();
		hero.reduceMoney(buyWeapon(hero, weaponNum, oriMoney));
		if (hero.getMoney() < oriMoney) {
			System.out.println("포션 상점에서 물건을 구매 시도하는 중입니다.\n구입 완료되었습니다.");
			System.out.printf("%d원 남았습니다.\n", hero.getMoney());
		} else {
			System.out.println("돈이 부족하여 무기를 구매할 수 없습니다.");
		}
	}

	public int buyWeapon(Hero hero, int num, int money) {
		if (hero instanceof Hero.Warrior) {
			return buyWarriorWeapon(hero, num, money);
		} else if (hero instanceof Hero.Mage) {
			return buyMageWeapon(hero, num, money);
		} else if (hero instanceof Hero.Archer) {
			return buyArcherWeapon(hero, num, money);
		}
		return 0;
	}

	public int buyWarriorWeapon(Hero hero, int num, int money) {
		if (num == 1 && money >= 20) {
			hero.increasePower(10);
			return 20;
		} else if (num == 2 && money >= 50) {
			hero.increasePower(20);
			return 50;
		} else if (num == 3 && money >= 100) {
			hero.increasePower(30);
			return 100;
		}
		return 0;
	}

	public int buyMageWeapon(Hero hero, int num, int money) {
		if (num == 1 && money >= 20) {
			hero.increasePower(10);
			return 20;
		} else if (num == 2 && money >= 50) {
			hero.increasePower(20);
			return 50;
		} else if (num == 3 && money >= 100) {
			hero.increasePower(30);
			return 100;
		}
		return 0;
	}

	public int buyArcherWeapon(Hero hero, int num, int money) {
		if (num == 1 && money >= 20) {
			hero.increasePower(10);
			return 20;
		} else if (num == 2 && money >= 50) {
			hero.increasePower(20);
			return 50;
		} else if (num == 3 && money >= 100) {
			hero.increasePower(30);
			return 100;
		}
		return 0;
	}
}
