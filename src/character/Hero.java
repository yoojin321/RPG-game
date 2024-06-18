package character;

import java.util.Random;
import java.util.Scanner;

import map.Mission;

public class Hero {
	public int level, power, hp, defense, mp, experience, money;
	public String name, job;

	public void initHero(Scanner in) {
		this.level = 1;
		this.experience = 0;
		this.money = 0;

		System.out.print("영웅의 이름을 입력하세요 : ");
		this.name = in.next();
		System.out.println("이름이 입력되었습니다.");
		System.out.println("게임에 입장하였습니다.");
		showStats();
	}

	public String getAttackName(int attackNum) {
		return "";
	}

	public int attack(Monster monster, int attackChoice) {
		return 0;
	}

	public String getName() {
		return name;
	}

	public int selectAttack(Scanner in) {
		if (level >= 1) {
			System.out.println("1. " + getAttackName(1));
		}
		if (level >= 2) {
			System.out.println("2. " + getAttackName(2));
		}
		if (level >= 3) {
			System.out.println("3. " + getAttackName(3));
		}
		System.out.print("공격 번호를 입력하세요: ");
		return in.nextInt();
	}

	protected int applyDamage(Monster monster, int baseDamage) {
		if (monster.getDefense() >= baseDamage) {
			return 0;
		} else {
			monster.reduceHp(baseDamage - monster.getDefense());
		}
		this.experience += monster.getExperience();
		this.money += monster.getMoney();
		return baseDamage;
	}

	public void attacked(int hp) {
		this.hp = hp;
		System.out.printf("%s의 체력은 %d 남았습니다.\n", name, hp);
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public void revive() {
		this.hp = 1;
		System.out.printf("%s가 부활했습니다.\n", name);
	}

	public void checkLevelUp() {
		if (experience >= level * 80) {
			level += 1;
			money += level * 50;
			experience = 0;
			System.out.printf("%s의 레벨이 %d가 되었습니다.\n", name, level);
			System.out.printf("레벨업 기념으로 돈이 %d원 증가하여 %d원이 되었습니다.\n", level * 50, money);

			Mission mission = new Mission();
			if (mission.performMission()) {
				int reward = level * 30;
				money += reward;
				System.out.printf("미션 성공! 보상으로 %d원이 지급되었습니다. 현재 돈: %d원\n", reward, money);
			} else {
				System.out.println("미션 실패! 보상 없음.");
			}
		}
	}

	public void showStats() {
		System.out.println("**************************");
		System.out.println("현재 Hero의 이름 : " + name);
		System.out.printf("현재 %s의 레벨 : %d\n", name, level);
		System.out.printf("현재 %s의 힘 : %d\n", name, power);
		System.out.printf("현재 %s의 방어력 : %d\n", name, defense);
		System.out.printf("현재 %s의 HP : %d\n", name, hp);
		System.out.printf("현재 %s의 MP : %d\n", name, mp);
		System.out.printf("현재 %s의 경험치 : %d\n", name, experience);
		System.out.printf("현재 %s의 돈 : %d원\n", name, money);
		System.out.println("**************************");
	}

	public void increasePower(int amount) {
		this.power += amount;
	}

	public void increaseDefense(int amount) {
		this.defense += amount;
	}

	public void increaseExperience(int amount) {
		this.experience += amount;
	}

	public void increaseHp(int amount) {
		this.hp += amount;
	}

	public void increaseMp(int amount) {
		this.mp += amount;
	}

	public void reduceMoney(int amount) {
		this.money -= amount;
	}

	public int getMoney() {
		return money;
	}

	public int getDefense() {
		return defense;
	}

	public int getHp() {
		return hp;
	}

	public boolean performMission(Scanner in) {
		Random random = new Random();
		int correctNumber = random.nextInt(10) + 1;
		System.out.println("레벨업 미션: 1부터 10 사이의 숫자 중 하나를 맞춰보세요!");
		System.out.print("숫자를 입력하세요: ");
		int userGuess = in.nextInt();
		return userGuess == correctNumber;
	}

	public static class Warrior extends Hero {
		public Warrior() {
			this.job = "전사";
			this.power = 15;
			this.defense = 25;
			this.hp = 80;
			this.mp = 0;
		}

		public String getAttackName(int attackNum) {
			switch (attackNum) {
			case 1:
				return "쓰러스트";
			case 2:
				return "삼단 베기";
			case 3:
				return "방패 공격";
			}
			return "";
		}

		public int attack(Monster monster, int attackChoice) {
			int damage = 0;
			switch (attackChoice) {
			case 1:
				damage = thrust(monster);
				break;
			case 2:
				damage = tripleSlash(monster);
				break;
			case 3:
				damage = shieldAttack(monster);
				break;
			}
			return damage;
		}

		public int thrust(Monster monster) {
			int baseDamage = level * 10 + power * 30;
			if (monster.getName().equals("살쾡이")) {
				baseDamage *= 1.5; // 살쾡이에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int tripleSlash(Monster monster) {
			int baseDamage = level * 15 + power * 35;
			if (monster.getName().equals("너구리")) {
				baseDamage *= 1.5; // 너구리에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int shieldAttack(Monster monster) {
			int baseDamage = level * 20 + power * 40;
			if (monster.getName().equals("들개")) {
				baseDamage *= 1.5; // 들개에게 취약
			}
			return applyDamage(monster, baseDamage);
		}
	}

	public static class Mage extends Hero {
		public Mage() {
			this.job = "마법사";
			this.power = 18;
			this.defense = 10;
			this.hp = 60;
			this.mp = 60;
		}

		public String getAttackName(int attackNum) {
			switch (attackNum) {
			case 1:
				return "얼음";
			case 2:
				return "파이어볼";
			case 3:
				return "번개";
			}
			return "";
		}

		public int attack(Monster monster, int attackChoice) {
			int damage = 0;
			switch (attackChoice) {
			case 1:
				damage = iceBolt(monster);
				break;
			case 2:
				damage = fireBall(monster);
				break;
			case 3:
				damage = lightning(monster);
				break;
			}
			return damage;
		}

		public int iceBolt(Monster monster) {
			int baseDamage = level * 12 + power * 25;
			if (monster.getName().equals("살쾡이")) {
				baseDamage *= 1.5; // 살쾡이에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int fireBall(Monster monster) {
			int baseDamage = level * 18 + power * 30;
			if (monster.getName().equals("멧돼지")) {
				baseDamage *= 1.5; // 멧돼지에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int lightning(Monster monster) {
			int baseDamage = level * 20 + power * 35;
			if (monster.getName().equals("들개")) {
				baseDamage *= 1.5; // 들개에게 취약
			}
			return applyDamage(monster, baseDamage);
		}
	}

	public static class Archer extends Hero {
		public Archer() {
			this.job = "궁수";
			this.power = 15;
			this.defense = 10;
			this.hp = 70;
			this.mp = 0;
		}

		public String getAttackName(int attackNum) {
			switch (attackNum) {
			case 1:
				return "화살";
			case 2:
				return "독화살";
			case 3:
				return "연속 화살";
			}
			return "";
		}

		public int attack(Monster monster, int attackChoice) {
			int damage = 0;
			switch (attackChoice) {
			case 1:
				damage = arrow(monster);
				break;
			case 2:
				damage = poisonArrow(monster);
				break;
			case 3:
				damage = rapidArrow(monster);
				break;
			}
			return damage;
		}

		public int arrow(Monster monster) {
			int baseDamage = level * 11 + power * 28;
			if (monster.getName().equals("너구리")) {
				baseDamage *= 1.5; // 너구리에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int poisonArrow(Monster monster) {
			int baseDamage = level * 13 + power * 30;
			if (monster.getName().equals("들개")) {
				baseDamage *= 1.5; // 들개에게 취약
			}
			return applyDamage(monster, baseDamage);
		}

		public int rapidArrow(Monster monster) {
			int baseDamage = level * 17 + power * 32;
			if (monster.getName().equals("멧돼지")) {
				baseDamage *= 1.5; // 멧돼지에게 취약
			}
			return applyDamage(monster, baseDamage);
		}
	}
}
