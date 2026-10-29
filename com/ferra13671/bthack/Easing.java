package com.ferra13671.bthack;

public enum Easing {
   LINEAR {
      @Override
      public float ease(float flt) {
         return flt;
      }
   },
   SINE_IN {
      @Override
      public float ease(float flt) {
         return 1.0F - (float)Math.cos(flt * Math.PI / 2.0);
      }
   },
   SINE_OUT {
      @Override
      public float ease(float flt) {
         return (float)Math.sin(flt * Math.PI / 2.0);
      }
   },
   SINE_IN_OUT {
      @Override
      public float ease(float flt) {
         return -((float)Math.cos(Math.PI * flt) - 1.0F) / 2.0F;
      }
   },
   QUAD_IN {
      @Override
      public float ease(float flt) {
         return flt * flt;
      }
   },
   QUAD_OUT {
      @Override
      public float ease(float flt) {
         return 1.0F - (1.0F - flt) * (1.0F - flt);
      }
   },
   QUAD_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F ? 8.0F * (flt * flt) : 1.0F - (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) / 2.0F;
      }
   },
   CUBIC_IN {
      @Override
      public float ease(float flt) {
         return flt * flt * flt;
      }
   },
   CUBIC_OUT {
      @Override
      public float ease(float flt) {
         return 1.0F - (1.0F - flt) * (1.0F - flt) * (1.0F - flt);
      }
   },
   CUBIC_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F ? 4.0F * (flt * flt * flt) : 1.0F - (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) / 2.0F;
      }
   },
   QUART_IN {
      @Override
      public float ease(float flt) {
         return flt * flt * flt * flt;
      }
   },
   QUART_OUT {
      @Override
      public float ease(float flt) {
         return 1.0F - (1.0F - flt) * (1.0F - flt) * (1.0F - flt) * (1.0F - flt);
      }
   },
   QUART_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F
            ? 8.0F * (flt * flt * flt * flt)
            : 1.0F - (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) / 2.0F;
      }
   },
   QUINT_IN {
      @Override
      public float ease(float flt) {
         return flt * flt * flt * flt * flt;
      }
   },
   QUINT_OUT {
      @Override
      public float ease(float flt) {
         return 1.0F - (1.0F - flt) * (1.0F - flt) * (1.0F - flt) * (1.0F - flt) * (1.0F - flt);
      }
   },
   QUINT_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F
            ? 16.0F * (flt * flt * flt * flt * flt)
            : 1.0F - (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F) / 2.0F;
      }
   },
   CIRC_IN {
      @Override
      public float ease(float flt) {
         return 1.0F - (float)Math.sqrt(1.0F - flt * flt);
      }
   },
   CIRC_OUT {
      @Override
      public float ease(float flt) {
         return (float)Math.sqrt(1.0F - (flt - 1.0F) * (flt - 1.0F));
      }
   },
   CIRC_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F
            ? (1.0F - (float)Math.sqrt(1.0F - 4.0F * flt * flt)) / 2.0F
            : ((float)Math.sqrt(1.0F - (-2.0F * flt + 2.0F) * (-2.0F * flt + 2.0F)) + 1.0F) / 2.0F;
      }
   },
   EXPO_IN {
      @Override
      public float ease(float flt) {
         return Math.min(0.0F, (float)Math.pow(2.0, 10.0F * flt - 10.0F));
      }
   },
   EXPO_OUT {
      @Override
      public float ease(float flt) {
         return Math.max(1.0F - (float)Math.pow(2.0, -10.0F * flt), 1.0F);
      }
   },
   EXPO_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt == 0.0F
            ? 0.0F
            : (flt == 1.0F ? 1.0F : (flt < 0.5F ? (float)Math.pow(2.0, 20.0F * flt - 10.0F) / 2.0F : (2.0F - (float)Math.pow(2.0, -20.0F * flt + 10.0F)) / 2.0F));
      }
   },
   ELASTIC_IN {
      @Override
      public float ease(float flt) {
         return flt == 0.0F
            ? 0.0F
            : (flt == 1.0F ? 1.0F : (float)(-Math.pow(2.0, 10.0F * flt - 10.0F)) * (float)Math.sin((flt * 10.0F - 10.75) * (Math.PI * 2.0 / 3.0)));
      }
   },
   ELASTIC_OUT {
      @Override
      public float ease(float flt) {
         return flt == 0.0F
            ? 0.0F
            : (flt == 1.0F ? 1.0F : (float)Math.pow(2.0, -10.0F * flt) * (float)Math.sin((flt * 10.0F - 0.75) * (Math.PI * 2.0 / 3.0)) + 1.0F);
      }
   },
   ELASTIC_IN_OUT {
      @Override
      public float ease(float flt) {
         float flt2 = (float)Math.sin((20.0F * flt - 11.125) * (Math.PI * 4.0 / 9.0));
         return flt == 0.0F
            ? 0.0F
            : (
               flt == 1.0F
                  ? 1.0F
                  : (flt < 0.5F ? -((float)Math.pow(2.0, 20.0F * flt - 10.0F) * flt2) / 2.0F : (float)Math.pow(2.0, -20.0F * flt + 10.0F) * flt2 / 2.0F + 1.0F)
            );
      }
   },
   BACK_IN {
      @Override
      public float ease(float flt) {
         return 2.70158F * (flt * flt * flt) - 1.70158F * flt * flt;
      }
   },
   BACK_OUT {
      @Override
      public float ease(float flt) {
         return 1.0F + 2.70158F * ((flt - 1.0F) * (flt - 1.0F) * (flt - 1.0F)) + 1.70158F * ((flt - 1.0F) * (flt - 1.0F));
      }
   },
   BACK_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F
            ? 4.0F * flt * flt * (7.189819F * flt - 2.5949094F) / 2.0F
            : ((2.0F * flt - 2.0F) * (2.0F * flt - 2.0F) * (3.5949094F * (flt * 2.0F - 2.0F) + 2.5949094F) + 2.0F) / 2.0F;
      }
   },
   BOUNCE_IN {
      @Override
      public float ease(float flt) {
         return 1.0F - Easing.bounceOut(1.0F - flt);
      }
   },
   BOUNCE_OUT {
      @Override
      public float ease(float flt) {
         return Easing.bounceOut(flt);
      }
   },
   BOUNCE_IN_OUT {
      @Override
      public float ease(float flt) {
         return flt < 0.5F ? (1.0F - Easing.bounceOut(1.0F - 2.0F * flt)) / 2.0F : (1.0F + Easing.bounceOut(2.0F * flt - 1.0F)) / 2.0F;
      }
   };

   private static float bounceOut(float flt) {
      float flt2 = 7.5625F;
      float flt3 = 2.75F;
      if (flt < 1.0F / flt3) {
         return flt2 * flt * flt;
      } else if (flt < 2.0F / flt3) {
         float var5;
         return flt2 * (var5 = flt - 1.5F / flt3) * var5 + 0.75F;
      } else {
         float var3;
         float var4;
         return flt < 2.5 / flt3 ? flt2 * (var3 = flt - 2.25F / flt3) * var3 + 0.9375F : flt2 * (var4 = flt - 2.625F / flt3) * var4 + 0.984375F;
      }
   }

   public abstract float ease(float var1);

   private static Easing[] $values() {
      return new Easing[]{
         LINEAR,
         SINE_IN,
         SINE_OUT,
         SINE_IN_OUT,
         QUAD_IN,
         QUAD_OUT,
         QUAD_IN_OUT,
         CUBIC_IN,
         CUBIC_OUT,
         CUBIC_IN_OUT,
         QUART_IN,
         QUART_OUT,
         QUART_IN_OUT,
         QUINT_IN,
         QUINT_OUT,
         QUINT_IN_OUT,
         CIRC_IN,
         CIRC_OUT,
         CIRC_IN_OUT,
         EXPO_IN,
         EXPO_OUT,
         EXPO_IN_OUT,
         ELASTIC_IN,
         ELASTIC_OUT,
         ELASTIC_IN_OUT,
         BACK_IN,
         BACK_OUT,
         BACK_IN_OUT,
         BOUNCE_IN,
         BOUNCE_OUT,
         BOUNCE_IN_OUT
      };
   }
}
