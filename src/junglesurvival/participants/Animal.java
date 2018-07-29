package junglesurvival.participants;

public class Animal extends Enemy {

  private static final int REGULAR_DAMAGE_PER_ATTACK = 1;
  private static final int ANIMAL_STARTING_LIFE_POINTS = 7;
  private static final int ANIMAL_GIVEN_EXPERIENCE = 1;
  private AnimalType animalType;
  private boolean isTamed;

  public Animal(AnimalType animalType) {
    super(animalType.toString().toLowerCase());
    this.animalType = animalType;
    setExperiencePointsGiven(ANIMAL_GIVEN_EXPERIENCE);
    setDamage(REGULAR_DAMAGE_PER_ATTACK);
    setLifepoints(ANIMAL_STARTING_LIFE_POINTS);
  }

  private boolean isTamed(){
    return isTamed;
  }


  AnimalType getAnimalType() {
    return animalType;
  }

  public void attack(Enemy enemy) {
    if (enemy instanceof Animal){
      Animal currentEnemy = (Animal)enemy;
      if (!this.equals(currentEnemy)){
        currentEnemy.reduceLifepoints(getDamage());
      } else {
        System.out.println("Animals from same type can't attack each other");
      }
    } else {
      enemy.reduceLifepoints(getDamage());
    }
  }

  @Override
  public void attack(Hero hero) {

    if(!isTamed()){
    super.attack(hero);
    } else {
      System.out.println("Tamed animal can't attack its owner!");
    }
  }

  public boolean equals(Animal animal) {
    if(animal.getAnimalType().equals(this.getAnimalType())){
      return true;
    }
    return false;
  }

}

