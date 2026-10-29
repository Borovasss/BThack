package com.ferra13671.bthack;

import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public record ClearColor(String name, int clearcolor) {

   public int getClearcolor() {
      return this.clearcolor;
   }
}
