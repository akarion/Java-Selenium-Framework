package ParallelExperiments;

import ParallelExperiments.DriverAdapter;

public abstract class WebTest {
    private DriverAdapter driver;

    public DriverAdapter getDriver(){
        if(driver==null){
            driver = new DriverAdapter();
        }
        return driver;

    }


}
