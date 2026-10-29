package com.ferra13671.bthack;

import java.util.ArrayList;
import java.util.List;

public final class Helper10 {
   static final transient List<Command> list1 = new ArrayList<>();

   static {
      subscribe(
         new Auto2faCommand(),
         new AutoauthCommand(),
         new ClearchatCommand(),
         new ConfigCommand(),
         new FakeplayerCommand(),
         new ModuleCommand(),
         new OpenDirectoryCommand(),
         new PrefixCommand(),
         new RotateCommand(),
         new SocialsCommand()
      );
   }

   public static List<Command> getList() {
      return list1;
   }

   public static void subscribe(Command... commandArr) {
      list1.addAll(List.of(commandArr));
   }
}
