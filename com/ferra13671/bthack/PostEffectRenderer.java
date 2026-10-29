package com.ferra13671.bthack;

import com.ferra13671.cometrenderer.utils.Builder;
import java.util.ArrayList;
import java.util.List;
import org.apiguardian.api.API;
import org.apiguardian.api.API.Status;

@API(status = Status.MAINTAINED)
public class PostEffectRenderer extends Builder {
   final transient List list1 = new ArrayList();
   final transient List list2 = new ArrayList();

   public PostEffectRenderer withHelper11(Helper11 helper11) {
      this.list2.add(helper11);
      return this;
   }

   public Helper39 build() {
      return new Helper39(this.list1, this.list2);
   }


   public PostEffectRenderer withClearColor(ClearColor... clearColorArr) {
      this.list1.addAll(List.of(clearColorArr));
      return this;
   }

   public PostEffectRenderer() {
      super("post-effect pipeline");
   }
}
