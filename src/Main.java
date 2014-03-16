import java.io.*;
import java.net.*;
import java.util.ArrayList;


public class Main {

    public static final String path = "images/";
    static int countFile = 1;
    static ArrayList<String> arrayList;
    public static final File DIR = new File(path);
    static int countT = 0;

    public static void main(String[] args) {
	
        if (args.length != 1) {
            System.out.println("Вы должны передать при запуске один параметр - имя файла со ссылками.");
            return;
        }

        arrayList = getPaths(args[0]);

        addDir();

        for (String i : arrayList) {
           new DownloadFile(i);
        }

        addFrameToImage();
    }

    /**
     * метод получения ссылок файлов из файла и сохранения их в ArrayList
     */
    private static ArrayList<String> getPaths(String arg) {
        ArrayList<String> arrayList = new ArrayList<String>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(arg));
            String readLine;

            while ((readLine = bufferedReader.readLine()) != null) {
                arrayList.add(readLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /**
     * метод проверяет существует ли папака для скачаивания файлов, если нет то создает
     */
    private static void addDir() {
        if (!DIR.exists()) {
            if (DIR.mkdir()){
                System.out.println("Folder \"Images\" create\n");
            }else{
                System.out.println("Can not create folder \"Images\"\n");
            }
        } else {
            System.out.println("Folder \"Images\" exists\n");
        }
    }

    /**
     * метод скачаивания файлов
     */
  

    /**
     * метод добавления рамки нf картинку
     */
    private static void addFrameToImage() {

        String[] fileName = DIR.list();                 // получаем в матрицу имена скаченных файлов

        for (String i : fileName) {
            System.out.println("Обрабатывается файл: " + i);
            Frame.addFrame(path, i);

        }
    }
}