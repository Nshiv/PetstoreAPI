package utilities;
import java.io.IOException;
import org.testng.annotations.DataProvider;
public class DataProviders {

    @DataProvider(name="Data")
    public String[][] getAllData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//Userdata.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Users");
        int colcount=xl.getCellCount("Users",1);

        String apidata[][]=new String[rownum][colcount];

        for(int i=1;i<=rownum;i++)
        {
            for(int j=0;j<colcount;j++)
            {
                apidata[i-1][j]= xl.getCellData("Users",i, j);
            }
        }

        return apidata;
    }

    @DataProvider(name="UserNames")
    public String[] getUserNames() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//userData.xlsx";
        XLUtility xl=new XLUtility(path);

        int rownum=xl.getRowCount("Users");

        String apidata[]=new String[rownum];

        for(int i=1;i<=rownum;i++)
        {
            apidata[i-1]= xl.getCellData("Users",i, 1);

        }

        return apidata;
    }

}
