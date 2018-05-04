package ScanFolder;

import org.apache.log4j.Logger;
import com.onion.common.config.OnionConfig;

public class PathOperation {
	public static final Logger log = Logger.getLogger(PathOperation.class);
	private String path = OnionConfig.getString("file1.path.local");
	
	public void GetPath(){
		System.out.println(this.path);
	}
	
	
	public static void main(String[] args) {
		new PathOperation().GetPath();
	}

}
