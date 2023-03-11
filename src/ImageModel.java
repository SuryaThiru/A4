/**
 * This interface acts as a Model in the Image Manipulation Application and holds all the
 * logic to the operations that can be performed by the user.
 */
public interface ImageModel {
  public void load(String filePath);

  public void save(String filePath);

  public void flipHorizontal();

  public void flipVertical();

  public void brighten(int increment);

  public ImageModel[] splitChannels();

  public void combineChannels(ImageModel[] channels);
}