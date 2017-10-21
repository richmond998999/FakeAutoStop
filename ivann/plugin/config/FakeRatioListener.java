package ivann.plugin.config;

import org.gudy.azureus2.plugins.PluginConfig;
import org.gudy.azureus2.plugins.download.Download;
import org.gudy.azureus2.plugins.download.DownloadException;
import org.gudy.azureus2.plugins.download.DownloadManager;
import org.gudy.azureus2.plugins.download.DownloadManagerListener;
import org.gudy.azureus2.plugins.download.DownloadStats;
import org.gudy.azureus2.plugins.utils.UTTimer;
import org.gudy.azureus2.plugins.utils.UTTimerEvent;
import org.gudy.azureus2.plugins.utils.UTTimerEventPerformer;
import org.gudy.azureus2.plugins.utils.Utilities;

public class FakeRatioListener implements DownloadManagerListener, UTTimerEventPerformer {
	
	private DownloadManager dlManager;
	
	private PluginConfig plugin_config;
	
	private Utilities utility;
	
	private Config config;
	
	private UTTimer timer;
	
	private final long SCAN_INTERVAL = 1000;

	public FakeRatioListener(DownloadManager download_manager, PluginConfig plugin_config, Utilities utility, Config config) {
		
		this.dlManager = download_manager;
		this.plugin_config = plugin_config;
		this.utility = utility;
		this.config = config;
		
		
		timer = utility.createTimer("fakeautotimer");
		timer.addPeriodicEvent(SCAN_INTERVAL, this);
	}

	@Override
	public void perform(UTTimerEvent arg0) {
		
		if(config.isEnable()) {
		
			Download[] downloads = dlManager.getDownloads();
			
			for(Download download : downloads) {
				int fakeShareRatio = download.getStats().getShareRatioFake();
				
				float fakeShareLimitFloat = new Float(config.getFake_ratio_limit());
				int fakeShareLimit = (int) (fakeShareLimitFloat * 1000.00f);
				
//				System.out.println(download.getName() + "'s fake share ratio is " + fakeShareRatio);
//				System.out.println("Download's status is " + download.getState());
				
				if(fakeShareRatio >= fakeShareLimit && Download.ST_STOPPED != download.getState()) {
					System.out.println(download.getName() + " fake share ratio is " + download.getStats().getShareRatioFake() + " with share limit " + fakeShareLimit);
					
					try {
						download.stop();
					} catch (DownloadException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}

	@Override
	public void downloadAdded(Download arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void downloadRemoved(Download arg0) {
		// TODO Auto-generated method stub
		
	}

}
