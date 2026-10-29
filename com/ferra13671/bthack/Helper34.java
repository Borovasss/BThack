package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Helper34 {
   final transient HashMap friends = new HashMap();
   final transient HashMap enemies = new HashMap();

   public boolean isEnemy(String string) {
      return this.enemies.containsKey(string);
   }

   public void removeEnemy(String string) {
      this.enemies.remove(string);
   }

   public List getEnemies() {
      return this.enemies.values().stream().toList();
   }

   public void forEachEnemy(Consumer consumer) {
      this.enemies.values().forEach(consumer);
   }

   public void forEachFriend(Consumer consumer) {
      this.friends.values().forEach(consumer);
   }

   public Helper13 getEnemy(String string) {
      return (Helper13)this.enemies.get(string);
   }

   public void addEnemy(Helper13 helper13) {
      this.enemies.put(helper13.getName(), helper13);
   }

   public void addFriend(Helper13 helper13) {
      this.friends.put(helper13.getName(), helper13);
   }

   public Helper13 getFriend(String string) {
      return (Helper13)this.friends.get(string);
   }

   public void removeFriend(String string) {
      this.friends.remove(string);
   }

   public boolean isFriend(String string) {
      return this.friends.containsKey(string);
   }

   public List getFriends() {
      return this.friends.values().stream().toList();
   }
}
