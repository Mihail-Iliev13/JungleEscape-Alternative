package junglesurvival.participants;

public abstract class Boss extends Enemy {

  private static final int REGULAR_DAMAGE_PER_ATTACK = 7;
  protected final int BOSS_STARTING_LIFE_POINTS = 40;
  private static final int BOSS_GIVEN_EXPERIENCE = 5;

  protected Boss(){
    super();
    setLifepoints(BOSS_STARTING_LIFE_POINTS);
    setDamage(REGULAR_DAMAGE_PER_ATTACK);
    setExperiencePointsGiven(BOSS_GIVEN_EXPERIENCE);
  }

  protected Boss(String name) {
    super(name);
    setLifepoints(BOSS_STARTING_LIFE_POINTS);
    setDamage(REGULAR_DAMAGE_PER_ATTACK);
    setExperiencePointsGiven(BOSS_GIVEN_EXPERIENCE);
  }

  protected abstract void doSomeMagic(Hero hero);

  protected abstract boolean isMagicAvailable();

  @Override
  public void attack(Hero hero) {

    if(isMagicAvailable()){
      doSomeMagic(hero);
    } else {
      super.attack(hero);
    }

  }
}
