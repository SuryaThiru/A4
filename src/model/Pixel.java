package model;

/**
 * This Interface represents a pixel which consists of multiple channels.
 */
public interface Pixel {

  /**
   * This method is used to return a particular channel available in the Pixel.
   *
   * @param index represents the index of the pixel.
   * @return returns an integer value.
   */
  int getChannel(int index);

  /**
   * This method is used to return the total number of channels in a pixel.
   *
   * @return returns an integer value
   */
  int numChannels();

  /**
   * This method gets all the channel values.
   *
   * @return returns integer array of channel values
   */
  int[] getChannels();

}
