package main;

import java.util.Scanner;

import character.Hero;
import character.Monster;
import map.PotionStore;
import map.WeaponStore;

public class Rpg_Game1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		Hero hero = selectHero(in);
		hero.initHero(in);

		while (true) {
			System.out.println("1. 무기 상점\n2. 몬스터 선택");
			System.out.print("입장할 장소를 선택하세요 : ");
			int choice = in.nextInt();
			if (choice == 1) {
				WeaponStore weaponStore = new WeaponStore();
				weaponStore.enterWeaponStore(hero, in);
				hero.showStats();
			} else if (choice == 2) {
				Monster monster = selectMonster(in);

				while (true) {
					System.out.println(hero.getName() + "의 공격입니다.");
					int attackChoice = hero.selectAttack(in);
					int monsterDamage = hero.attack(monster, attackChoice);
					monster.attacked(monsterDamage);
					if (monster.isDead()) {
						System.out.printf("%s가 죽었습니다.\n", monster.getName());
						hero.checkLevelUp();
						break;
					}

					System.out.println(monster.getName() + "의 공격입니다.");
					int heroHp = monster.attack(hero);
					hero.attacked(heroHp);
					if (hero.isDead()) {
						hero.revive();
						hero.checkLevelUp();
						break;
					}
				}

				hero.showStats();

				while (true) {
					System.out.println("1. 사냥터\n2. 포션 상점");
					System.out.print("입장할 장소를 입력하세요 : ");
					int placeNum = in.nextInt();
					if (placeNum == 1) {
						break;
					} else if (placeNum == 2) {
						PotionStore potionStore = new PotionStore();
						potionStore.enterPotionStore(hero, in);
						hero.showStats();
					} else {
						System.out.println("입력 오류입니다. 다시 입력하십시오.");
					}
				}
			} else {
				System.out.println("입력 오류입니다. 다시 선택하십시오.");
			}
		}
	}

	public static Hero selectHero(Scanner in) {
		System.out.println("**********RPG GAME*********");
		System.out.println("1. 전사\n2. 마법사\n3. 궁수");
		System.out.print("직업의 번호를 입력하세요. : ");
		int jobChoice = in.nextInt();
		switch (jobChoice) {
		case 1:
			return new Hero.Warrior();
		case 2:
			return new Hero.Mage();
		case 3:
			return new Hero.Archer();
		default:
			System.out.println("잘못된 선택입니다. 제시된 숫자 중 하나를 입력하세요.");
			return selectHero(in);
		}
	}

	public static Monster selectMonster(Scanner in) {
		System.out.println("사냥터에 입장하였습니다.");
		System.out.println("1. 너구리\n2. 살쾡이\n3. 들개\n4. 멧돼지");
		System.out.print("전투할 상대를 입력하세요 : ");
		int a = in.nextInt();
		switch (a) {
		case 1:
			return new Monster.Raccoon();
		case 2:
			return new Monster.WildCat();
		case 3:
			return new Monster.WildDog();
		case 4:
			return new Monster.WildPig();
		default:
			System.out.println("입력 오류입니다. 전투할 상대를 숫자로 다시 입력하세요.");
			return selectMonster(in);
		}
	}
}
