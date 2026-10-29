package com.ferra13671.bthack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class PlaceholderParser {
   static final transient Pattern pattern1 = Pattern.compile("\\$\\{id\\h*=\\h*(?<id>[^{}]*)}");
   static final transient Pattern pattern2 = Pattern.compile("\\$<\\h*this\\h*>");
   static final transient Pattern pattern3 = Pattern.compile("\\$<\\h*parent\\h*>");

   static void handleId(Id id, String string, Map<Id, String> map, Map<String, String> map2) {
      if (!map2.containsKey(id.id())) {
         string = getString3(string, pattern1, (matchResult) -> PlaceholderParser.getId6(id, map, map2, matchResult));
         map2.put(id.id(), string);
      }
   }

   private static String getId1(Id id, MatchResult matchResult) {
      return id.id();
   }

   private static void handleMap2(Map<Id, String> map, Map<String, String> map2, Id id, String string) {
      handleId(id, string, map, map2);
   }

   static String getString3(String string2, Pattern pattern, Function<MatchResult, String> function) {
      Matcher matcher = pattern.matcher(string2);

      for (MatchResult matchResult : matcher.results().toList()) {
         String string = function.apply(matchResult);
         if (string != null) {
            string2 = string2.replace(matchResult.group(), string);
         }
      }

      return string2;
   }

   private static String getId4(Id id, MatchResult matchResult) {
      return id.getId().id();
   }

   PlaceholderParser() {
      throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
   }

   static Map<String, String> withMap5(Map<Id, String> map) {
      Map<String, String> map2 = new HashMap<>();
      map.forEach((id, string) -> PlaceholderParser.handleMap2(map, map2, id, string));
      return map2;
   }

   private static String getId6(Id id, Map<Id, String> map, Map<String, String> map2, MatchResult matchResult) {
      String string = matchResult.group("id");
      string = getString3(string, pattern2, (matchResult1) -> PlaceholderParser.getId1(id, matchResult1));
      string = getString3(string, pattern3, (matchResult1) -> PlaceholderParser.getId4(id, matchResult1));
      Id id2 = new Id(string);
      if (map.containsKey(id2)) {
         if (!map2.containsKey(id2.id())) {
            handleId(id2, (String)map.get(id2), map, map2);
         }

         return (String)map2.get(id2.id());
      } else {
         return null;
      }
   }
}
