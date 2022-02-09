package testFileIo;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class TestFileChannelLock extends Thread {
	
	public static void main (String[] ar) throws IOException {
		final String FILE_TESTING_PATH = "./src/test/resources/fileThreadSafeTest.txt";
		
		try (FileChannel channel = FileChannel.open(new File(FILE_TESTING_PATH).toPath(), StandardOpenOption.APPEND)) { 
		    // write to channel
		}
	}
}
