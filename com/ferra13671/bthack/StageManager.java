package com.ferra13671.bthack;

import com.ferra13671.bthack.loader.api.logger.LoggerLevel;
import java.util.ArrayList;
import java.util.List;

public class StageManager extends Helper4 {
   protected final transient List<Helper4> stages = new ArrayList<>();
   protected final transient String name;
   protected transient Helper4 task = null;
   protected transient boolean completed = false;

   @Override
   public final void runVoid1() {
      this.markCompleted();
      BThackClient.getInstance().getLogger().log(LoggerLevel.BOOT, "Invoking stage:".concat(this.getString()));
      this.runVoid2();

      for (Helper4 helper4 : this.stages) {
         this.task = helper4;
         this.task.runVoid1();
      }

      this.completed = true;
   }

   @Override
   public boolean isBoolean2() {
      return this.completed;
   }

   @Override
   public Helper4 withString(String string) {
      int num = this.indexOfStage(string);
      return num == -1 ? null : (Helper4)this.stages.get(num);
   }

   private int indexOfStage(String string) {
      for (int num = 0; num < this.stages.size(); num++) {
         Helper4 helper4 = (Helper4)this.stages.get(num);
         if (helper4.getName().equals(string)) {
            return num;
         }
      }

      return -1;
   }

   @Override
   public Helper4 withString3(String string, Helper4 helper4) {
      this.markCompleted();
      int num = this.indexOfStage(string);
      if (num == -1) {
         throw new NullPointerException(String.format("Cannot find required stage '%s'", string));
      } else {
         return this.withInt(num + 1, helper4);
      }
   }

   @Override
   public String getString() {
      return (this.helper4 == null ? " " : this.helper4.getString().concat(" -> ")).concat(this.getName());
   }

   private void markCompleted() {
      if (this.isBoolean2()) {
         throw new IllegalStateException("InitProvider has finished initializing");
      }
   }

   @Override
   public Helper4 withString2(String string, Helper4 helper4) {
      this.markCompleted();
      int num = this.indexOfStage(string);
      if (num == -1) {
         throw new NullPointerException(String.format("Cannot find required stage '%s'", string));
      } else {
         return this.withInt(num - 1, helper4);
      }
   }

   @Override
   public String getName() {
      return this.name;
   }

   public static StageManager of(String string, Runnable runnable) {
      class StageManagerSub1 extends StageManager {
         final transient Runnable runnable1;

         StageManagerSub1(String string, Runnable var2) {
            super(string);
            this.runnable1 = var2;
         }

         @Override
         public void runVoid2() {
            this.runnable1.run();
         }
      }

      return new StageManagerSub1(string, runnable);
   }

   protected StageManager(String string) {
      this.name = string;
   }

   public void runVoid2() {
   }

   @Override
   public Helper4 withHelper4(Helper4 helper4) {
      this.markCompleted();
      this.stages.addFirst(helper4);
      helper4.helper4 = this;
      return helper4;
   }

   public static StageManager of(String string) {
      return new StageManager(string);
   }

   public Helper4 getTask() {
      return this.task;
   }

   @Override
   public Helper4 push(Helper4 helper4) {
      this.markCompleted();
      this.stages.addLast(helper4);
      helper4.helper4 = this;
      return helper4;
   }

   @Override
   public Helper4 withInt(int num, Helper4 helper4) {
      if (num >= 0 && num <= this.stages.size() - 1) {
         this.stages.add(num, helper4);
         helper4.helper4 = this;
         return helper4;
      } else {
         throw new IllegalStateException(String.format("position(%s) must be between %s and %s", num, 0, this.stages.size() - 1));
      }
   }
}
