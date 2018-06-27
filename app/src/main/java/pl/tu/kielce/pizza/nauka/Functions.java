package pl.tu.kielce.pizza.nauka;

import java.io.File;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Functions {

    public static void main(String[] args) {
        downloadImagesAndSaveThemAtDirectory();
    }

    private static boolean downloadImagesAndSaveThemAtDirectory() {

        String uid = "d00e6592-8dda-4534-9a9f-25a4024ff018";
        String filePath = "d00e6592-8dda-4534-9a9f-25a4024ff018/document.png";
        String downloadPath = "hodor:8080/asd";

        byte[] image = downloadFile(documentPath.apply(downloadPath, filePath));
        byte[] thumbnailImage = downloadFile(documentThumbnailsPath.apply(downloadPath, filePath));

        boolean saveImageStatus = saveImageFileAtDirectory(documentDirectoryPath.apply(uid), getFileName(filePath), image);
        boolean saveThumbnailStatus = saveImageFileAtDirectory(documentThumbnailsDirectoryPath.apply(uid), getFileName(filePath), thumbnailImage);

        return saveImageStatus && saveThumbnailStatus;
    }

    private static byte[] downloadFile(String apply) {
        return new byte[0];
    }

    private static boolean saveImageFileAtDirectory(String apply, String fileName, byte[] thumbnailImage) {
        return true;
    }

    private static String getFileName(String path) {
        return path.substring(path.lastIndexOf('/') + 1);
    }


    private static BiFunction<String, String, String> documentPath = (downloadPath, filePath) -> downloadPath + filePath;
    private static BiFunction<String, String, String> documentThumbnailsPath = (downloadPath, filePath) -> {
        int indexOfSlash = filePath.lastIndexOf('/');
        String thumbFilePath = new StringBuilder()
                .append(filePath, 0, indexOfSlash)
                .append("/thumb")
                .append(filePath.substring(indexOfSlash))
                .toString();

        return downloadPath + thumbFilePath;
    };

    private static Function<String, String> documentDirectoryPath = (uid) -> uid;
    private static Function<String, String> documentThumbnailsDirectoryPath = (uid) -> uid + File.separator +  "thumb";


}
