import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class DownloadFile implements Runnable {
    Thread thread;
    String pathUrl;
    public DownloadFile(String pathUrl){
	this.pathUrl = pathUrl;
	this.thread = new Thread(this);
	Main.countT++;
	System.out.println("ЗАПУЩЕНО ПОТОКОВ: " +Main.countT);
	this.thread.start();
    }
    
    public void download(){
    URL url;
    URLConnection urlConnection;
        try {
	    url = new URL(pathUrl);
	    urlConnection = url.openConnection();

	    if ((urlConnection.getContentType().equals("image/jpeg")) || (urlConnection.getContentType().equals("image/JPEG"))) {   //скачивание будет если файл jpg

	        File file = new File(Main.path + "file_" + Main.countFile + ".jpg");

	        BufferedInputStream input = new BufferedInputStream(urlConnection.getInputStream());
	        BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(file));
	        int i;
	        while ((i = input.read()) != -1) {
	            output.write(i);
	        }

	        System.out.println("Download OK: " + pathUrl + " --> file_" + Main.countFile + ".jpg");


	        Main.countFile++;
	        input.close();
	        output.flush();
	        output.close();
	    } else {
	        System.out.println("Download ERROR: " + pathUrl + " --> type file: " + urlConnection.getContentType()); // ошибка если по ссылке что-то другое, а не файл картинки
	    }
	} catch (MalformedURLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (FileNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	}

    @Override
    public void run() {
	
	
	download();
	Main.countT--;
	System.out.println("ЖИВЫХ ПОТОКОВ: " +Main.countT);
	
    }
}
