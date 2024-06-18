package character;

public class Monster {
	public int hp, mp, power, defense, experience, money;
	public String name;

	public String getName() {
		return name;
	}

	public int getDefense() {
		return defense;
	}

	public void reduceHp(int amount) {
		this.hp -= amount;
	}

	public boolean isDead() {
		return hp <= 0;
	}

	public int attack(Hero hero) {
		if (hero.getDefense() >= power) {
			return hero.getHp();
		} else {
			return hero.getHp() + hero.getDefense() - power;
		}
	}

	public int getExperience() {
		return experience;
	}

	public int getMoney() {
		return money;
	}

	public void attacked(int damage) {
		System.out.printf("%s의 데미지는 %d입니다.\n", name, damage);
	}

	public static class Raccoon extends Monster {
		public Raccoon() {
			this.name = "너구리";
			this.hp = 50;
			this.mp = 10;
			this.power = 5;
			this.defense = 3;
			this.money = 5;
			this.experience = 10;
			System.out.println("너구리와 전투를 시작합니다.");
		}
	}

	public static class WildCat extends Monster {
		public WildCat() {
			this.name = "살쾡이";
			this.hp = 150;
			this.mp = 20;
			this.power = 15;
			this.defense = 7;
			this.money = 10;
			this.experience = 20;
			System.out.println("살쾡이와 전투를 시작합니다.");
		}
	}

	public static class WildDog extends Monster {
		public WildDog() {
			this.name = "들개";
			this.hp = 120;
			this.mp = 15;
			this.power = 12;
			this.defense = 5;
			this.money = 8;
			this.experience = 15;
			System.out.println("들개와 전투를 시작합니다.");
		}
	}

	public static class WildPig extends Monster {
		public WildPig() {
			this.name = "멧돼지";
			this.hp = 200;
			this.mp = 30;
			this.power = 20;
			this.defense = 10;
			this.money = 15;
			this.experience = 25;
			System.out.println("멧돼지와 전투를 시작합니다.");
		}
	}
}
