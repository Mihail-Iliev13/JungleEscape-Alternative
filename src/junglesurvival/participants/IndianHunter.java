package junglesurvival.participants;

import junglesurvival.GameMessages;
import junglesurvival.items.jewels.JewelColor;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.items.weapons.WeaponType;

public class IndianHunter extends Hero {

  private Animal tamedAnimal;

  public IndianHunter(){
    super(WeaponType.TOMAHAWK, Gender.MALE);
  }

  public IndianHunter(String name, WeaponType weapon, Gender gender){
    super(name, weapon, gender);
  }

   public Animal getTamedAnimal(){

    if(tamedAnimal == null){
      return null;
    }

    return new Animal(tamedAnimal.getAnimalType());
  }

  public void tameAnimal(Animal animal){

    if(jewels.size() > 0 && hasJewel(JewelColor.BLUE)){
      useJewel(JewelColor.BLUE);
      tamedAnimal = animal;
      GameMessages.showAnimalTamedMessage(this, animal);
    } else {
      GameMessages.showUntameableMessage(JewelColor.BLUE, animal);
    }
  }

  private boolean hasTamedAnimal(){
    return tamedAnimal != null;
  }

  private void attackWithAnimal(Enemy enemy){
    GameMessages.attackWithTamedAnimal(this ,tamedAnimal);
    getTamedAnimal().attack(enemy);
  }

  @Override
  public void attack(Enemy enemy) {
    if(hasTamedAnimal()){
      if (enemy instanceof Animal && ((Animal)enemy).equals(tamedAnimal)){
          GameMessages.showIdenticalAnimalsMessage();
          super.attack(enemy);
        } else {
        attackWithAnimal(enemy);
      }
    } else {
      super.attack(enemy);
    }
  }

  @Override
  void concedeAttack(Enemy attacker) {
    if(tamedAnimal != null) {
      tamedAnimal.reduceLifepoints(attacker.getDamage());
      GameMessages.showTamedAnimalReducedLifePoints(tamedAnimal);
      if(tamedAnimal.getLifepoints() < 1) {
        tamedAnimal = null;
      }
    } else {
      super.concedeAttack(attacker);
    }
  }

}
