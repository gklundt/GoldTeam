/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goldteam.domain;

/**
 *
 * @author Mishal
 */
public interface Sky {
    
      public Double getDurabilityPercentage();

      public void setDurabilityPercentage(Double durabilityPercentage);

      public Double getDecayRate();

      public void setDecayRate(Double decayRate);

      public boolean isSolidTop();

      public boolean isSolidBottom();

    public void setSolidTop(Boolean isSolidTop);

    public void setSolidBottom(Boolean isSolidBottom);
}
