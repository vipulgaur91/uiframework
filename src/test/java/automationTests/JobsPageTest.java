package automationTests;

import org.testng.annotations.Test;
import pageObjects.HomePageEvents;
import pageObjects.JobsPageEvents;

public class JobsPageTest extends TestInitiator{
	

	/**navigates to the jobs page, view open opportunities in student programmes and validates it
	 * 
	 */
	@Test
	public void navigateToJobsPageAndViewJobsInStudentProgrammes()
	{
		HomePageEvents.navigateToJobsPage();
		JobsPageEvents.verifyIfJobsPageIsLoaded();
		JobsPageEvents.clickAndVerifyJobsinStudentProgrammes();
	}
}
