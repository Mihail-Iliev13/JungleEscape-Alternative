package junglesurvival.items.weapons;

import junglesurvival.participants.interfaces.Fieldable;

public class Weapon implements Fieldable {

  private static final int TOMAHAWK_DAMAGE_POINTS = 4;
  private static final int DAGGER_DAMAGE_POINTS = 4;
  private static final int SWORD_DAMAGE_POINTS = 6;
  private static final int TORCH_DAMAGE_POINTS = 5;
  private static final int BOW_DAMAGE_POINTS = 3;

  private WeaponType type;
  private int weaponDamagePoints;

  public Weapon(WeaponType type){
    this.type = type;
    setWeaponDamagePoints(type);
  }

  public WeaponType getType() {
    return type;
  }

  public int getDamagePoints() {
    return weaponDamagePoints;
  }

  private void setWeaponDamagePoints(WeaponType type) {
    switch (type){
      case SWORD:
        this.weaponDamagePoints = SWORD_DAMAGE_POINTS;
        break;
      case DAGGER:
        this.weaponDamagePoints = DAGGER_DAMAGE_POINTS;
        break;
      case BOW:
        this.weaponDamagePoints = BOW_DAMAGE_POINTS;
        break;
      case TORCH:
        this.weaponDamagePoints = TORCH_DAMAGE_POINTS;
      case TOMAHAWK:
        this.weaponDamagePoints = TOMAHAWK_DAMAGE_POINTS;
        break;
        default: break;
    }
  }

  @Override
  public boolean isFieldable() {
    return true;
  }

  @Override
  public String toString() {
    return this.getType().toString().toLowerCase();
  }
}
