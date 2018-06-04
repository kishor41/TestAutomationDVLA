package testscript;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Test;
import library.ReadExcel;

public class DvlaVehicleTest extends TestCase  {
	
	List<Vehicle> vehicles = new ArrayList();
	
	@Override
	protected void setUp() throws Exception {
		for(int i=0;i<2;i++){
			ReadExcel readExcel=new ReadExcel();
			String vehRegNum=readExcel.excelRead(i,0);
			String vehMake=readExcel.excelRead(i,1);
			String vehColor=readExcel.excelRead(i,2);
		    Vehicle vh = new Vehicle(vehRegNum,vehMake,vehColor);
		    vehicles.add(vh); 
		}
    }
	
    @After
    public void afterMethod() {
        Actions.tearDown();
    }
	
	@Test
	public void testVehicleRegistration() {
		for (Vehicle vh : vehicles) {
			Actions.openBrowser();
			Actions.openApplication("https://vehicleenquiry.service.gov.uk/");
			Actions.enterRegNumber(vh.getRegNumber());
			try {
				Actions.clickContinueButton();
			} catch (Exception e) {
				Actions.tearDown();
			}
			PageResult pgRes = Actions.retrievePageResult();
			assertTrue(vh.getMake().equalsIgnoreCase(pgRes.getMake()));
		}
	}

}
