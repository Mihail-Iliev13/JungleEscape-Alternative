package junglesurvival.items.weapons;

public enum WeaponType {
  TOMAHAWK("tomahawk"), DAGGER("dagger"), SWORD("sword"), TORCH("torch"), BOW("bow");

  private String str;

   WeaponType(String str){
    this.str = str;
  }

  @Override
   public String toString() {
    return str;
  }
}
