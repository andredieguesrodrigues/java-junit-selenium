package utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class ExternalSource {

    private static HSSFWorkbook spreadSheet;

    public static String getProperty(String sheetName, String propName) throws IOException {

        loadSpreadSheet();

        HSSFSheet sheet = spreadSheet.getSheet(sheetName);

        String valorPropriedade = null;

        Iterator linhas = sheet.rowIterator();
        while (valorPropriedade == null) {
            HSSFRow linha = (HSSFRow) linhas.next();
            Iterator celulas = linha.cellIterator();
            HSSFCell celula = (HSSFCell) celulas.next();
            String valor = celula.getStringCellValue();
            if (propName.equals(valor)) {
                celula = (HSSFCell) celulas.next();
                valorPropriedade = celula.getStringCellValue();
            }
        }
        return valorPropriedade;
    }

    private static void loadSpreadSheet() throws IOException {
        try {
            InputStream input = new BufferedInputStream(
                    new FileInputStream("config.xls"));
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook spreadSheet = new HSSFWorkbook(fs);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
