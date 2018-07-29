package junglesurvival.participants;

import junglesurvival.GameMessages;
import junglesurvival.items.jewels.Jewel;
import junglesurvival.items.jewels.JewelColor;
import junglesurvival.items.mushrooms.Mushroom;
import junglesurvival.items.potions.Potion;
import junglesurvival.items.potions.PotionType;
import junglesurvival.items.weapons.Weapon;
import junglesurvival.items.weapons.WeaponType;
import junglesurvival.participants.interfaces.Healable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.function.Predicate;

public class Hero extends Participant implements Healable{

  private static final int STARTING_LEVEL = 1;
  public static final int MAX_LIFE_POINTS = 100;
  private static final int EXP_POINTS_NEEDED_FOR_LEVELING_UP = 4;
  private static final int BONUS_DAMAGE_FOR_LEVELING_UP = 2;

  private Gender gender;
  private int experiencePoints;
  private int level;
  private int baseDamage = 0;
  private Weapon currentWeapon;
  private ArrayList<Weapon> weapons;
  //should be protected because it has to be used for specific methods in child classes
  protected LinkedList<Jewel> jewels;
  private ArrayList<Potion> potions;

  protected Hero(WeaponType weapon, Gender gender){
    super();
    setLifepoints(MAX_LIFE_POINTS);
    setGender(gender);
    this.currentWeapon = new Weapon(weapon);
    setDamage(currentWeapon.getDamagePoints() + baseDamage);
    setExperiencePoints(0);
    this.level = STARTING_LEVEL;
    this.jewels = new LinkedList<>();
    this.potions = new ArrayList<>();
    this.weapons = new ArrayList<>();
  }

  protected Hero(String name, WeaponType weapon, Gender gender) {
    super(name);
    setLifepoints(MAX_LIFE_POINTS);
    setGender(gender);
    this.currentWeapon = new Weapon(weapon);
    setDamage(currentWeapon.getDamagePoints() + baseDamage);
    setExperiencePoints(0);
    this.level = STARTING_LEVEL;
    this.jewels = new LinkedList<>();
    this.potions = new ArrayList<>();
    this.weapons = new ArrayList<>();
  }

  public Gender getGender() {
    return gender;
  }

  public int getExperiencePoints() {
    return experiencePoints;
  }

  public Weapon getCurrentWeapon(){
    return new Weapon(currentWeapon.getType());
  }

  public ArrayList<Weapon> listOfWeapons(){
    return new ArrayList<>(weapons);
  }

  public int getLevel() {
    return level;
  }

  public int jewelsCollected(){
    return jewels.size();
  }

  public void pickItem(Weapon weapon){

    if(!hasWeapon(weapon)){
      weapons.add(weapon);
      GameMessages.showPickItemMessage(this,weapon.toString());
    }
  }

  public void pickItem(Jewel jewel){
    jewels.add(jewel);
    GameMessages.showPickItemMessage(this, jewel.toString());
  }

  public void pickItem(Potion potion){
    potions.add(potion);
    GameMessages.showPickItemMessage(this, potion.toString());
  }

  //TODO: if there are no jewels in the bag, throw exception;
  public void bribe(Monster monster){
    if(jewels.size() > 0 && hasJewel(JewelColor.BLUE)){
      useJewel(JewelColor.BLUE);
      GameMessages.showBribedEnemyMessage(monster);
    } else {
      GameMessages.showEnemyCantBeBribedMessage(monster);
      fight(monster);
    }
  }

  public void bribe(Boss boss){
    if(jewels.size() > 0 && hasJewel(JewelColor.RED)){
      useJewel(JewelColor.RED);
      GameMessages.showBribedEnemyMessage(boss);
    } else {
      GameMessages.showEnemyCantBeBribedMessage(boss);
      fight(boss);
    }
  }

  public void switchWeapon(WeaponType weapon) {
    Predicate<Weapon> p1 = e -> e.getType() == weapon;
    if (weapons.stream().anyMatch(p1)){
      equip(new Weapon(weapon));
    } else {
      GameMessages.showMissingWeaponMessage(weapon);
    }
  }

  public void switchWeapon(String weapon) {
    if(weapon.equals("current"))
      return;

    for (int i = 0; i < listOfWeapons().size(); i++) {
      if(listOfWeapons().get(i).toString().equals(weapon)){
        equip(listOfWeapons().get(i));
        return;
      }
    }
    GameMessages.showMissingWeaponMessage(weapon);
  }

  public void fight (Enemy enemy){

    while (!enemy.isDead()){
      enemy.attack(this);
      this.attack(enemy);
    }
  }

  public void fight (Animal animal){

    while (!animal.isDead()){
      animal.attack(this);
      this.attack(animal);
    }
  }

  public void equip(Weapon weapon){

    Weapon oldWeapon = new Weapon(currentWeapon.getType());
    this.weapons.add(oldWeapon);
    this.currentWeapon = weapon;
    this.weapons.remove(weapon);
    changeDamage();
    GameMessages.showEquipWeaponMessage(this, oldWeapon.getType(), getCurrentWeapon().getType());
  }

  public void drink(Potion potion){

    if (potion.getType().equals(PotionType.EXPERIENCE)){
      gainExperience(potion);
    } else {
      heal(potion.getPointsGiven());
      GameMessages.showHealMessage(this, potion.getPointsGiven());
    }
  }

  public void drink(){

    int index = potions.indexOf(new Potion(PotionType.HEALTH));
    heal(Potion.HEALTH_POINTS_GIVEN);
    potions.remove(index);
  }

  public void eat(Mushroom mushroom){

    if(mushroom.isGood()){
      heal(mushroom.getMushroomPoints());
      GameMessages.showGoodMushroomEatenMessage(this, mushroom);
    } else {
      reduceLifepoints(mushroom.getMushroomPoints());
      GameMessages.showBadMushroomEatenMessage(this, mushroom);
    }
  }

  public boolean hasHealthPotion(){
    if(potions.size() == 0)
      return false;

    int index = 0;
    do {

      if(potions.get(index).getType().equals(PotionType.HEALTH))
        return true;

      ++index;
    } while (index < potions.size());

    return false;
  }

  public boolean hasWeapon(Weapon newWeapon) {

    Predicate<Weapon> p1 = e -> e.getType() == newWeapon.getType();
    if (weapons.stream().anyMatch(p1)){
      return true;
    }
    return false;
  }

  protected int getBaseDamage(){
    return baseDamage;
  }

  protected void attack(Enemy enemy){

    GameMessages.showAttackMessage(this);
    enemy.concedeAttack(this);

    if(enemy.isDead()) {
      gainExperience(enemy);
    } else {
      GameMessages.showEnemyReducedLifePointsMessage(enemy, this);
    }
  }

  boolean hasJewel(JewelColor blue) {
    int index = 0;
    while (index < jewels.size()){
      if (jewels.get(index).getColor().equals(JewelColor.BLUE)){
        return true;
      }
      ++index;
    }
    return false;
  }

  void useJewel(JewelColor colour) {
    jewels.sort(Comparator.comparing(Jewel::getColor));
    if (jewels.getFirst().getColor().equals(colour)){
      jewels.removeFirst();
    } else if (jewels.getLast().getColor().equals(colour)){
      jewels.removeLast();
    }

    KingKong kg = new KingKong();
  }

  void concedeAttack(Enemy enemy){

    reduceLifepoints(enemy.getDamage());

    if(isDead()){
      GameMessages.showDeadMessage();
      System.exit(0);
    } else {
      GameMessages.showHeroLifePointsReducedMessage(this, enemy);
    }
  }

  private void setGender(Gender gender){
    this.gender = gender;
  }

  private void setExperiencePoints(int experience) {
    this.experiencePoints = experience;
  }

  private void changeDamage(){
    setDamage(currentWeapon.getDamagePoints() + baseDamage);
  }

  private void levelUp(){
    ++level;
    baseDamage += BONUS_DAMAGE_FOR_LEVELING_UP;
    changeDamage();
    GameMessages.showLevelUpMessage(this);
  }

  private void gainExperience(Potion potion) {

    if(potion.getType() != PotionType.EXPERIENCE){
      return;
    }

    int currentExperiencePoints = getExperiencePoints();
    int additionalPoints = potion.getPointsGiven();
    setExperiencePoints(currentExperiencePoints + additionalPoints);
    GameMessages.showGainExperienceMessage(this, potion);
    if (getExperiencePoints() >= EXP_POINTS_NEEDED_FOR_LEVELING_UP){
      levelUp();
      setExperiencePoints(getExperiencePoints() % EXP_POINTS_NEEDED_FOR_LEVELING_UP);
    }
  }

  private void gainExperience(Enemy enemy) {

    int currentExperiencePoints = getExperiencePoints();
    int additionalPoints = enemy.getExperiencePointsGiven();
    setExperiencePoints(currentExperiencePoints + additionalPoints);
    GameMessages.showGainExperienceMessage(this,enemy);
    if (getExperiencePoints() >= EXP_POINTS_NEEDED_FOR_LEVELING_UP){
      levelUp();
      setExperiencePoints(getExperiencePoints() % EXP_POINTS_NEEDED_FOR_LEVELING_UP);
    }
  }

  @Override
  public void heal(int increaser) {

    int currentLifepoints = getLifepoints();
    if(currentLifepoints + increaser > MAX_LIFE_POINTS){
      setLifepoints(MAX_LIFE_POINTS);
    } else {
      setLifepoints(currentLifepoints + increaser);
    }
  }

}
