package scanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileWatcher {

  protected List<FileListener> listeners = new ArrayList<>();

  protected final File folder;
  public FileWatcher(File folder) {
	  this.folder = folder;
  }

  public List<FileListener> getListeners() {
	  return listeners;
  }
  public FileWatcher setListeners(List<FileListener> listeners) {
	  this.listeners = listeners;

	  return this;
  }

}
