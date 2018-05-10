package ScanFolder;

import com.onion.common.config.OnionConfig;

public class PathOperation {
	private String path = OnionConfig.getString("file.path.local");
		
	public PathOperation() {}
	
	public PathOperation(String key) {
		this.path = OnionConfig.getString(key);
	}
	
	public String GetPath(){
		return this.path;
	}
		
	public static void main(String[] args) {
		new PathOperation().GetPath();
	}

}
 