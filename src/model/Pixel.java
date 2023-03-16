package model;

import java.util.Arrays;

public class Pixel {

  int[] channels;

  public Pixel(int... channels) {
    this.channels = channels;
  }

  public int getChannels(int index) {
    if(index > channels.length || index < 0) {
      throw new IllegalArgumentException("index mismatch");
    }
    return channels[index];
  }

  public boolean equals(Object obj)
  {

    // type casting obj to Employee
    Pixel s = (Pixel) obj;
    for (int i = 0; i < channels.length; i++) {
      if (channels[i] != s.channels[i]) {
        return false;
      }
    }
    return true;
  }

  public int numChannels() {
    return channels.length;
  }
}
