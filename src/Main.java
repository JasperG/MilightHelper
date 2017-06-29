/* Demonstration how to use Milight class */

public class Main
{

	public static void main(String[] args)
	{

		/* Set the group of milight devices we want to control. -3 includes RGB, RGBWW and WiFi bulb. */
		Milight.milightGroup = -3;

		/* Create a new Milight instance. This call will block until device discovery is done */
		new Milight();

		/* Check if we have connected to a Milight device */
		if (Milight.isConnected)
		{

			/* We have connected. From now on, one can just set
			 * Milight.newBrightness for 0-100 brightness values
			 * Milight.newSaturation for 0-100 saturation values
			 * Milight.newColor for 0-256 color values
			 */

			/* Switch on the lights */
			Milight.INSTANCE.switchOnOff(true);

			/* Zet de brightness en verzadiging */
			Milight.newBrightness = 100;
			Milight.newSaturation = 100;
			Milight.newColor = 50;

			/* For demonstrational purposes: 5 seconds of random colors to all bulbs */
			try
			{
				for (int i = 0; i < 10; i++)
				{
					/* Wait 0.5 seconds */
					Thread.sleep(500);

					/* Set a new random color */
					Milight.setRandomColor();
				}

			}
			catch (InterruptedException e)
			{
				/* Whatever, if interrupted, dan willen we het proces afbreken */
			}

			/* Demo of a manual color change call: */
			Milight.newColor = 55;
			synchronized (Milight.INSTANCE)
			{
				Milight.INSTANCE.notify();
			}
			/* End demo of manual color change */

			/* Set color based on hex color code */
			try
			{
				Thread.sleep(2000);

				/* Red */
				Milight.setColorAndSaturationBasedOnHex("#ff0000");

				Thread.sleep(2000);

				/* Green */
				Milight.setColorAndSaturationBasedOnHex("#00ff00");
				
				Thread.sleep(2000);
				
				/* White, (full bright white) */
				Milight.setColorAndSaturationBasedOnHex("#ffffff");

				Thread.sleep(2000);
				
				/* Gray (dimmed white) */
				Milight.setColorAndSaturationBasedOnHex("#666666");
				
				Thread.sleep(2000);

				/* Blue */
				Milight.setColorAndSaturationBasedOnHex("#0000ff");
				
				Thread.sleep(400);
			}
			catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* End demo hex color code */

			/* To terminate; Call stopWorkerThread, so the thread that sends keep-alive packets dies. */
			Milight.INSTANCE.stopWorkerThread();

		}
	}

}
