package model;

/**
 * This class represents every Pixel object in an Image.
 */
public class Pixel {

  int[] channels;

  /**
   * Constructor is used to initialize the class variables.
   *
   * @param channels represents the no of channels.
   */
  public Pixel(int... channels) {
    this.channels = channels;
  }

  /**
   * This method is used to return all the channels available in the Pixel.
   * @param index represents the index of the pixel.
   * @return returns an integer value.
   */
  public int getChannels(int index) {
    if (index > channels.length || index < 0) {
      throw new IllegalArgumentException("index mismatch");
    }
    return channels[index];
  }

  @Override
  public boolean equals(Object obj) {

    // type casting obj to Employee
    Pixel s = (Pixel) obj;
    for (int i = 0; i < channels.length; i++) {
      if (channels[i] != s.channels[i]) {
        return false;
      }
    }
    return true;
  }

  /**
   * This method is used to return the total number of channels in a pixel.
   * @return returns an integer value.
   */
  public int numChannels() {
    return channels.length;
  }
}
