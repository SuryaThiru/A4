package model;

/**
 * This class represents every Pixel object in an Image and implements Pixel interface.
 */
public class PixelImpl implements Pixel {

  int[] channels;

  /**
   * Constructor is used to initialize the class variables.
   *
   * @param channels represents the no of channels.
   */
  public PixelImpl(int... channels) {
    this.channels = channels;
  }

  @Override
  public boolean equals(Object obj) {

    // type casting obj to Employee
    PixelImpl s = (PixelImpl) obj;
    for (int i = 0; i < channels.length; i++) {
      // To handle lossy conversion - delta 40
      if ((s.channels[i] != 0
              && Math.abs(channels[i] - s.channels[i]) / s.channels[i] * 100 > 40)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    for (int channel : channels) {
      result = prime * result + channel;
    }
    return result;
  }

  @Override
  public int numChannels() {
    return channels.length;
  }

  @Override
  public int getChannel(int index) {
    if (index > channels.length || index < 0) {
      throw new IllegalArgumentException("index mismatch");
    }
    return channels[index];
  }

  @Override
  public int[] getChannels() {
    return this.channels;
  }

}
