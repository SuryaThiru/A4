package model;

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

  public int numChannels() {
    return channels.length;
  }
}
