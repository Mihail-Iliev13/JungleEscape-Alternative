package junglesurvival.participants;

public abstract class Monster extends Enemy{

  private static final int REGULAR_DAMAGE_PER_ATTACK = 4;
  private static final int MONSTER_STARTING_LIFE_POINTS = 30;
  private static final int MONSTER_GIVEN_EXPERIENCE = 3;

  Monster(String name) {
    super(name);
    setLifepoints(MONSTER_STARTING_LIFE_POINTS);
    setDamage(REGULAR_DAMAGE_PER_ATTACK);
    setExperiencePointsGiven(MONSTER_GIVEN_EXPERIENCE);
  }

  Monster() {
    super();
    setLifepoints(MONSTER_STARTING_LIFE_POINTS);
    setDamage(REGULAR_DAMAGE_PER_ATTACK);
    setExperiencePointsGiven(MONSTER_GIVEN_EXPERIENCE);
  }

  protected abstract void fierceAttack(Hero hero);

  protected abstract boolean isFierceAttackAvailable(Hero hero);

  @Override
  public void attack(Hero hero) {
    if(isFierceAttackAvailable(hero)){
      fierceAttack(hero);
    } else {
      if(getDamage() != REGULAR_DAMAGE_PER_ATTACK){
        setDamage(REGULAR_DAMAGE_PER_ATTACK);
      }
      super.attack(hero);
    }
  }
}
